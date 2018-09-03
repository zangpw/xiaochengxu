package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Table(name="sms_log")
public class SmsLog extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Short type;

    private Long memberId;

    private String content;

    private String phoneNumber;

    private String returnCode;

    private Long createTime;

    private Short dataFlag;
}