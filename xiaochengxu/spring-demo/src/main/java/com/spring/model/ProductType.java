package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="product_type")
public class ProductType extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long brandId;

    private String name;
}