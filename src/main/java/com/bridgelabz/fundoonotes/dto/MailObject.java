package com.bridgelabz.fundoonotes.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MailObject implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String Object;
	private String response;
}
