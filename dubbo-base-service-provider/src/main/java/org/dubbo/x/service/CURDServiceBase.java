package org.dubbo.x.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.dubbo.x.entity.IdEntity;
import org.dubbo.x.entity.PageSearch;
import org.dubbo.x.entity.SearchFilter;
import org.dubbo.x.repository.DaoBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

/**
 * @author tom
 */
public abstract class CURDServiceBase<T extends IdEntity> implements CURDService<T> {
    private final static Logger LOGGER = LoggerFactory.getLogger(CURDServiceBase.class);
    private final static String WHERE_TYPE_AND = "AND";
    private final static String WHERE_TYPE_OR = "OR";

    private String token;
    private IdEntity currentUser;

    @Override
    public IdEntity getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(IdEntity currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public List<T> listAll() {
        return getDao().findAllBy();
    }

    @Override
    public Page<T> page(PageSearch pageSearch) {
        Sort sort = new Sort(Sort.Direction.fromString("desc"), "createDate");

        if (pageSearch.getSort() != null) {
            sort = new Sort(Sort.Direction.fromString(pageSearch.getSort().getDirection()), StringUtils.split(pageSearch.getSort().getFieldName(),","));
        }

        LOGGER.debug("pageSearch:{}", pageSearch);

        Pageable pageable = new PageRequest(pageSearch.getPageNumber() - 1, pageSearch.getPageSize(), sort);

        Specification<T> spec = null;

        if (null != pageSearch.getAndFilters() && pageSearch.getAndFilters().size()>0){
            spec = bySearchFilter(pageSearch.getFilters(), pageSearch.getAndFilters());
        }else{
            spec = bySearchFilter(pageSearch.getFilters());
        }

        Page<T> page = this.getDao().findAll(spec, pageable);

        LOGGER.debug("page:{}", page);

        return page;
    }

    public Specification<T> bySearchFilter(final List<SearchFilter> filters) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate orFilter = toPredicates(root, builder, filters, WHERE_TYPE_OR);

                return builder.or(orFilter);
            }
        };
    }

    public Specification<T> bySearchFilter(final List<SearchFilter> filters, final List<SearchFilter> andFilters) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                Predicate orFilter = toPredicates(root, builder, filters, WHERE_TYPE_OR);
                Predicate andFilter = toPredicates(root, builder, andFilters, WHERE_TYPE_AND);

                return builder.and(orFilter, andFilter);
            }
        };
    }

    private Predicate toPredicates(Root<T> root, CriteriaBuilder builder,List<SearchFilter> filters, String whereType){
        List<Predicate> predicates = Lists.newArrayList();

        for (SearchFilter filter : filters) {
            // nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
            String[] names = StringUtils.split(filter.fieldName, ".");
            Path expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }

            // logic operator
            switch (filter.operator) {
                case EQ:
                    predicates.add(builder.equal(expression, filter.value));
                    break;
                case LIKE:
                    predicates.add(builder.like(expression, "%" + filter.value + "%"));
                    break;
                case GT:
                    predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
                    break;
                case LT:
                    predicates.add(builder.lessThan(expression, (Comparable) filter.value));
                    break;
                case GTE:
                    predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
                    break;
                case LTE:
                    predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
                    break;
                case NOT_EQ:
                    predicates.add(builder.notEqual(expression, (Comparable) filter.value));
                    break;
            }
        }
        // 将所有条件用 and 联合起来
        if (!predicates.isEmpty()) {
            if (WHERE_TYPE_AND.equalsIgnoreCase(whereType)) {
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }else{
                return builder.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        }

        return builder.conjunction();
    }



    @Override
    public T createOrUpdte(T entity) {

        if (entity instanceof IdEntity) {
            try {
                if (null == entity.getId()) {
                    BeanUtilsBean.getInstance().setProperty(entity, "createDate", new Date());
                    BeanUtilsBean.getInstance().setProperty(entity, "createUserId", getCurrentUser().getId());
                }
                BeanUtilsBean.getInstance().setProperty(entity, "updateDate", new Date());
                BeanUtilsBean.getInstance().setProperty(entity, "updateUserId", getCurrentUser().getId());
            } catch (Exception e) {
                LOGGER.warn("CU set propertie error!");
            }
        }

        getDao().save(entity);

        return entity;
    }

    @Override
    public void delete(String uuid) {
        getDao().delete(uuid);
    }

    @Override
    public T detail(String uuid) {
        return getDao().findOne(uuid);
    }

    public abstract DaoBase<T> getDao();
}
