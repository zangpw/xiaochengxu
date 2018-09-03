package com.spring.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Table(name="member")
@Data
public class Member extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;

    private String name;

    private String nickName;

    private String password;

    private String avatar;

    private Integer points;

    private BigDecimal overMoney;

    private String mobile;

    private String receiptCode;

    private String trueName;

    private String bank;

    private String bankCard;

    private String alipayAccount;

    private String school;
}