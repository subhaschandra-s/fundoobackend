package com.bridgelabz.fundoonotes.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	@Modifying
	@Query(value= "insert into profile(profile_pic_name,user_id)values(:file,:id)", nativeQuery = true)
	int insert(String file, long id);

	@Query(value = "select profile_pic_name from profile where user_id=:id ",nativeQuery = true)
    String getProfile(int id);

	@Modifying
	@Query(value = "update profile set profile_pic_name=:filename where user_id=:id ",nativeQuery = true)
    void updateProfile(String filename, int id);
	
}
