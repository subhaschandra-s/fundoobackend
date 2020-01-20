package com.bridgelabz.fundoonotes.serviceImplementation;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bridgelabz.fundoonotes.model.Profile;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.ProfileRepository;
import com.bridgelabz.fundoonotes.service.ProfileService;
import com.bridgelabz.fundoonotes.utility.Jwt;


@Service
public class ProfileServiceImplementation implements ProfileService
{
	
	@Autowired
	private ProfileRepository profilerepository;
	
	@Autowired
	private Jwt jwtin;

	 @Value("${s3.bucket}")
	 private String bucketName;
	 
	 private AmazonS3Client s3client;
	 
	 @Value("${aws.access_key_id}")
     private String awsId;
		 
	 @Value("${aws.secret_access_key}")
	 private String awsKey;
	
	   @PostConstruct
		public void initializeAmazon()
		{
		    AWSCredentials awsCredentials=new BasicAWSCredentials(this.awsId, this.awsKey);
			this.s3client=new  AmazonS3Client(awsCredentials);
		}

	@Override
	public boolean uploadprofile(MultipartFile multipartfile, String jwt) throws Exception 
	{
		UserInfo user=profilerepository.findOneByemailId(jwtin.extractemailId(jwt));
		if(user!=null)
		{
			File file=convertMultipartFileToFile(multipartfile);
			String filename=generatefilename(multipartfile);
		    uploadfileTos3Bucket(filename,file);
			
		}
		return false;
	}

	private void uploadfileTos3Bucket(String filename, File file) 
	{
		s3client.putObject(new PutObjectRequest(bucketName, filename, file));
		
	}

	private String generatefilename(MultipartFile multipartfile) 
	{
		return new Date().getTime() + "-" +multipartfile.getOriginalFilename().replace(" ", "_");
	}

	private File convertMultipartFileToFile(MultipartFile multipartfile) throws Exception
	{
		File convfile=new File(multipartfile.getOriginalFilename());
		FileOutputStream fos=new FileOutputStream(convfile);
		fos.write(multipartfile.getBytes());
		fos.close();
		return convfile;
	}

	
			
	}


