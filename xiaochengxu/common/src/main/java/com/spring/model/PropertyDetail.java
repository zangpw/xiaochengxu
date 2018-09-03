package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="property_detail")
public class PropertyDetail extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;

    private Long productPropertieId;

    private String value;

    private Double weightRatio;

    private String remark;

}