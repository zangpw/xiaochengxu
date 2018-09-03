package com.spring.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="question_answer")
public class QuestionAnswer extends Base{
	@Id
	@GeneratedValue(generator = "JDBC")
    private Long id;

    private String question;

    private String answer;

}