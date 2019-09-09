package com.qubikapp.app.ws.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qubikapp.app.ws.exceptions.UserServiceException;
import com.qubikapp.app.ws.model.request.UserDetailsRequestModel;
import com.qubikapp.app.ws.model.response.ErrorMessages;
import com.qubikapp.app.ws.model.response.OperationStatusModel;
import com.qubikapp.app.ws.model.response.RequestOperationName;
import com.qubikapp.app.ws.model.response.RequestOperationStatus;
import com.qubikapp.app.ws.model.response.UserRest;
import com.qubikapp.app.ws.service.UserService;
import com.qubikapp.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest getUser(@PathVariable String userId) {

		UserRest returnValue = new UserRest();
		UserDto storedUser = userService.getUserByUserId(userId);
		BeanUtils.copyProperties(storedUser, returnValue);
		return returnValue;
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

		if (userDetails.getFirstName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getMessage());

//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userDetails, userDto);

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
//		BeanUtils.copyProperties(createdUser, returnValue);
		UserRest returnValue = modelMapper.map(createdUser, UserRest.class);
		return returnValue;
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public UserRest updateUser(@RequestBody UserDetailsRequestModel userDetails, @PathVariable String id) {

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);

		UserDto createdUser = userService.updateUser(userDto, id);
//		BeanUtils.copyProperties(createdUser, returnValue);
		UserRest returnValue = modelMapper.map(createdUser, UserRest.class);
		return returnValue;
	}

	@DeleteMapping(path="/{id}",produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		userService.deleteUser(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;

	}
	
	@GetMapping(path = "/email-verification", produces = {MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel verifyEmailToken(@RequestParam(value="token") String token) {

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());
		
		boolean isVerified = userService.verifyEmailToken(token);
		if(isVerified) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}else {
			returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		}
		return returnValue;
	}
}
