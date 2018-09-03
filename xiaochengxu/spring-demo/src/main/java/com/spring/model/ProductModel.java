package com.spring.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
@Data
@Table(name="product_model")
public class ProductModel extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long productTypeId;

    private String img;

    private String name;

    private BigDecimal standardPrice;

    private Integer cumulativeQuantity;

    private Long expectedDecline;
    
    @Transient
    private List<ProductPropertie> productPropertieList;

}