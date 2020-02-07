package com.bridgelabz.fundoonotes.dto;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "elasticsearch",type = "notedto")
public class NotesDTO 
{
	private int id;
	
	
	private String title;
	private String takeanote;
	private String reminderstatus;
	private String color;
	private boolean isPinned;
	private boolean isArchived;
	private boolean isTrashed;
	
	

	private  List<String> label;
	
	
	public NotesDTO()
	{
		
	}
	public NotesDTO(int id, String title, String takeanote, String reminderstatus,boolean isPinned,boolean isArchived, String color, boolean isTrashed,List<String> label) {
		super();
		this.id = id;
		this.title = title;
		this.takeanote = takeanote;
		this.reminderstatus = reminderstatus;
		this.color = color;
		this.label = label;
		this.isPinned=isPinned;
		this.isArchived=isArchived;
		this.isTrashed=isTrashed;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getReminderstatus() {
		return reminderstatus;
	}

	public void setReminderstatus(String reminderstatus) {
		this.reminderstatus = reminderstatus;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getLabel() {
		return label;
	}

	public void setLabel(List<String> label) {
		this.label = label;
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
	public boolean isTrashed() {
		return isTrashed;
	}
	public void setTrashed(boolean isTrashed) {
		this.isTrashed = isTrashed;
	}
	
	
	

	

}
