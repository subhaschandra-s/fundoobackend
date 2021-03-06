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

import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.bridgelabz.fundoonotes.model.Label;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.LabelService;

@RequestMapping("/label")
@RestController
public class LabelController 
{
	@Autowired
	private LabelService labelservice;
	
	@PostMapping("/savelabel")
	public ResponseEntity<Response>savelabel(@RequestBody LabelDTO ldto,@RequestHeader("Authorization")String jwt) throws Exception
	{
		if(labelservice.savelabel(ldto, jwt))
			return ResponseEntity.ok().body(new Response("Successfull",200,ldto));
		else
			return ResponseEntity.ok().body(new Response("problem in label",400,"Error"));
		
	}
	
	@PutMapping("/updatelabel")
	public ResponseEntity<Response>updatlabel(@RequestBody LabelDTO ldto,@RequestHeader("Authorization")String jwt) throws Exception
	{
		if(labelservice.updatelabel(ldto,jwt))
			return ResponseEntity.ok().body(new Response("updated label",200,ldto));
		else
			return ResponseEntity.ok().body(new Response("Problem occured",400,"Failss"));
	}
	
	@DeleteMapping("/deletelabel")
	public ResponseEntity<Response>deletelabel(@RequestParam("labelid")int lid,@RequestHeader("Authorization")String jwt)
	{
		if(labelservice.deletelabel(lid,jwt))
			return ResponseEntity.ok().body(new Response("deleted label",200,"Done"));
		else
			return ResponseEntity.ok().body(new Response("Problem occuredd",400,"Failedd"));
	}
	
	@GetMapping("/getlabel")
	public ResponseEntity<Response>getlabel(@RequestParam("labelid")int lid,@RequestHeader("Authorization") String jwt)
	{
		Label label=labelservice.getlabel(lid,jwt);
		if(label!=null)
			return ResponseEntity.ok().body(new Response("got label by id",200,label));
		else
			return ResponseEntity.ok().body(new Response("Problem in labels",400,"Faileddd"));	
	}
	
	@GetMapping("/getAllLabels")
	public ResponseEntity<Response>getAllLabels(@RequestHeader("Authorization") String jwt)
	{
		if(labelservice.getAllLabels(jwt) != null)
			return ResponseEntity.ok().body(new Response("All labels availble",200,labelservice.getAllLabels(jwt)));
		else
			return ResponseEntity.ok().body(new Response("Problem in label",400,"Failed"));	
	}
	
	@GetMapping("/getLabelsbyNote")
	public ResponseEntity<Response> getLabelsbyNote(@RequestParam("id") int id,@RequestHeader("Authorization") String jwt)
	{
		if(labelservice.getLabelsbyNote(id) != null)
			return ResponseEntity.ok().body(new Response("All labels availble",200,labelservice.getLabelsbyNote(id)));
		else
			return ResponseEntity.ok().body(new Response("Problem in label",400,"Failed"));
	}

}
