package org.dubbo.x.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 搜索过滤条件
 * Created by tom on 16/7/4.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel("搜索过滤条件")
public class SearchFilter implements Serializable {
    public enum Operator {
        EQ, LIKE, GT, LT, GTE, LTE
    }

    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    public String fieldName;
    /**
     * 搜索内容
     */
    @ApiModelProperty("搜索内容")
    public Object value;
    /**
     * 搜索条件 EQ, LIKE, GT, LT, GTE, LTE
     */
    @ApiModelProperty("搜索条件 EQ, LIKE, GT, LT, GTE, LTE")
    public Operator operator;

    public SearchFilter() {
    }

    public SearchFilter(String fieldName, Operator operator, Object value) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = Operator.valueOf(operator);
    }

    @Override
    public String toString() {
        return "SearchFilter{" +
                "fieldName='" + fieldName + '\'' +
                ", value=" + value +
                ", operator=" + operator +
                '}';
    }
}
