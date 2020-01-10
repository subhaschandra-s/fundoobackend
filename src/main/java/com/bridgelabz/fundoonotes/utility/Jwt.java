package com.bridgelabz.fundoonotes.utility;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.model.UserInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Jwt {
	private String SECRET_KEY = "secret";

	public String generateToken(UserInfo user) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, user.getEmailId());
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().addClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public boolean validateToken(String token, UserDetails user) {
		final String emailId = extractemailId(token);
		return (emailId.equals(user.getUsername()) && !isTokenExpired(token));
	}

	public String extractemailId(String token) {

		if (token.startsWith("Bearer "))
		{

			token = token.substring(7);
			return extractClaim(token, Claims::getSubject);

		}

		return null;
	}

	private Date extractExpiration(String token) {

		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);

		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

}
