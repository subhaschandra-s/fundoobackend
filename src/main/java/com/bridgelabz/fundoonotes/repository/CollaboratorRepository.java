package com.bridgelabz.fundoonotes.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Collaborator;
import com.bridgelabz.fundoonotes.model.NotesInfo;
import com.bridgelabz.fundoonotes.model.UserInfo;

@Repository
@Transactional
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>
{

	@Query(value="from UserInfo where emailId=?1")
	UserInfo findOneByemailId(String emailId);
	
	@Query("from NotesInfo where id=?1")
	NotesInfo findByNoteid(long id);

	@Modifying
	@Query(value="delete from collaborator where notes_id=?1 ",nativeQuery = true)
	int deleteCollaborator(long id);

	@Query(value="select * from collaborator where notes_id=:id ",nativeQuery = true)
	Collaborator getcollaborator(long id);

	
}
