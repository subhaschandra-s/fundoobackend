package com.bridgelabz.fundoonotes.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;

@Component
public interface ProfileService 
{

	boolean uploadprofile(MultipartFile file, String jwt) throws Exception;

	void updateprofile(MultipartFile file, String jwt) throws Exception;

	S3Object retriveprofile(String jwt);
	
	
	
}
