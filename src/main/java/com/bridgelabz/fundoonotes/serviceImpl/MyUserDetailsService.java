package com.bridgelabz.fundoonotes.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService
{
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserInfo user= userService.getUser(username); 
		return new User(username, user.getPassword(), new ArrayList<>());
	}

}
