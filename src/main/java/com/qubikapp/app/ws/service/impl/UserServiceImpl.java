package com.qubikapp.app.ws.service.impl;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.qubikapp.app.ws.entity.UserEntity;
import com.qubikapp.app.ws.exceptions.UserServiceException;
import com.qubikapp.app.ws.model.response.ErrorMessage;
import com.qubikapp.app.ws.model.response.ErrorMessages;
import com.qubikapp.app.ws.repository.UserRepository;
import com.qubikapp.app.ws.service.UserService;
import com.qubikapp.app.ws.shared.Utils;
import com.qubikapp.app.ws.shared.dto.AmazonSES;
import com.qubikapp.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		// provera da li vec postoji isti user
		UserEntity storedUserDetails = userRepository.findByEmail(userDto.getEmail());
		if (storedUserDetails != null)
			throw new RuntimeException("Record already exists");

		// TODO Auto-generated method stub
		
//		BeanUtils.copyProperties(userDto, userEntity);
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity=modelMapper.map(userDto, UserEntity.class);

		String publicUserId = utils.generateUserId(30);
		
		
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userEntity.setUserId(publicUserId);
		userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
		userEntity.setEmailVerificationStatus(false);
		UserEntity createdUser = userRepository.save(userEntity);
		
		
		
		UserDto returnValue =modelMapper.map(createdUser, UserDto.class);
		new AmazonSES().verifyEmail(returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity storedUser = userRepository.findByEmail(email);

		if (storedUser == null)
			throw new UsernameNotFoundException(email);

//		return new User(storedUser.getEmail(), storedUser.getEncryptedPassword(), new ArrayList<>());
		return new User(storedUser.getEmail(),storedUser.getEncryptedPassword(),storedUser.getEmailVerificationStatus(),true,true,true,new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity==null)throw new UsernameNotFoundException(email);
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity==null)throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getMessage());
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		
		return returnValue;
	}

	@Override
	public UserDto updateUser(UserDto userDto,String userId) {
		// TODO Auto-generated method stub
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity==null) {
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getMessage());
		}
		
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserDto returnDto = new UserDto();
		UserEntity returnValue=userRepository.save(userEntity);
		BeanUtils.copyProperties(returnValue, returnDto);
		return returnDto;
		
		
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		
		UserEntity user = userRepository.findByUserId(userId);
		if(user==null)
			throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getMessage());
		userRepository.delete(user);

	}

	@Override
	public boolean verifyEmailToken(String token) {
		// TODO Auto-generated method stub
		boolean returnValue = false;
		
		UserEntity userEntity = userRepository.findByEmailVerificationToken(token);
		if(userEntity!=null) {
			boolean hasTokenExpired= utils.hasTokenExpired(token);
			if(!hasTokenExpired) {
				userEntity.setEmailVerificationToken(null);
				userEntity.setEmailVerificationStatus(Boolean.TRUE);
				userRepository.save(userEntity);
				returnValue=true;
			}
		}
		return returnValue;
	}

}
