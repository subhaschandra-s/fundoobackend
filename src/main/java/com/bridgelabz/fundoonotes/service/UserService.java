package com.bridgelabz.fundoonotes.service;


import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.ForgotDTO;
import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.model.UserInfo;

@Component
public interface UserService 
{
	
UserInfo Register(UserDTO userdto);

public String Login(LoginDTO dto);

UserInfo getUser(String username);

public UserInfo getmail(ForgotDTO  forgotDTO);

public String sendmail(String emailId,String jwt);

public UserInfo activateuser(String jwt);


}
