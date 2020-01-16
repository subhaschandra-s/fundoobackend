package com.bridgelabz.fundoonotes.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "label")
public class Label 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lid;

	private String lname;
	
	public Label()
	{
		super();
		
	}

	public Label(int lid, String lname, List<NotesInfo> notes, UserInfo userinfo) {
		super();
		this.lid = lid;
		this.lname = lname;
		this.notes = notes;
		this.userinfo = userinfo;
	}

	public Label(String lname2, UserInfo user) 
	{
		this.lname=lname2;
		this.userinfo=user;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public List<NotesInfo> getNotes() {
		return notes;
	}

	public void setNotes(List<NotesInfo> notes) {
		this.notes = notes;
	}
	
	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	@JsonIgnore
	@ManyToMany(mappedBy = "label")
	private List<NotesInfo> notes;
	
	@JsonIgnore
	@ManyToOne
	private UserInfo userinfo;

	@Override
	public String toString() {
		return "Label [lid=" + lid + ", lname=" + lname + ", notes=" + notes + ", userinfo=" + userinfo + "]";
	}
	

}
