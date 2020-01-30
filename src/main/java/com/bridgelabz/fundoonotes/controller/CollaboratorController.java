package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.CollaboratorDTO;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.CollaboratorService;

@RequestMapping("/collaborator")
@RestController
public class CollaboratorController
{
	@Autowired
	private CollaboratorService collaboratorService;
	
	@PostMapping("/addcollaborator")
	public ResponseEntity<Response> addCollaborator(@RequestBody CollaboratorDTO collaboratorDTO,@RequestHeader("Authorization") String jwt)
	{

		if(collaboratorService.addCollaborator(collaboratorDTO, jwt) !=null)
			return ResponseEntity.ok().body(new Response("Successfull",200,collaboratorDTO));
		else
			return ResponseEntity.ok().body(new Response("problem occurs",400,"Failed"));
		
	}

	@DeleteMapping("/deletecollaborator")
	public ResponseEntity<Response>deleteCollaborator(@RequestParam("id")int id,@RequestHeader("Authorization")String jwt)
	{
		if(collaboratorService.deleteCollaborator(id,jwt))
			return ResponseEntity.ok().body(new Response("Successfull",200,collaboratorService.deleteCollaborator(id,jwt)));
		else
			return ResponseEntity.ok().body(new Response("problem occurs",400,"Failed"));
    }
	
	@GetMapping("/getcollaborator")
	public ResponseEntity<Response>getCollaborator(@RequestParam("id")int id,@RequestHeader("Authorization")String jwt)
	{
		if(collaboratorService.getcollaborator(id,jwt) != null)
			return ResponseEntity.ok().body(new Response("Successfull",200,collaboratorService.getcollaborator(id,jwt)));
		else
			return ResponseEntity.ok().body(new Response("problem occurs",400,"Failed"));	
		
	}
	
}
