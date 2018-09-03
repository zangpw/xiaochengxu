package com.spring.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="information")
@Data
public class Information extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String name;

    private Long memberId;

    private Long orderId;

    private String content;

    private Short isRead;

    private Long createTime;

    private Short dataFlag;

    private Long updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Short getIsRead() {
        return isRead;
    }

    public void setIsRead(Short isRead) {
        this.isRead = isRead;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Short getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(Short dataFlag) {
        this.dataFlag = dataFlag;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }
}