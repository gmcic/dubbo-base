package org.dubbo.x.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * Created by tom on 16/6/29.
 */


@MappedSuperclass
public class StatusEntity extends IdEntity {

    /**
     * 状态 1 删除 0 未删除
     */
    private StatusEnum status = StatusEnum.OK;

    @Enumerated(EnumType.STRING)
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
