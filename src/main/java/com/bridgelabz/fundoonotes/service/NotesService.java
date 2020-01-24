package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.NotesInfo;

@Service
public interface NotesService 
{
	
	public boolean saveanote(NotesDTO notesDTO,String jwt1) throws Exception;

	public boolean deletenote(int id);

	public boolean update(NotesDTO notesDTO,String jwt2) throws Exception;

	public NotesInfo getnote(int id);

	public List<NotesInfo> getAllnotes(String jwt);

	public Object getAllPinned(String jwt1);

	public Object getAllArchived(String jwt);
	
	
}

