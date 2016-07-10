package org.dubbo.x.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface DaoBase<T> extends PagingAndSortingRepository<T, String>,JpaSpecificationExecutor<T> {
    List<T> findAllBy();
}
