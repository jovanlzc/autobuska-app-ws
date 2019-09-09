package com.qubikapp.app.ws.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qubikapp.app.ws.entity.AutobuskaLinijaEntity;
import com.qubikapp.app.ws.entity.TipPolaska;
import com.qubikapp.app.ws.model.request.LinijaRequestModel;
import com.qubikapp.app.ws.model.request.PolazakRequestModel;
import com.qubikapp.app.ws.model.response.OperationStatusModel;
import com.qubikapp.app.ws.model.response.RequestOperationStatus;
import com.qubikapp.app.ws.service.LinijaService;
import com.qubikapp.app.ws.shared.LinijaMapper;
import com.qubikapp.app.ws.shared.dto.LinijaDto;
import com.qubikapp.app.ws.shared.dto.PolazakDto;

@RestController
@RequestMapping("/linije")
public class AutobuskaLinijaController {
	@Autowired
	private LinijaService linijaService;
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public AutobuskaLinijaEntity createAutobuskaLinija(@RequestBody LinijaRequestModel linijaRequest) {
		OperationStatusModel osm = new OperationStatusModel();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new LinijaMapper());
		
		LinijaDto linijaDto = modelMapper.map(linijaRequest, LinijaDto.class);
		modelMapper= new ModelMapper();
		List<PolazakDto> polasci = new ArrayList<>();
		for(PolazakRequestModel prm: linijaRequest.getPolasci()) {
			
			PolazakDto pd = modelMapper.map(prm, PolazakDto.class);
			String date1 = prm.getDatum();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			Date date2;
			try {
				date2 = sdf.parse(date1);
				Calendar datumPolaska = Calendar.getInstance();
				datumPolaska.setTime(date2);
				pd.setDatum(datumPolaska);
				pd.setTipPolaska(TipPolaska.values()[prm.getTipPolaska()]);
				polasci.add(pd);
			} catch (ParseException e) {
			
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		linijaDto.setPolasci(polasci);
		return linijaService.createLinija(linijaDto);
//		osm.setOperationResult(RequestOperationStatus.SUCCESS.name());
	}
}
