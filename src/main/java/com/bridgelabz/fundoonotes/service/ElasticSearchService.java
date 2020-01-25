package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.dto.NotesDTO;


public interface ElasticSearchService 
{
	
	String deletenote(long id) throws Exception;

	String createnote(NotesDTO note) throws Exception;

	Object updatenote(NotesDTO note) throws Exception;
}
