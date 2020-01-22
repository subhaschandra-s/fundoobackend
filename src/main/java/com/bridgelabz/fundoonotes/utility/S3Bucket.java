package com.bridgelabz.fundoonotes.utility;

import java.io.File;
import java.io.FileOutputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Component
public class S3Bucket 
{
	
	AmazonS3 s3client;
	
	@Value("${aws.access_key_id}")
	  private String accessKey;
	 
	  @Value("${aws.secret_access_key}")
	  private String secretKey;
	  
	 
	  @Value("${s3.bucket}")
	  private String bucketName;
	  
	  
	  @SuppressWarnings("deprecation")
	  @PostConstruct
		private void initializeAmazon()
	  {
			AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
			this.s3client = new AmazonS3Client(credentials);
		}
	  
	  
	  
	  private File convertMultiPartToFile(MultipartFile file) throws Exception 
	  {
			File convFile = new File(file.getOriginalFilename());
			FileOutputStream fos = new FileOutputStream(convFile);
			fos.write(file.getBytes());
			fos.close();
			return convFile;
		}



  public void uploadprofile( MultipartFile multipartFile) throws Exception
  {
	
	File file = convertMultiPartToFile(multipartFile);
	s3client.putObject(new PutObjectRequest(bucketName,multipartFile.getOriginalFilename(), file));
	
  }
  
  
  public void UpdateProfile(String filetodelete, MultipartFile multipartFile,String jwt) throws Exception
  {
	
	File file = convertMultiPartToFile(multipartFile);
	s3client.deleteObject(new DeleteObjectRequest(bucketName,filetodelete));
	s3client.putObject(new PutObjectRequest(bucketName,multipartFile.getOriginalFilename(), file));
	
 }

  public S3Object myProfilePic(String file)
  {
	 return s3client.getObject(new GetObjectRequest(bucketName,file));
   }


}

