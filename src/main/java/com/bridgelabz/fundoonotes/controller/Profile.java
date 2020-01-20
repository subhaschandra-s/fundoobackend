package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.ProfileService;

@RequestMapping("/Profile")
@RestController
public class Profile
{

	@Autowired
    private ProfileService profileService;
	
	@PostMapping("/uploadprofile")
	private ResponseEntity<Response>Uploadpic(@RequestPart(value = "file")MultipartFile file,@RequestHeader("Authorization") String jwt) throws Exception
	{
		if(profileService.uploadprofile(file,jwt))
		return ResponseEntity.ok().body(new Response("Uploaded",200,profileService.uploadprofile(file,jwt)));
		else
		return ResponseEntity.ok().body(new Response("problem occurs",400,"Failed"));
	
	}
}
