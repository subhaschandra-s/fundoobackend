package com.bridgelabz.fundoonotes.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoonotes.dto.ChangePasswordDTO;
import com.bridgelabz.fundoonotes.dto.ForgotDTO;
import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.UserDAO;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utility.Jwt;
import com.bridgelabz.fundoonotes.utility.Utility;

@RequestMapping("/user")
@RestController
public class UserController 
{
	@Autowired
	private Jwt jn;
	
	
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserDAO repo;

	@GetMapping("/hi")
	public void getHi() 
	{
		
	}

	
	@PostMapping("/register")
	private ResponseEntity<Response> register(@Valid @RequestBody UserDTO userdto,BindingResult bindingResult )
	{
		if (bindingResult.hasErrors()) 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response( "Registration issues",400,bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())));
		}
		else if (userdto.getPassword().equals(userdto.getPassword2())) 
		{
	     	
		    UserInfo user =service.Register(userdto);
			user.setPassword("*****");
			 return user!=null
					? ResponseEntity.status(HttpStatus.CREATED)
							.body(new Response("registration successfull", 200, user))
					: ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
							.body(new Response("user already exist", 400, userdto));
		}
		return null;
		
		}
	
	
	@PostMapping("/login")
	private ResponseEntity<Response> login(@Valid @RequestBody LoginDTO dto, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response("Login issues",400,bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())));
		}
		else
		{
			
		String user=service.Login(dto);
		
		if(user!=null) 
		{
		    UserInfo user1=repo.findOneByemailId(dto.getEmailId());

		    return user1!= null
		    		? ResponseEntity.status(HttpStatus.CREATED)
							.body(new Response("login successfull", 200, user))
					: ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
							.body(new Response("user already exist", 400, dto));
		}
		}
		return null;
		
		
	}
	
	
	
	
	@RequestMapping("/verification")
	private ResponseEntity<Response> Userverification(@RequestParam String jwt,HttpServletRequest req)
	{
		UserInfo user= service.activateuser(jwt);
		
		return user != null 
				? ResponseEntity.status(HttpStatus.OK).body(new Response("verified", 200, user))
				: ResponseEntity.status(HttpStatus.OK).body(new Response("not verified", 400, user));
		
	}
	
	@PostMapping("/forgot")
	private ResponseEntity<Response> forgot(@Valid @RequestBody ForgotDTO forgotdto,BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) 
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response("server issues",400,bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList())));
		}
		else 
		{
			UserInfo user=service.getmail(forgotdto);
        	user.setPassword("*****");
			return user!= null
					? ResponseEntity.status(HttpStatus.OK).body(new Response("Successfull", 200, user))
							: ResponseEntity.status(HttpStatus.OK).body(new Response("Failed", 400, forgotdto));
		}
		
		
	}
	
	
	@PutMapping("/modify")
	private ResponseEntity<Response> Updatepassword(@RequestBody ChangePasswordDTO changedto,HttpServletRequest request)
	{
		String jwt=request.getHeader("header");
		if(changedto.getPassword().equals(changedto.getConfirmpassword()))
		{
			String pass=(Utility.encoder(changedto.getPassword()));
	     	String emailId=jn.extractemailId(jwt);
			
			if(repo.findOneByemailId(emailId)!=null)
			{
			int n1= repo.setpassword(emailId, pass);
			
		    if(n1!=0)
			return ResponseEntity.status(HttpStatus.OK).body(new Response("verified", 200, "updated successfully"));
			else
			return ResponseEntity.status(HttpStatus.OK).body(new Response("not verified", 400, "not updated"));
					
			}
			
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(new Response("Failed", 200, "Password Incorrect"));
	}
}
