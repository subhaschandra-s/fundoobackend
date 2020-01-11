package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Label;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.model.UserInfo;

@Repository
public interface NoteRepository extends JpaRepository<NotesInfo,Long>
{

	@Query(value="select * from user_info where email_id= ?1", nativeQuery = true)
	UserInfo findOneByemailId(String emailId);
	
	@Query(value ="select * from label where label =?1", nativeQuery = true)
	Label getLabels(String label);
	
	@Query(value= "")
	@Modifying
	Integer createLabel(String newLabel,UserInfo user);
	
	@Query("from Label where label=?1")
	Label findLabelByName(String label);
}
