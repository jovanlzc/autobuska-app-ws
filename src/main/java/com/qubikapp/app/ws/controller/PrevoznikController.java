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
import com.qubikapp.app.ws.model.response.PrevoznikRest;
import com.qubikapp.app.ws.service.MestoService;
import com.qubikapp.app.ws.service.PrevoznikService;
import com.qubikapp.app.ws.shared.dto.MestoDto;
import com.qubikapp.app.ws.shared.dto.PrevoznikDto;

@RestController
@RequestMapping("/prevoznici")
public class PrevoznikController {
	@Autowired
	PrevoznikService prevoznikService;
	
	@GetMapping(produces = {  MediaType.APPLICATION_JSON_VALUE })
	public List<PrevoznikRest> getAllMesta(){
		List<PrevoznikDto> prevoznici =prevoznikService.getAllPrevoznici(); 
		List<PrevoznikRest> returnValue = new ArrayList<>();
		
		for(PrevoznikDto p:prevoznici) {
			PrevoznikRest pr = new PrevoznikRest();
			BeanUtils.copyProperties(p, pr);
			returnValue.add(pr);
		}
		return returnValue;
	}
}
