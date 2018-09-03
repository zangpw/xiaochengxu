package com.spring.model;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="recover_method")
public class RecoverMethod extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String name;

}