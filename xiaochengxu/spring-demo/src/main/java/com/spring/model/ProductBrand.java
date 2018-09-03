package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="product_brand")
@Data
public class ProductBrand extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;
	
	private Long productId;

    private String name;

    private String logo;

    private String remark;
    
}