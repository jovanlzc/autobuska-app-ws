package com.qubikapp.app.ws.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qubikapp.app.ws.entity.TipPolaska;
import com.qubikapp.app.ws.exceptions.UserServiceException;
import com.qubikapp.app.ws.model.request.PolazakRequestModel;
import com.qubikapp.app.ws.model.request.UserDetailsRequestModel;
import com.qubikapp.app.ws.model.response.ErrorMessages;
import com.qubikapp.app.ws.model.response.PolazakRest;
import com.qubikapp.app.ws.model.response.UserRest;
import com.qubikapp.app.ws.service.PolazakService;
import com.qubikapp.app.ws.shared.PolazakDatumMapper;
import com.qubikapp.app.ws.shared.dto.PolazakDto;
import com.qubikapp.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("/polasci")
public class PolazakController {
	@Autowired
	private PolazakService polazakService;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<PolazakRest> getAllPolasci(
			@RequestParam(value="mestoPolaska",required = false) String mestoPolaskaId,
			@RequestParam(value="mestoDolaska",required = false) String mestoDolaskaId,
			@RequestParam(value="prevoznik",required = false) String prevoznikId,
			@RequestParam(value="datum",required = false) String datum
			){
		
		List<PolazakRest> returnValue = new ArrayList<>();
		List<PolazakDto> polasci = polazakService.vratiSvePolaske(mestoPolaskaId, mestoDolaskaId, prevoznikId, datum);
		for(PolazakDto pd : polasci) {
			PolazakRest pr = new PolazakRest();
			BeanUtils.copyProperties(pd, pr);
			returnValue.add(pr);
		}
		return returnValue;
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public PolazakRest createUser(@RequestBody PolazakRequestModel polazak) throws Exception {

		if (polazak.getLinijaId()==0)
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getMessage());

//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userDetails, userDto);
		
		ModelMapper modelMapper = new ModelMapper();
		PolazakDto polazakDto = modelMapper.map(polazak, PolazakDto.class);
		String date1 = polazak.getDatum();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date2=sdf.parse(date1);
		Calendar datumPolaska = Calendar.getInstance();
		datumPolaska.setTime(date2);
		polazakDto.setDatum(datumPolaska);
		polazakDto.setTipPolaska(TipPolaska.values()[polazak.getTipPolaska()]);
		PolazakDto kreiraniPolazak = polazakService.kreirajPolazak(polazakDto);
//		BeanUtils.copyProperties(createdUser, returnValue);
		PolazakRest returnValue = modelMapper.map(kreiraniPolazak, PolazakRest.class);
		return returnValue;
	}
}
