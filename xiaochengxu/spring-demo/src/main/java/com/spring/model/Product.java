package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="product")
@Data
public class Product extends Base {
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String name;

    private String logo;
}