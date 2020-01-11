package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.NotesInfo;

public interface NotesService 
{
	
	NotesInfo saveanote(NotesDTO notesDTO,String jwt1);

	//boolean deleteOneNote(long id,String token);
	
	
}
