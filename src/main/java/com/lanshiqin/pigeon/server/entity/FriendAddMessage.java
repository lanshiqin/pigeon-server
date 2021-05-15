package com.lanshiqin.pigeon.server.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 好友添加消息关系表
 *
 * @author 蓝士钦
 */
@TableName("friend_add_message")
public class FriendAddMessage {

    /**
     * 添加好友请求原始id
     */
    @TableId
    private String requestId;

    /**
     * 请求发起读账号id
     */
    private Integer accountId;

    /**
     * 添加目标的账号id
     */
    private Integer objectAccountId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 目标账号读取状态
     * true 已读
     * false 未读
     */
    private Boolean objectAccountRead;

    /**
     * 目标账号同意请求状态
     * 0 未同意
     * 1 已同意
     * -1 已拒绝
     */
    private Integer objectAgreedStatus;

    /**
     * 请求的创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getObjectAccountId() {
        return objectAccountId;
    }

    public void setObjectAccountId(Integer objectAccountId) {
        this.objectAccountId = objectAccountId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getObjectAccountRead() {
        return objectAccountRead;
    }

    public void setObjectAccountRead(Boolean objectAccountRead) {
        this.objectAccountRead = objectAccountRead;
    }

    public Integer getObjectAgreedStatus() {
        return objectAgreedStatus;
    }

    public void setObjectAgreedStatus(Integer objectAgreedStatus) {
        this.objectAgreedStatus = objectAgreedStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
