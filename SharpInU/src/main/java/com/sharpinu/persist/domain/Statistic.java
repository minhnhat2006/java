package com.sharpinu.persist.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

@Entity
@Table(name = "statistic")
public class Statistic implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "field")
	@AccessType("property")
	private String field;

	@Column(name = "count")
	private Integer count;

	public Statistic() {
	}

	public Statistic(String field, Integer count) {
		this.field = field;
		this.count = count;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
