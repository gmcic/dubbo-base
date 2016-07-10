package org.dubbo.x.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * 分页信息
 * Created by tom on 16/7/4.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel("分页信息")
public class PageSearch implements Serializable {
    /**
     * 每页记录数
     */
    @ApiModelProperty("每页记录数")
    private int pageSize = 20;
    /**
     * 页号 从 1 开始
     */
    @ApiModelProperty("页号 从 1 开始")
    private int pageNumber = 1;

    /**
     * 搜索条件
     */
    @ApiModelProperty("搜索条件")
    private List<SearchFilter> filters;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Sort sort;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<SearchFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<SearchFilter> filters) {
        this.filters = filters;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "PageSearch{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", filters=" + filters +
                ", sort=" + sort +
                '}';
    }
}
