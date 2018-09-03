package com.spring.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Table(name="order_product_propertie")
@Data
public class OrderProductPropertie extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long OrderProductId;

    private Long productPropertieId;
    
    @Transient
    private ProductPropertie productPropertie;
    
    @Transient
    private List<OrderProductPropertieDetail> orderProductPropertieDetailList;
}