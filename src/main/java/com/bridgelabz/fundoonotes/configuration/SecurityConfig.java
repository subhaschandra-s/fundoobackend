package com.bridgelabz.fundoonotes.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bridgelabz.fundoonotes.filter.Filter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private Filter filter;
	
	protected void  configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		.authorizeRequests().antMatchers("/user/register").permitAll()
		.antMatchers("/user/verification").permitAll()
		.antMatchers("/user/forgot").permitAll()
		.antMatchers("/user/modify").permitAll()
		.antMatchers("/user/login").permitAll()
		.anyRequest().authenticated()
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
		
	}

}
