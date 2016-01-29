package com.sharpinu.persist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import java.sql.Timestamp;


/**
 * The persistent class for the setting database table.
 * 
 */
@Entity
@Table(name = "setting")
public class Setting implements Serializable {
	private static final long serialVersionUID = 1L;

	public interface TYPE {
		public int GLOBAL = 0;
		public int USER = 1;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SETTING_ID")
	@AccessType("property")
	private int settingId;
	
	@Column(name="SETTING_NAME")
	private String settingName;
	
	@Column(name="SETTING_VALUE")
	private String settingValue;
	
	@Column(name="SETTING_TYPE")
	private int settingType;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="UPDATED_DATE")
	private Timestamp updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_USER_ID")
	private User createdUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_USER_ID")
	private User updatedUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SETTING_GROUP_ID")
	private SettingGroup settingGroup;

	public int getSettingId() {
		return settingId;
	}

	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	public String getSettingValue() {
		return settingValue;
	}

	public int getSettingType() {
		return settingType;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public User getCreatedUser() {
		return createdUser;
	}

	public User getUpdatedUser() {
		return updatedUser;
	}

	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

	public void setSettingValue(String settingValue) {
		this.settingValue = settingValue;
	}

	public void setSettingType(int settingType) {
		this.settingType = settingType;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}

	public void setUpdatedUser(User updatedUser) {
		this.updatedUser = updatedUser;
	}

	public SettingGroup getSettingGroup() {
		return settingGroup;
	}

	public void setSettingGroup(SettingGroup settingGroup) {
		this.settingGroup = settingGroup;
	}
}