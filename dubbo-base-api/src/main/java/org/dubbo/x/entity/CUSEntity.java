package org.dubbo.x.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * Created by tom on 16/6/29.
 */

@MappedSuperclass
@Getter
@Setter
@ToString
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

    @Enumerated(EnumType.STRING)
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
