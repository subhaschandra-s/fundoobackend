package com.bridgelabz.fundoonotes.serviceImplementation;

import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.service.ElasticSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticSearchServiceImplementation implements ElasticSearchService
{
	
	@Autowired
	private RestHighLevelClient config;
	
	@Autowired
	private NoteRepository noterepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	private static final String INDEX= "elasticsearch";
	
	private static final String TYPE = "note";

	@Override
	public String createnote(NotesDTO note) throws Exception 
	{
		@SuppressWarnings("unchecked")
		Map<String , Object> notemapper = mapper.convertValue(note, Map.class);
		IndexRequest indexRequest= new IndexRequest(INDEX,TYPE,String.valueOf(note.getId())).source(notemapper);
		IndexResponse indexResponse=config.index(indexRequest, RequestOptions.DEFAULT);
		if(indexResponse!=null)
		{
		return indexResponse.getResult().name();
		}
		else 
			return null;
	}

	@Override
	public Object updatenote(NotesDTO note) throws Exception
	{
		@SuppressWarnings("unchecked")
		Map<String, Object> notemapper=mapper.convertValue(note, Map.class);
		UpdateRequest request=new UpdateRequest(INDEX,TYPE,String.valueOf(note.getId())).doc(notemapper);
		UpdateResponse updateResponse=config.update(request, RequestOptions.DEFAULT);
		if(updateResponse!=null)
			return  updateResponse.getResult().name();
		else
		    return null;
	}

	@Override
	public String deletenote(long id) throws Exception
	{
		NotesInfo note= noterepository.getnotesbyid(id);
		DeleteRequest deleteRequest=new DeleteRequest(INDEX,TYPE,String.valueOf(note.getId()));
		DeleteResponse deleteResponse=config.delete(deleteRequest, RequestOptions.DEFAULT);
		if(deleteResponse!=null)
			return deleteResponse.getResult().name();
		else
		return null;
	}

}
