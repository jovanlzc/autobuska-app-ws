package com.qubikapp.app.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qubikapp.app.ws.model.response.MestoRest;
import com.qubikapp.app.ws.service.MestoService;
import com.qubikapp.app.ws.shared.dto.MestoDto;

@RestController
@RequestMapping("/mesta")
public class MestoController {
	@Autowired
	MestoService mestoService;
	
	@GetMapping(produces = {  MediaType.APPLICATION_JSON_VALUE })
	public List<MestoRest> getAllMesta(){
		List<MestoDto> mesta =mestoService.getAllMesta(); 
		List<MestoRest> returnValue = new ArrayList<>();
		
		for(MestoDto m:mesta) {
			MestoRest mr = new MestoRest();
			BeanUtils.copyProperties(m, mr);
			returnValue.add(mr);
		}
		return returnValue;
	}
	
}
