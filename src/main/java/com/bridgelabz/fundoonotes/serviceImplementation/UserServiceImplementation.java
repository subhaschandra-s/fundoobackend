 package com.bridgelabz.fundoonotes.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.configuration.RabbitMqSender;
import com.bridgelabz.fundoonotes.dto.ForgotDTO;
import com.bridgelabz.fundoonotes.dto.LoginDTO;
import com.bridgelabz.fundoonotes.dto.MailObject;
import com.bridgelabz.fundoonotes.dto.UserDTO;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.UserDAO;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utility.Jwt;
import com.bridgelabz.fundoonotes.utility.Utility;

@Service
public class UserServiceImplementation implements UserService 
{
	@Autowired
	private UserDAO userDAO;
		
	@Autowired
	private UserInfo user;
	

	@Autowired
	private Response res;

	@Autowired
	private Jwt jwtin;
	
	@Autowired
	private RabbitMqSender rabbitmqsender; 
	
	@Autowired
	private NotesServiceImplementation impl;
	
	@Autowired
	private JavaMailSender javaMailSender;

	public UserInfo Register(UserDTO userdto) 
	{
		user.setFirstname(userdto.getFirstname());
		
		user.setLastname(userdto.getLastname());
		user.setEmailId(userdto.getEmailId());
		user.setMobileNo(userdto.getMobileNo());
		user.setPassword(userdto.getPassword());
		user.setPassword(Utility.encoder(user.getPassword()));
		user.setCreatedate();
		
		userDAO.save(user);
		String jwt=res.getToken(user);
		sendmail(userdto.getEmailId(),jwt);
		return user;
	}

	@Override
	public String Login(LoginDTO dto)
	{
		UserInfo u=userDAO.findOneByemailId(dto.getEmailId());
		
		MailObject mailobj=new MailObject();
		mailobj.setEmail(dto.getEmailId());
		mailobj.setResponse(res.getToken(u));
		mailobj.setObject("hello");
		rabbitmqsender.send(mailobj);
		
		if(u!=null)
		{
			if(u.getEmailId().equals(dto.getEmailId()) && Utility.passwordMatcher(dto.getPassword(), u.getPassword()))
			{				
				String jwt= res.getToken(u);
			     impl.getRedisId(jwt);
				return jwt;
			}
		}
		return null;
		
	}

	@Override
	public UserInfo getUser(String username) {
		return userDAO.findOneByemailId(username);
	}

	@Override
	public UserInfo getmail(ForgotDTO forgotDTO) 
	{
		UserInfo u=userDAO.findOneByemailId(forgotDTO.getEmailId());
		String jwt=res.getToken(u);
		sendmail(forgotDTO.getEmailId(),jwt);
		return u;	
	}

	@Override
	public String sendmail(String emailId,String jwt) 
	{
		SimpleMailMessage msg=new SimpleMailMessage();
		
		msg.setTo(emailId);
		
		msg.setText(jwt);
		
		javaMailSender.send(msg);
		
		return "email sent to your mail";
		
	}
	
	

	@Override
	public UserInfo activateuser(String jwt) 
	{
		String emailId=jwtin.extractemailId(jwt);
	
		if(userDAO.findOneByemailId(emailId)!=null)
		{
			
			userDAO.setverifiedEmail(emailId);
		}
		return user;
					
	}

	
}
