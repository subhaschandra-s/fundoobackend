package com.bridgelabz.fundoonotes.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "note")
public class NotesInfo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	private String takeanote;

	@Column(columnDefinition = "boolean default false")
	private boolean isPinned;

	@Column(columnDefinition = "boolean default false")
	private boolean isArchived;

	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;
	
	private String reminderstatus;

	public String getReminder() {
		return reminderstatus;
	}


	public void setReminder(String reminderstatus) {
		this.reminderstatus = reminderstatus;
	}


	@Column(columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;


	
	@ManyToMany
	private List<Label> label;
	
	
	@OneToMany(mappedBy = "notes")
	private List<Collaborator> collaborator;
	
	public List<Collaborator> getCollaborator() {
		return collaborator;
	}


	public void setCollaborator(List<Collaborator> collaborator) {
		this.collaborator = collaborator;
	}


	@JsonIgnore
	@OneToOne
	private UserInfo userinfo;
	

	@Column(columnDefinition = "varchar(10) default 'ffffff' ")
	private String color;

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getTakeanote() {
		return takeanote;
	}


	public void setTakeanote(String takeanote) {
		this.takeanote = takeanote;
	}


	public boolean isPinned() {
		return isPinned;
	}


	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}


	public boolean isArchived() {
		return isArchived;
	}


	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}


	public boolean isDeleted() {
		return isDeleted;
	}


	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt() {
		this.createdAt = new Date();
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}


	public NotesInfo() 
	{
		super();
		
	}


	public NotesInfo(String title, String takeanote,String reminderstatus,String color, List<Label> label, UserInfo userinfo) {
		super();
		
		this.title = title;
		this.takeanote = takeanote;
		this.label = label;
		this.userinfo = userinfo;
		this.reminderstatus=reminderstatus;
		this.color=color;
		
	}


	public NotesInfo(int id2, String title2, String takeanote2, String reminderstatus2, String color2,
			List<Label> labels, UserInfo user1) 
	{
		this.id=id2;
		this.title=title2;
		this.takeanote=takeanote2;
		this.reminderstatus=reminderstatus2;
		this.color=color2;
		this.label=labels;
		this.userinfo=user1;
	
	
	}



	public List<Label> getLabel() {
		return label;
	}


	public void setLabel(List<Label> label) {
		this.label = label;
	}


	public UserInfo getUserinfo() {
		return userinfo;
	}


	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	@Override
	public String toString() {
		return "NotesInfo [id=" + id + ", title=" + title + ", takeanote=" + takeanote + ", isPinned=" + isPinned
				+ ", isArchived=" + isArchived + ", isDeleted=" + isDeleted + ", reminderstatus=" + reminderstatus
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", label=" + label + ", userinfo="
				+ userinfo + ", color=" + color + "]";
	}

	
}
