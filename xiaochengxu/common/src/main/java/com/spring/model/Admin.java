package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="admin")
@Data
public class Admin extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String name;

    private String password;
}