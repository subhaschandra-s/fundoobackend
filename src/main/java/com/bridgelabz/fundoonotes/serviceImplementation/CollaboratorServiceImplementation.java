package com.bridgelabz.fundoonotes.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.CollaboratorDTO;
import com.bridgelabz.fundoonotes.model.Collaborator;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.CollaboratorRepository;
import com.bridgelabz.fundoonotes.service.CollaboratorService;
import com.bridgelabz.fundoonotes.utility.Jwt;

@Service
public class CollaboratorServiceImplementation implements CollaboratorService
{
	@Autowired
	private CollaboratorRepository repo;
	
	@Autowired
	private Jwt jwtn;

	@Override
	public Collaborator addCollaborator(CollaboratorDTO collaboratorDTO, String jwt) 
	{
		UserInfo user=repo.findOneByemailId(jwtn.extractemailId(jwt));
		if(!user.getEmailId().equals(collaboratorDTO.getCollaborator()))
		{
			NotesInfo note=repo.findByNoteid(collaboratorDTO.getId());
			Collaborator collaborator=new Collaborator(collaboratorDTO.getCollaborator(),note);
			repo.save(collaborator);
			return collaborator;
		}
		
		return null;
	}

	@Override
	public boolean deleteCollaborator(int id, String jwt)
	{
		UserInfo user=repo.findOneByemailId(jwtn.extractemailId(jwt));
		if(jwtn.validatetoken(jwt) && user!=null)
		{
			NotesInfo note=repo.findByNoteid(id);
			repo.deleteCollaborator(note.getId());
			return true;
		}
		return false;
	}

	@Override
	public Collaborator getcollaborator(int id, String jwt)
	{
		UserInfo user=repo.findOneByemailId(jwtn.extractemailId(jwt));
		if(jwtn.validatetoken(jwt) && user!=null)
		{
			NotesInfo note=repo.findByNoteid(id);
			return repo.getcollaborator(note.getId());
		}	
		return null;
	}

}
