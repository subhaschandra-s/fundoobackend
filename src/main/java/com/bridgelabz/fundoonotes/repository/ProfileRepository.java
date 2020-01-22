package com.bridgelabz.fundoonotes.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Profile;
import com.bridgelabz.fundoonotes.model.UserInfo;

@Repository
@Transactional
public interface ProfileRepository  extends JpaRepository<Profile, Long>
{

	@Query(value="from UserInfo where emailId=?1")
	UserInfo findOneByemailId(String emailId);

	@Query(value= "insert into profile()")
	int insert(int id);

	String getProfile(int id);

	void updateProfile(String originalFilename, int id);
	
}
