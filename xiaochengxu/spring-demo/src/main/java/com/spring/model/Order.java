package com.spring.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Table(name="orders")
@Data
public class Order extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long memberId;

    private String trueName;

    private String mobile;

    private String address;

    private Short state;

    private Long cancelTime;

    private Long sendTime;

    private Long startCheckTime;

    private Long endCheckTime;

    private Long finishTime;

    private Long returnTime;

    private Long returnFinishTime;

    private String trackingNumber;

    private BigDecimal recoverPrice;

    private Long recyclingMethodId;

    private Long recyclingStartTime;

    private Long recyclingEndTime;
    
    private Long productId;
}