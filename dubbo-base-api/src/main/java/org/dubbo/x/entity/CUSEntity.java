package org.dubbo.x.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * Created by tom on 16/6/29.
 */


@MappedSuperclass
public class CUSEntity extends StatusEntity{
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 创建日期
     */
    private Long createDate;
    /**
     * 修改人
     */
    private String updateUserId;
    /**
     * 修改日期
     */
    private Long updateDate;
    /**
     * 状态 1 删除 0 未删除
     */
    private StatusEnum status = StatusEnum.OK;


    @JsonIgnore
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @JsonIgnore
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Enumerated(EnumType.STRING)
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
