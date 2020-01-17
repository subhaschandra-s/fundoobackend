package com.bridgelabz.fundoonotes.service;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.CollaboratorDTO;
import com.bridgelabz.fundoonotes.model.Collaborator;

@Component
public interface CollaboratorService 
{

	Collaborator addCollaborator(CollaboratorDTO collaboratorDTO, String jwt);

	boolean deleteCollaborator(int id, String jwt);

	Collaborator getcollaborator(int id, String jwt);

}
