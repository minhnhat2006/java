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

@Entity
@Table(name = "role")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@AccessType("property")
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "date_created")
	private Date dateCreated;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	private List<UserRole> userRoles;

	public Role() {
	}

	public Role(String roleName, Date dateCreated) {
		this.roleName = roleName;
		this.dateCreated = dateCreated;
	}

	public Role(String roleName, Date dateCreated, List<UserRole> userRoles) {
		this.roleName = roleName;
		this.dateCreated = dateCreated;
		this.userRoles = userRoles;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
}
