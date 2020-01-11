package com.bridgelabz.fundoonotes.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class NotesDTO 
{
	
	private String title;
	private String takeanote;
	
	
	private  List<String> label;
	

	

}
