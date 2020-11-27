package com.myprojects.spring.security.entity.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.userdetails.UserDetails;

import com.myprojects.spring.security.entity.common.BaseEntity;
import com.myprojects.spring.security.entity.organization.Organization;

@SuppressWarnings("serial")
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username", "organization_id" }) })
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class User extends BaseEntity implements UserDetails, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "name can't be null")
	private String name;

	@NotNull(message = "username can't be null")
	@Pattern(regexp = "^[@A-Za-z0-9_]{3,20}$", message = "username should contains only alphabets/digit/@ and length should be in between 4 to 20")
	private String username;

	@NotNull(message = "password can't be null")
	private String password;

	private Boolean active;

	private String generatedPassword;

	private String email;

	@Size(min = 10, max = 10)
	@Pattern(regexp = "^[0-9]*$", message = "Mobile no should contain only digit")
	private String mobileNo;

	private String contactNo;

	private String notificationToken;

	private String picUrl;

	@Column(columnDefinition = "boolean default false")
	private boolean accountExpired = false;

	@Column(columnDefinition = "boolean default false")
	private boolean accountLocked = false;

	@Column(columnDefinition = "boolean default false")
	private boolean credentialsExpired = false;

	@Column(columnDefinition = "boolean default false")
	private boolean enabled = true;

	@ManyToOne
	private Organization organization;

	@Transient
	private Long tOrganizationId;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserRole> userRoles;

	@Transient
	private Collection<Authority> authorities;

	@Transient
	private Collection<Role> roles;

	@Transient
	private Long userId;

	public User() {

	}

	public User(
			@NotNull @Pattern(regexp = "^[@A-Za-z0-9_]{3,20}$", message = "username should contains only alphabets/digit/@ and length should be in between 4 to 20") String username,
			@NotNull String password, Collection<Authority> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public Collection<Authority> getAuthorities() {
		return authorities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getNotificationToken() {
		return notificationToken;
	}

	public void setNotificationToken(String notificationToken) {
		this.notificationToken = notificationToken;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired();
	}

	public String getGeneratedPassword() {
		return generatedPassword;
	}

	public void setGeneratedPassword(String generatedPassword) {
		this.generatedPassword = generatedPassword;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long gettOrganizationId() {
		return tOrganizationId;
	}

	public void settOrganizationId(Long tOrganizationId) {
		if (tOrganizationId != null) {
			this.organization = new Organization();
			this.organization.setId(tOrganizationId);
		}
		this.tOrganizationId = tOrganizationId;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
