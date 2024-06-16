package com.lzl.tkmybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.Column;
import java.util.Date;

@Data
public abstract class BaseTkEntity {
    /**
     * 删除状态
     * 0:未删除
     * 1:已删除
     */
    @Column(name = "deleted")
    @LogicDelete(isDeletedValue = 1,notDeletedValue = 0)
    protected Boolean deleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    protected Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    protected String createUser;

    /**
     * 最近更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    protected Date updateTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    protected String updateUser;

    /**
     * 设置创建人和创建时间
     *
     * @param baseEntityObj baseEntity的子类对象
     * @param <T>           对象类型
     */
    public <T extends BaseTkEntity> void setUserAndTime(T baseEntityObj) {
        this.createTime = baseEntityObj.getCreateTime();
        this.createUser = baseEntityObj.getCreateUser();
        this.updateTime = baseEntityObj.getUpdateTime();
        this.updateUser = baseEntityObj.getUpdateUser();
    }
}
