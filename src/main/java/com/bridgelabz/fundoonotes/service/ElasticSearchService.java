package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.NotesInfo;


public interface ElasticSearchService 
{
	
	String deletenote(long id) throws Exception;

	String createnote(NotesDTO note) throws Exception;

	Object updatenote(NotesDTO note) throws Exception;

	List<NotesInfo> searchByTitle(String title) throws Exception;
}
