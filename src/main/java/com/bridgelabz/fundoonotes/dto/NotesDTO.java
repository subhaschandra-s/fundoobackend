package com.bridgelabz.fundoonotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotesDTO 
{
	private String title;
	private String takeanote;
	private boolean ispinned;
	private boolean isarchived;
	private String color;
	
	

}
