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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the setting_group database table.
 * 
 */
@Entity
@Table(name = "setting_group")
public class SettingGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SETTING_GROUP_ID")
	@AccessType("property")
	private int settingGroupId;
	
	@Column(name="SETTING_GROUP_NAME")
	private String settingGroupName;
	
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "settingGroup")
	private List<Setting> settings;

	public int getSettingGroupId() {
		return settingGroupId;
	}

	public String getSettingGroupName() {
		return settingGroupName;
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

	public List<Setting> getSettings() {
		return settings;
	}

	public void setSettingGroupId(int settingGroupId) {
		this.settingGroupId = settingGroupId;
	}

	public void setSettingGroupName(String settingGroupName) {
		this.settingGroupName = settingGroupName;
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

	public void setSettings(List<Setting> settings) {
		this.settings = settings;
	}

}