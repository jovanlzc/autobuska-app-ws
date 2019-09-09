package com.qubikapp.app.ws.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qubikapp.app.ws.SpringApplicationContext;
import com.qubikapp.app.ws.model.request.UserLoginRequestModel;
import com.qubikapp.app.ws.service.impl.UserServiceImpl;
import com.qubikapp.app.ws.shared.dto.UserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub

		try {
			UserLoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(),
					UserLoginRequestModel.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String username = ((User)auth.getPrincipal()).getUsername();
		
		String token = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512,SecurityConstants.getToken()).compact();
		
		UserServiceImpl userService = (UserServiceImpl)SpringApplicationContext.getBean("userServiceImpl");
		
		UserDto userDto = userService.getUser(username);
		
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+token);
		response.addHeader("UserID", userDto.getUserId());
	}
}
