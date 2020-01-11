package com.bridgelabz.fundoonotes.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
//@Entity
@Table(name= "label")
public class Label 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lid;

	private String lname;
	
	@ManyToMany(mappedBy = "label")
	private List<NotesInfo> notes;
	
	

}
