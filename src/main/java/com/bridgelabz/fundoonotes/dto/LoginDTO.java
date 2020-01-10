package com.bridgelabz.fundoonotes.dto;

import javax.persistence.Column;

import com.sun.istack.NotNull;

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
public class LoginDTO 
{
	@NotNull
	@Column(unique = true)
	private String emailId;
	
	@NotNull
	private String password;

}
