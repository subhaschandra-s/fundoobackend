package com.bridgelabz.fundoonotes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO 
{

	private String firstname;
	private String lastname;
	private String emailId;
	private long mobileNo;
	private String password;
	private String password2;
		
	

}
