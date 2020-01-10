package com.bridgelabz.fundoonotes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

@Component
@Entity
public class UserInfo 
{
	
   public UserInfo() 
   {
		
	}

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
   
   @NotNull
	private String firstname;
	private String lastname;
	
	@Column(unique = true)
	@NotNull
	private String emailId;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isverified;
	
	

	@Column(unique = true)
	@NotNull
	private long mobileNo;
	
	@NotNull
	private String password;
	
	
	@CreatedDate
	private Date createdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIsverified() {
		return isverified;
	}

	public void setIsverified(boolean isverified) {
		this.isverified = isverified;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate() 
	{
		this.createdate = new Date();
				}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", emailId=" + emailId
				+ ", mobileNo=" + mobileNo + ", password=" + password + ", createdate=" + createdate + "]";
	}
	
		
}
