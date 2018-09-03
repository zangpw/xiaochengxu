package com.spring.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
@Data
@Table(name="recover_product_price")
public class RecoverProductPrice extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long productModelId;

    private Long memberId;

    private BigDecimal recoverProductPrice;
    
    @Transient
    private ProductModel productModel;
}