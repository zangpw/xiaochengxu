package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="recover_product_price_detail")
public class RecoverProductPriceDetail extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long recoverProductPricePropertieId;

    private Long propertyDetailId;

}