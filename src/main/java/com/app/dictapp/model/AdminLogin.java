package com.app.dictapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="adminlogin")
public class AdminLogin {
@Id
@Column(length= 50)
private String adminid;
@Column(length=30,nullable=false)
private String password;
public String getAdminid() {
	return adminid;
}
public void setAdminid(String adminid) {
	this.adminid = adminid;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
