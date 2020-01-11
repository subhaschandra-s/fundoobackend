package com.bridgelabz.fundoonotes.dto;



import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginDTO 
{
	
	@NotNull(message = "Emailid should not be a null")
	private String emailId;

	@NotNull(message = "password should not be a null")
	private String password;

}
