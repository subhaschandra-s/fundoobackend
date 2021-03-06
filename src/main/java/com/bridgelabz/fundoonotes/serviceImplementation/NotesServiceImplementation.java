package com.bridgelabz.fundoonotes.serviceImplementation;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.NotesDTO;
import com.bridgelabz.fundoonotes.model.Label;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.service.NotesService;
import com.bridgelabz.fundoonotes.utility.Jwt;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class NotesServiceImplementation implements NotesService {
	
	@Autowired
	private Jwt jwt;

	@Autowired
	private NoteRepository noterepository;
	
	@Autowired
	private RedisTemplate<String, Object>redis;

	@Override
	public boolean saveanote(NotesDTO notesDTO, String jwt1) throws Exception 
	{
		
		UserInfo user = noterepository.findOneByemailId(jwt.extractemailId(jwt1));
		if (jwt.validatetoken(jwt1) && user != null) 
		{
			int notesid;
			try {
				notesid = noterepository.giveMaxId() + 1;
			} catch (Exception e) {
				notesid = 1;
			}
			List<Label> labels = getLabels(notesDTO.getLabel(), jwt1);
			NotesInfo notes = new NotesInfo(notesDTO.getTitle(),notesDTO.getTakeanote(),notesDTO.getReminderstatus(),notesDTO.getColor(),labels,user);
			noterepository.save(notes);
			return true;
		} else {
			throw new Exception("your token is not valid");
		}
	}

	public List<Label> getLabels(List<String> labelsdto, String jwt2) {
		int size = labelsdto.size();
		String emailId = jwt.extractemailId(jwt2);
		UserInfo user = noterepository.findOneByemailId(emailId);
		for (int i = 0; i < size; i++) 
		{
			Label label=noterepository.getLabels(labelsdto.get(i));
			if (label == null) {
				noterepository.createLabel(labelsdto.get(i), user);
			}
		}
		List<Label> labels = labelsdto.stream().map(s -> noterepository.findLabelByName(s)).collect(Collectors.toList());

		return labels;
	}

	@Override
	public boolean deletenote(int id) 
	{
		noterepository.delete(id);
		return true;
	}

	@Override
	public boolean update(NotesDTO notesDTO, String jwt2) throws Exception 
	{
		String userid=getRedisId(jwt2);
		UserInfo user1=noterepository.findOneByemailId(userid);	
		if(jwt.validatetoken(jwt2) && user1!=null )
		{
			List<Label> labels=getLabels(notesDTO.getLabel(),jwt2);
			NotesInfo note=noterepository.getnotesbyid(notesDTO.getId());
			note.setTitle(notesDTO.getTitle());
			note.setTakeanote(notesDTO.getTakeanote());
			note.setReminder(notesDTO.getReminderstatus());
			note.setColor(notesDTO.getColor());
			note.setLabel(labels);
			note.setUserinfo(user1);
		    note.setPinned(true);
		    note.setArchived(true);
		    noterepository.save(note);
			return true;
		}
		else
		
		throw new Exception("token is not valid");
	}

	public String getRedisId(String token)
	{
		if(redis.opsForValue().get(token)==null)
		{
			String idForRedis=jwt.extractemailId(token);
			log.info("idforRedis is "+idForRedis);
			redis.opsForValue().set(token, idForRedis, 3 * 60, TimeUnit.SECONDS);	
		}
		return (String) redis.opsForValue().get(token);
			
	}
	

	@Override
	public NotesInfo getnote(int id) 
	{
		return noterepository.getNotes(id);		
		
	}

	@Override
	public List<NotesInfo> getAllnotes(String jwt1,long id)
	{
		
		UserInfo user=noterepository.findOneByemailId(jwt.extractemailId(jwt1));
		List<NotesInfo> notes=noterepository.getAllnotes(user.getId(),(id*10-10));
		return notes;
	}

	@Override
	public Object getAllPinned(String jwt1) 
	{
		UserInfo user=noterepository.findOneByemailId(jwt.extractemailId(jwt1));
		List<NotesInfo> ob=noterepository.getAllPinned(user.getId());
		return ob;
	}

	@Override
	public Object getAllArchived(String jwt1)
	{
	UserInfo user=noterepository.findOneByemailId(jwt.extractemailId(jwt1));
	List<NotesInfo> no=noterepository.getAllArchived(user.getId());
		return no;
	}

	@Override
	public Object getAllTrashed(String jwt1)
	{
		UserInfo user=noterepository.findOneByemailId(jwt.extractemailId(jwt1));
		List<NotesInfo> no=noterepository.getAllTrashed(user.getId());
		return no;
	}

}
