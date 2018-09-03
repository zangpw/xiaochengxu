package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="dictionary")
@Data
public class Dictionary extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String value;

}