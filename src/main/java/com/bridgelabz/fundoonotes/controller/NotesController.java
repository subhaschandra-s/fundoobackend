package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.service.NotesService;

@RestController
public class NotesController 
{
	@Autowired
	private NotesService notesservice;

	@PostMapping("/save")
	public String save(@RequestParam NotesDTO notesDTO,String jwt)
	{
		NotesInfo notes=notesservice.saveanote(notesDTO,jwt);
				
		return null;
		
	}
}
