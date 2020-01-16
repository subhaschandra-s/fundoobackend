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
public interface LabelRepository extends JpaRepository<Label, Integer>
{

	@Query(value="from UserInfo where emailId=?1")
	UserInfo findOneByemailId(String emailId);

	@Query("from Label where lid=?1")
	Label getlabelbyid(int lid);

	@Modifying
	@Query(value="delete from label where lid=?1 ",nativeQuery = true)
	int deletelabel(int lid);

	@Query(value="select * from label where lid= ?1 ",nativeQuery = true)
	Label getlabel(int lid);

	@Query(value="select * from label where userinfo_id=:id ",nativeQuery = true)
	List<Label> getAllLabels(int id);

	@Query("from NotesInfo where id=:?1")
	List<NotesInfo> getNoteId(long id);

	@Query(value="select * from note where id=:id ",nativeQuery = true)
	List<Label> getLabelsbyNote(long id);

	
}
