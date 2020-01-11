package com.bridgelabz.fundoonotes.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.Label;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserDAO;
import com.bridgelabz.fundoonotes.service.NotesService;
import com.bridgelabz.fundoonotes.utility.Jwt;

public class NotesServiceImplementation implements NotesService
{
	@Autowired
	private Jwt jwt;
	
	@Autowired
	private NoteRepository noterepository;
		
	@Autowired
	private UserDAO  userDAO;

	@Override
	public NotesInfo saveanote(NotesDTO notesDTO,String jwt1) 
	{
		
	   
	   
	  
	    return null;
		
		
	}
	
	public List<Label> getLabels(List<String> labelsdto,String jwt2)
	{
		int size=labelsdto.size();
		
		String emailId=jwt.extractemailId(jwt2);
		UserInfo user=noterepository.findOneByemailId(emailId);
		for(int i=0;i<size;i++)
		{
			if(noterepository.getLabels(labelsdto.get(i))==null)
			{
				noterepository.createLabel(labelsdto.get(i), user);
			}
		}
//		List<Label> labels=
		return null;
		   
	
		
		
		
	}

}

