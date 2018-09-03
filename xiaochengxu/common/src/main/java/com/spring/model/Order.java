package com.spring.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
@Table(name="orders")
@Data
public class Order extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	
	private Long orderSn;

    private Long productId;

    private Long memberId;

    private String trueName;

    private String mobile;

    private String address;

    private Short state;
    
    private BigDecimal orderPrice;

    private BigDecimal consultPrice;

    private String remarks;

    private Long cancelTime;

    private Long sendTime;

    private Long startCheckTime;

    private Long endCheckTime;

    private Long finishTime;

    private Long returnTime;

    private Long returnFinishTime;

    private String trackingCompany;

    private String trackingNumber;

    private BigDecimal recoverPrice;

    private Long recyclingMethodId;

    private Long recyclingStartTime;

    private Long recyclingEndTime;
    
    @Transient
    private Member member;
    
    @Transient
    private List<OrderProduct> orderProduct;
    
}