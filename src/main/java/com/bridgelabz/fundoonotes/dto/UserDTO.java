package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.yaml.snakeyaml.emitter.Emitable;

import lombok.Data;

@Data
public class UserDTO 
{

	@NotNull(message = "First name should not be null")
	private String firstname;
	
	@NotNull(message = "Last name not be a null")
	private String lastname;
	
	@Pattern(regexp="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
	@NotNull(message = "Email should not be a null")
	private String emailId;
	
	@NotNull(message = "Mobile number not be a null")
	private long mobileNo;
	
	@NotNull(message = "Password not be a null")
	private String password;
	
	@NotNull(message = "Password not be a null")
	private String password2;
		
	

}
