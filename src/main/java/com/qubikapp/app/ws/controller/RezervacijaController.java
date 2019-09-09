package com.qubikapp.app.ws.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.qubikapp.app.ws.model.request.RezervacijaRequestModel;
import com.qubikapp.app.ws.model.request.UserDetailsRequestModel;
import com.qubikapp.app.ws.model.response.ErrorMessages;
import com.qubikapp.app.ws.model.response.OperationStatusModel;
import com.qubikapp.app.ws.model.response.RequestOperationName;
import com.qubikapp.app.ws.model.response.RequestOperationStatus;
import com.qubikapp.app.ws.model.response.RezervacijaRest;
import com.qubikapp.app.ws.model.response.UserRest;
import com.qubikapp.app.ws.service.RezervacijaService;
import com.qubikapp.app.ws.shared.RezervacijaIdMapper;
import com.qubikapp.app.ws.shared.dto.RezervacijaDto;
import com.qubikapp.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("/rezervacije")
public class RezervacijaController {
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 public List<RezervacijaRest> vratiSveRezervacije(
			 @RequestParam(value="userId",required = false) String userId,
				@RequestParam(value="aktivne",required = false) String aktivne){
		 List<RezervacijaDto> rezervacije = rezervacijaService.vratiSveRezervacije(userId,aktivne);
		 List<RezervacijaRest> returnValue = new ArrayList<>();
		 ModelMapper modelMapper = new ModelMapper();
//		 modelMapper.addMappings(new RezervacijaIdMapper());
		 for(RezervacijaDto rd: rezervacije) {
			 RezervacijaRest rr=modelMapper.map(rd, RezervacijaRest.class);
			 returnValue.add(rr);
		 }
		 return returnValue;
	 }
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public RezervacijaRest kreirajRezervaciju(@RequestBody RezervacijaRequestModel rezervacija) throws Exception {

		
//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userDetails, userDto);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		RezervacijaDto rezervacijaDto = modelMapper.map(rezervacija, RezervacijaDto.class);
		String date1 = rezervacija.getDatum();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dateTime = DateTime.parse(date1, formatter);
		Calendar datumPolaska = Calendar.getInstance();
		Date date2= dateTime.toDate();
		datumPolaska.setTime(date2);
		rezervacijaDto.setDatum(datumPolaska);
		RezervacijaDto kreiranaRezervacija = rezervacijaService.kreirajRezervaciju(rezervacijaDto);
		RezervacijaRest returnValue = modelMapper.map(kreiranaRezervacija, RezervacijaRest.class);
		return returnValue;
	}
	
	@PutMapping(path="/{id}",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public RezervacijaRest izmeniRezervaciju(@RequestBody RezervacijaRequestModel rezervacija,@PathVariable String id) throws Exception{
		RezervacijaRest returnValue =null;
		ModelMapper modelMapper = new ModelMapper();
		RezervacijaDto rezervacijaDto = modelMapper.map(rezervacija, RezervacijaDto.class);
		rezervacijaDto = rezervacijaService.izmeniRezervaciju(rezervacijaDto, id);
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public OperationStatusModel izbrisiRezervaciju(@PathVariable String id) {
		OperationStatusModel osm = new OperationStatusModel();
		osm.setOperationName(RequestOperationName.DELETE.name());
		rezervacijaService.izbrisiRezervaciju(id);
		osm.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return osm;
	}
}
