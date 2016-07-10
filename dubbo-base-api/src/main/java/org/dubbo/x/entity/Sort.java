package org.dubbo.x.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 排序
 * Created by tom on 16/7/4.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@ApiModel("排序")
public class Sort implements Serializable {
    /**
     * 属性名称
     */
    @ApiModelProperty("属性名称")
    public String fieldName;
    /**
     * 排序:升序 asc 降序 desc
     */
    @ApiModelProperty("排序:升序 asc 降序 desc")
    public String direction;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Sort{" +
                "fieldName='" + fieldName + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }
}
