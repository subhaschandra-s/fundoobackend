package com.bridgelabz.fundoonotes.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.bridgelabz.fundoonotes.model.Label;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.repository.LabelRepository;
import com.bridgelabz.fundoonotes.service.LabelService;
import com.bridgelabz.fundoonotes.utility.Jwt;

@Service
public class LabelImplementation implements LabelService
{
	@Autowired
	private Jwt jwtin;
	
	@Autowired
	private LabelRepository labelrepository;

	@Override
	public boolean savelabel(LabelDTO ldto, String jwt) throws Exception
	{
		UserInfo user=labelrepository.findOneByemailId(jwtin.extractemailId(jwt));
		if(jwtin.validatetoken(jwt) && user!=null )
		{
			Label label=new Label(ldto.getLname(),user);
			labelrepository.save(label);
			return true;
		}
		else
		 throw new Exception("your token is not valid");
	}

	@Override
	public boolean updatelabel(LabelDTO ldto, String jwt) throws Exception
	{
		UserInfo user=labelrepository.findOneByemailId(jwtin.extractemailId(jwt));
		if(jwtin.validatetoken(jwt) && user!=null)
		{
			Label label=labelrepository.getlabelbyid(ldto.getLid());
			label.setLname(ldto.getLname());
			label.setUserinfo(user);
			labelrepository.save(label);
			return true;
		}
		else
			throw new Exception("Token is not valid");
		
	}

	@Override
	public boolean deletelabel(int lid, String jwt) 
	{
		UserInfo user=labelrepository.findOneByemailId(jwtin.extractemailId(jwt));
		if(jwtin.validatetoken(jwt) && user!=null)
		{
			labelrepository.deletelabel(lid);
			return true;
		}
		
		return false;
	}

	@Override
	public Label getlabel(int lid, String jwt)
	{
		UserInfo user=labelrepository.findOneByemailId(jwtin.extractemailId(jwt));
		if(jwtin.validatetoken(jwt) && user!=null)
		{
			return labelrepository.getlabel(lid);
			
		}
		return null;
		
	}

	@Override
	public List<Label> getAllLabels(String jwt) 
	{
		UserInfo user=labelrepository.findOneByemailId(jwtin.extractemailId(jwt));
		if(jwtin.validatetoken(jwt) && user!=null)
		{
			List<Label> labels=labelrepository.getAllLabels(user.getId());
			return labels;
		}
		return null;	
		
	}

	@Override
	public List<Label> getLabelsbyNote(int id)
	{
		NotesInfo note=labelrepository.findByNote(id);
		List<Label> labels=note.getLabel();
		return labels;
	}
	
	

	
}
