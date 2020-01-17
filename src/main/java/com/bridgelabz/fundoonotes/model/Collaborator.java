package com.bridgelabz.fundoonotes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Collaborator 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String collaborator;
	
	
	public Collaborator() 
	{
		super();
		
	}

	@JsonIgnore
	@ManyToOne
	private NotesInfo notes;

	public Collaborator(long id, String collaborator, NotesInfo notes) {
		super();
		this.id = id;
		this.collaborator = collaborator;
		this.notes = notes;
	}

	public NotesInfo getNotes() {
		return notes;
	}

	public void setNotes(NotesInfo notes) {
		this.notes = notes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(String collaborator) 
	{
		this.collaborator = collaborator;
	}

	public Collaborator(String collaborator, long id)
	{
		super();
		this.id = id;
		this.collaborator = collaborator;
	}

	
	

	public Collaborator(String collaborator2, NotesInfo note) 
	{
		this.collaborator=collaborator2;
		this.notes=note;
	}

	@Override
	public String toString() {
		return "Collaborator [id=" + id + ", collaborator=" + collaborator + ", notes=" + notes + "]";
	}
	
}
