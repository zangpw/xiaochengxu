package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Table(name="order_product_propertie_detail")
@Data
public class OrderProductPropertieDetail extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long recoverOrderProductPropertieId;

    private Long propertyDetailId;
    
    @Transient
    private PropertyDetail propertyDetail;

}