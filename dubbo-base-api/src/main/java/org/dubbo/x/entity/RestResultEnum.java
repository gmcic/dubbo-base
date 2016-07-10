package org.dubbo.x.entity;

/**
 * Created by tom on 16/6/27.
 */
public enum RestResultEnum {
    OK(0, "OK"),
    NOT_FONT(404, "数据未找到!"),
    SERVER_ERROR(500, "服务器错误!"),
    INVALID_PARAMETER(501, "参数错误错误!"),
    UNAUTHORIZED(401, "未经授权!");

    RestResultEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
