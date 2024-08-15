package com.micro.question_service.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="txn_quiz")
public class QuizModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="total")
	private Integer total;
	
	@Column(name="correct")
	private Integer correct;
	
	@Column(name="wrong")
	private Integer wrong;
	
	@Column(name="log_dt")
	private Date logDt;
}
