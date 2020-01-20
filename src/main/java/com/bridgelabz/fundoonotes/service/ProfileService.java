package com.bridgelabz.fundoonotes.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface ProfileService 
{

	boolean uploadprofile(MultipartFile file, String jwt) throws Exception;
	
	
	
}
