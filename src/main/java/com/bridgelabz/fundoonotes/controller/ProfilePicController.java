package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.ProfileService;

@RequestMapping("/Profile")
@RestController
public class ProfilePicController
{

	@Autowired
    private ProfileService profileService;
	
	@PostMapping("/uploadprofile")
	private ResponseEntity<Response>UploadProfile(@RequestPart(value = "file")MultipartFile file,@RequestHeader("Authorization") String jwt) throws Exception
	{
		profileService.uploadprofile(file,jwt);
		return ResponseEntity.ok().body(new Response("Uploaded successfully",200,profileService.uploadprofile(file,jwt)));
	
	}
	
	@PutMapping("/updateprofile")
	private ResponseEntity<Response>Updateprofile(@RequestPart(value= "file")MultipartFile file,@RequestHeader("Authorization") String jwt) throws Exception
	{
		profileService.updateprofile(file,jwt);
		return ResponseEntity.ok().body(new Response("Uploaded successfully",200,profileService.uploadprofile(file,jwt)));
	}
	
	
	private ResponseEntity<Response>RetriveProfile(@RequestHeader("Authorization") String jwt)
	{
		if(profileService.retriveprofile(jwt)!=null)
		{
			
		}
		return null;
		
	}
}
