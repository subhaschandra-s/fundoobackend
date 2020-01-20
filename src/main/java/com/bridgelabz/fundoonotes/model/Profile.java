package com.bridgelabz.fundoonotes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Profile 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ProfilePicName;
	
	@OneToOne
	private UserInfo user;

	public Profile(String ProfilPicName ,UserInfo user)
	{
		this.ProfilePicName=ProfilPicName;
		this.user=user;
	}
}
