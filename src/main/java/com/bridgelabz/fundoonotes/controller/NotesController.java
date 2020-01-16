package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.NotesService;

@RequestMapping("/note")
@RestController
public class NotesController 
{
	@Autowired
	private NotesService notesservice;

	@PostMapping("/save")
	private ResponseEntity<Response>newnote(@RequestBody NotesDTO notesDTO,@RequestHeader("Authorization") String jwt) throws Exception
	{
		if(notesservice.saveanote(notesDTO,jwt))
		return ResponseEntity.ok().body(new Response("note created",200,notesDTO));
		else
		return ResponseEntity.ok().body(new Response("problem in creating note",400,notesDTO));
	}

	@DeleteMapping("/delete")
	private ResponseEntity<Response>delete(@RequestParam("noteid") int id)
	{
		if(notesservice.deletenote(id))
			return ResponseEntity.ok().body(new Response("note deleted",200,"done"));
		else
			return ResponseEntity.ok().body(new Response("problems occured",400,"Failed"));
			
	}
	
	@PutMapping("/update")
	private ResponseEntity<Response>update(@RequestBody NotesDTO notesDTO,@RequestHeader("Authorization") String jwt) throws Exception
	{
		if(notesservice.update(notesDTO, jwt))
			return ResponseEntity.ok().body(new Response("note updated",200,notesDTO));
		else
		return ResponseEntity.ok().body(new Response("Failed",200,notesDTO));
		
	}
	
	@GetMapping("/get")
	private ResponseEntity<Response> getnote(@RequestParam("noteid")int id)
	{
		NotesInfo notes=notesservice.getnote(id);
		
		if(notes!=null)
		 return ResponseEntity.ok().body(new Response("successfull",200,notes));
		else
			return ResponseEntity.ok().body(new Response("problems occured",400,"Failed"));	
	}
	
	@GetMapping("/getAllnotes")
	private ResponseEntity<Response> getAllnotes(@RequestHeader("Authorization") String jwt)
	{
		if(notesservice.getAllnotes(jwt) != null)
		return ResponseEntity.ok().body(new Response("Available",200,notesservice.getAllnotes(jwt)));
		else
			return ResponseEntity.ok().body(new Response("Problems occur",400,"failed"));
	}
	
	@GetMapping("/getAllPinned")
	private ResponseEntity<Response>getAllPinnednotes(@RequestHeader("Authorization") String jwt)
	{
		if(notesservice.getAllPinned(jwt)!=null)
			return ResponseEntity.ok().body(new Response("All Pinned",200,notesservice.getAllPinned(jwt)));
		else
			return ResponseEntity.ok().body(new Response("problems in notes",400,"Failed"));
	}
	
	@GetMapping("/getAllArchived")
	private ResponseEntity<Response>getAllArchivednotes(@RequestHeader("Authorization") String jwt)
	{
		if(notesservice.getAllArchived(jwt)!=null)
			return ResponseEntity.ok().body(new Response("Archived notes",200,notesservice.getAllArchived(jwt)));
		else
			return ResponseEntity.ok().body(new Response("Problems in notes",400,"Failed"));
	}
	
	
}
