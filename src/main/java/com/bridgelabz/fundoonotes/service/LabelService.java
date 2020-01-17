package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.LabelDTO;
import com.bridgelabz.fundoonotes.model.Label;

@Component
public interface LabelService 
{
	boolean savelabel(LabelDTO ldto,String jwt) throws Exception;

	boolean updatelabel(LabelDTO ldto, String jwt) throws Exception;

	boolean deletelabel(int lid, String jwt);

	Label getlabel(int lid, String jwt);

	List<Label> getAllLabels(String jwt);

	List<Label> getLabelsbyNote(int id);

}
