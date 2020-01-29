package com.bridgelabz.fundoonotes.serviceImplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.configuration.ElasticSearchConfiguration;
import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.service.ElasticSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticSearchServiceImplementation implements ElasticSearchService
{
	
	@Autowired
	private ElasticSearchConfiguration config;
	
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
		IndexResponse indexResponse=config.client().index(indexRequest, RequestOptions.DEFAULT);
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
		UpdateResponse updateResponse=config.client().update(request, RequestOptions.DEFAULT);
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
		DeleteResponse deleteResponse=config.client().delete(deleteRequest, RequestOptions.DEFAULT);
		if(deleteResponse!=null)
			return deleteResponse.getResult().name();
		else
		    return null;
	}

	@Override
	public List<NotesInfo> searchByTitle(String title) throws Exception 
	{
		SearchRequest searchRequest= new SearchRequest("elasticsearch");
		SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("title", title));
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse=config.client().search(searchRequest, RequestOptions.DEFAULT);
		if(searchResponse!=null)
		    return  getResult(searchResponse);
		else
			return null;
	}

	private List<NotesInfo> getResult(SearchResponse searchresponse) 
	{
		SearchHit[] searchhits = searchresponse.getHits().getHits();
		List<NotesInfo> notes = new ArrayList<>();
		if (searchhits.length > 0) 
		{
			Arrays.stream(searchhits)
					.forEach(hit -> notes.add(mapper.convertValue(hit.getSourceAsMap(), NotesInfo.class)));
		}
		return notes;
	}
}
