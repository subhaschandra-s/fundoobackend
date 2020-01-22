package com.bridgelabz.fundoonotes.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.ProfileRepository;
import com.bridgelabz.fundoonotes.service.ProfileService;
import com.bridgelabz.fundoonotes.utility.Jwt;
import com.bridgelabz.fundoonotes.utility.S3Bucket;


@Service
public class ProfileServiceImplementation implements ProfileService
{
	
	@Autowired
	private ProfileRepository profilerepository;
	
	@Autowired
	private Jwt jwtin;
	
	@Autowired
	private S3Bucket s3bucket;


	@Override
	public boolean uploadprofile(MultipartFile multipartfile, String jwt) throws Exception 
	{
		UserInfo user=profilerepository.findOneByemailId(jwtin.extractemailId(jwt));
		if(user!=null)
		{
			profilerepository.insert(user.getId());
			s3bucket.uploadprofile(multipartfile);
		}
		return false;
	}

	

	public void updateprofile(MultipartFile file, String jwt) throws Exception
	{
		UserInfo user = profilerepository.findOneByemailId(jwtin.extractemailId(jwt));
		if(user!=null)
		{
			String filetodelete = profilerepository.getProfile(user.getId());
			profilerepository.updateProfile(file.getOriginalFilename(), user.getId());
			s3bucket.UpdateProfile(filetodelete, file, jwt);
		}
		else
			throw new Exception(" your profile is not found");
	
	}
			
   

	@Override
	public S3Object retriveprofile(String jwt)
	{
		UserInfo user =  profilerepository.findOneByemailId(jwtin.extractemailId(jwt));
		String filename = profilerepository.getProfile(user.getId());
		return s3bucket.myProfilePic(filename);
	}



	
    
}


