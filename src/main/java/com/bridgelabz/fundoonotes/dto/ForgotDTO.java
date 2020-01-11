package com.bridgelabz.fundoonotes.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ForgotDTO 
{
@NotNull(message = "Email should not be a null")
private String emailId;

}
