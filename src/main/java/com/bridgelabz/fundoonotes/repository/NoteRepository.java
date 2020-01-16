package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Label;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.model.UserInfo;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<NotesInfo,Long>
{

	@Query(value="from UserInfo where emailId=?1")
	UserInfo findOneByemailId(String emailId);
	
	@Query("from Label where lname=?1") 
	Label getLabels(String lname);
	
	@Modifying
	@Query(value= "insert into label(lname,userinfo_id) values(:newLabel,:user)",nativeQuery = true)
	int createLabel(String newLabel,UserInfo user);
	
	@Query("from Label where lname=?1")
	Label findLabelByName(String lname);
	
	@Query(value="select * from note where id=?1" ,nativeQuery = true)
	NotesInfo getNotes(int id);
	
	
	@Query(value = "select max(id) from note",nativeQuery = true)
	Integer giveMaxId();
	
	@Modifying
	@Query(value="delete from note where id=?1", nativeQuery = true)
	int delete(int id);

	@Query(value="select * from note where userinfo_id=:id",nativeQuery = true)
	List<NotesInfo> getAllnotes(long id);

	@Query("from NotesInfo where id=?1")
	NotesInfo getnotesbyid(long id);

	@Query(value="select * from note where is_pinned=true and userinfo_id=:id",nativeQuery= true)
	List<NotesInfo> getAllPinned(long id);

	@Query(value="select * from note where is_archived=true and userinfo_id=:id", nativeQuery = true)
	List<NotesInfo> getAllArchived(long id);

}
