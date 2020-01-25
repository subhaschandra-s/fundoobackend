package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.service.ElasticSearchService;

@RestController
public class ElasticController 
{

	@Autowired
	private ElasticSearchService service;
	
	@GetMapping("/note/getelastic")
	public void getelastic(@RequestBody NotesDTO dto) throws Exception
	{
		if(service.createnote(dto)!=null)
		System.out.println("successfull");
		else
			System.out.println("failed");
	}
	
	@PutMapping("/note/updateelastic")
	public void updateelastic(@RequestBody NotesDTO dto) throws Exception
	{
		if(service.updatenote(dto)!=null)
			System.out.println("successfull");
		else
			System.out.println("failed");
	}
	
	@DeleteMapping("/note/deleteElastic")
	public void deleteElastic(@RequestParam("id") long id) throws Exception
	{
		if(service.deletenote(id) != null)
			System.out.println("succss");
		else
			System.out.println("asbxcjkas");
	}
}
