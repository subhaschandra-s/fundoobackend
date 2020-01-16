package com.bridgelabz.fundoonotes.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.ForgotDTO;
import com.bridgelabz.fundoonotes.dto.LoginDTO;
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
	private UserInfo us;
	

	@Autowired
	private Response res;
	
	@Autowired
	private Jwt jwtin;
	
	
	@Autowired
	private JavaMailSender javaMailSender;

	public UserInfo Register(UserDTO userdto) 
	{
		us.setFirstname(userdto.getFirstname());
		
		us.setLastname(userdto.getLastname());
		us.setEmailId(userdto.getEmailId());
		us.setMobileNo(userdto.getMobileNo());
		us.setPassword(userdto.getPassword());
		us.setPassword(Utility.encoder(us.getPassword()));
		us.setCreatedate();
		
		userDAO.save(us);
		String jwt=res.getToken(us);
		sendmail(userdto.getEmailId(),jwt);
		 
		
		return us;
	}

	@Override
	public String Login(LoginDTO dto)
	{
		
		UserInfo u=userDAO.findOneByemailId(dto.getEmailId());
		

		if(u!=null)
		{
			if(u.getEmailId().equals(dto.getEmailId()) && Utility.passwordMatcher(dto.getPassword(), u.getPassword()))
			{				
				String s= res.getToken(u);
			
				return s;
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
		return us;
					
	}

	
		
	
	
}
