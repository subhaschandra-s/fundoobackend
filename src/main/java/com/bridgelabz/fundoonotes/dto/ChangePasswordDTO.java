package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ChangePasswordDTO 
{
	@NotNull
    private String password;
	
	@NotNull
	private String confirmpassword;
	

	
	
}
