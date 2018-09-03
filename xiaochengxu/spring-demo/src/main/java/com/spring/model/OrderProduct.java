package com.spring.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Table(name="order_product")
@Data
public class OrderProduct extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long OrderId;

    private Long productModelId;
    
    @Transient
    private ProductModel productMode;
    @Transient
    private BigDecimal price;

}