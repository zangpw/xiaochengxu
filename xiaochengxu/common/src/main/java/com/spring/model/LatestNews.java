package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="latest_news")
@Data
public class LatestNews extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String title;

    private Short isHotSpot;

    private String author;

    private String img;

    private String informationContent;

    private Integer clickNum;
}