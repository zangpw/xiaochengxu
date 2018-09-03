package com.spring.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderVo {
	Long orderId;
	
	Long orderSn;

	BigDecimal orderPrice;

	Short state;

	Long createTime;

	String trueName;

	String brandName;

	String typeName;

	String modelName;
	
	String modelImg;
	
	String remarks;
	
	String trackingCompany;
	
	String trackingNumber;
	
	String mobile;
	
	BigDecimal recoverPrice;
	
	BigDecimal consultPrice;

	List<ProductPropertie> properties;
}
