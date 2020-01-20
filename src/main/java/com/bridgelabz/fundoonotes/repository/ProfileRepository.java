package com.bridgelabz.fundoonotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Profile;
import com.bridgelabz.fundoonotes.model.UserInfo;

@Repository
public interface ProfileRepository  extends JpaRepository<Profile, Long>
{

	@Query(value="from UserInfo where emailId=?1")
	UserInfo findOneByemailId(String emailId);
	
}
