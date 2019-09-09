package com.qubikapp.app.ws.shared;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.qubikapp.app.ws.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET= "0123456789ABCDEFGHIKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}

	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for(int i=0;i<length;i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		// TODO Auto-generated method stub
		return new String(returnValue);
	}

	public boolean hasTokenExpired(String token) {
		// TODO Auto-generated method stub
		Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getToken()).parseClaimsJws(token).getBody();
		
		Date tokenExpirationDate= claims.getExpiration();
		Date today = new Date();
		
		return tokenExpirationDate.before(today);
	}

	public String generateEmailVerificationToken(String publicUserId) {
		// TODO Auto-generated method stub
		String token = Jwts.builder().setSubject(publicUserId).setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512,SecurityConstants.getToken()).compact();
		return token;
	}
}
