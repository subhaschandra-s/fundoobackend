package com.bridgelabz.fundoonotes.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bridgelabz.fundoonotes.serviceImplementation.MyUserDetailsService;
import com.bridgelabz.fundoonotes.utility.Jwt;

@Component
public class Filter extends OncePerRequestFilter {
	@Autowired
	private Jwt jwtu;

	@Autowired
	private MyUserDetailsService mydetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");

		String emailId = null;
		if (authorizationHeader != null ) 
		{
			emailId = jwtu.extractemailId(authorizationHeader);
			
		}

		if (emailId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails user = this.mydetails.loadUserByUsername(emailId);
			if (jwtu.validateToken(authorizationHeader, user)) {
				
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,
						null, user.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
