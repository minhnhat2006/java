package com.sharpinu.persist.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_option database table.
 * 
 */
@Entity
@Table(name="t_option")
@NamedQuery(name="TOption.findAll", query="SELECT t FROM TOption t")
public class TOption implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="T_OPTION_ID")
	private int tOptionId;
	
	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="IS_KEY")
	private boolean isKey;

	//bi-directional many-to-one association to TQuestion
	@ManyToOne
	@JoinColumn(name="QUESTION_ID")
	private TQuestion tQuestion;

	public TOption() {
	}

	public int getTOptionId() {
		return this.tOptionId;
	}

	public void setTOptionId(int tOptionId) {
		this.tOptionId = tOptionId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsKey() {
		return this.isKey;
	}

	public void setIsKey(boolean isKey) {
		this.isKey = isKey;
	}

	public TQuestion getTQuestion() {
		return this.tQuestion;
	}

	public void setTQuestion(TQuestion tQuestion) {
		this.tQuestion = tQuestion;
	}

}