package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="cash_log")
@Data
public class CashLog extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private Long money;

    private Long cashConfigsId;

    private Long adminId;

    private Short satus;

    private String remarks;

    private Long handleTime;
}