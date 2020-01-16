package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.NotesInfo;

@Component
public interface NotesService 
{
	
	boolean saveanote(NotesDTO notesDTO,String jwt1) throws Exception;

	boolean deletenote(int id);

	boolean update(NotesDTO notesDTO,String jwt2) throws Exception;

	NotesInfo getnote(int id);

	List<NotesInfo> getAllnotes(String jwt);

	Object getAllPinned(String jwt1);

	Object getAllArchived(String jwt);
	
	
}

