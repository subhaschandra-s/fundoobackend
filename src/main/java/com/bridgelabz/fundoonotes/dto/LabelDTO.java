package com.bridgelabz.fundoonotes.dto;

public class LabelDTO 
{
	private int lid;
	
	private String lname;

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

	public LabelDTO() 
	{
		super();
	
	}

	public LabelDTO(int lid, String lname) {
		super();
		this.lid = lid;
		this.lname = lname;
	}

}
