package com.sharpinu.persist.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;
import org.springframework.beans.BeanUtils;

import com.sharpinu.web.form.admin.OurPracticeCategoryForm;

@Entity
@Table(name = "ourPracticeCategory")
public class OurPracticeCategory implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ourPracticeCategory_id")
	@AccessType("property")
	private Integer ourPracticeCategoryId;

	@Column(name = "name")
	private String name;

	@Column(name = "created_date")
	private Date createdDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ourPracticeCategory")
	private List<OurPractice> ourPractices;

	public OurPracticeCategory() {
	}

	public OurPracticeCategory(OurPracticeCategoryForm ourPracticeCategoryForm) {
		super();
		BeanUtils.copyProperties(ourPracticeCategoryForm, this);
		if (ourPracticeCategoryForm.getOurPracticeCategoryId() > 0) {
			this.setOurPracticeCategoryId(ourPracticeCategoryForm.getOurPracticeCategoryId());
			this.setCreatedDate(ourPracticeCategoryForm.getCreatedDate());
		} else {
			this.createdDate = new Date();
		}
	}

	public Integer getOurPracticeCategoryId() {
		return this.ourPracticeCategoryId;
	}

	public void setOurPracticeCategoryId(Integer ourPracticeCategoryId) {
		this.ourPracticeCategoryId = ourPracticeCategoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<OurPractice> getOurPractices() {
		return ourPractices;
	}

	public void setOurPractices(List<OurPractice> ourPractices) {
		this.ourPractices = ourPractices;
	}

}
