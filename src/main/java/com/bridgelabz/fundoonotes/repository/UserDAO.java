package com.bridgelabz.fundoonotes.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.UserInfo;

@Repository
@Transactional
public interface UserDAO extends JpaRepository<UserInfo, Integer>
{
	@Query(value="select * from user_info where email_id= ?1", nativeQuery = true)
	UserInfo findOneByemailId(String emailId);
	
	@Modifying
	@Query(value="update user_info set isverified=true where email_id=:emailId", nativeQuery = true)
	int setverifiedEmail(String emailId);

	@Modifying
	@Query(value="update user_info set password=:confirmpassword where email_id=:emailId", nativeQuery = true)
	int setpassword(String emailId,String confirmpassword);
	

}