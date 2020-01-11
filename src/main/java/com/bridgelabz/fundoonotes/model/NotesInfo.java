package com.bridgelabz.fundoonotes.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "note")
@Getter
@Setter

@NoArgsConstructor
@ToString
public class NotesInfo {
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

	private Date createdAt;

	private Date updatedAt;

	public NotesInfo(String title, String takeanote) {
		super();
		this.title = title;
		this.takeanote = takeanote;
	}

	@ManyToMany
	private List<Label> label;
	
	@OneToOne
	private UserInfo user;
	

	@Column(columnDefinition = "varchar(10) default 'ffffff' ")
	private String color;

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

	private Date localReminder;

}
