package com.spring.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
@Table(name="product_propertie")
@Data
public class ProductPropertie extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long productModelId;

    private String name;
    
    private Short isSelect;
    
    @Transient
    List<PropertyDetail> propertyDetailList;

}