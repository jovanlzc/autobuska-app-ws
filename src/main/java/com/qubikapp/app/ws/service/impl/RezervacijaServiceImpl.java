package com.qubikapp.app.ws.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qubikapp.app.ws.entity.RezervacijaEntity;
import com.qubikapp.app.ws.exceptions.RezervacijaServiceException;
import com.qubikapp.app.ws.model.response.ErrorMessages;
import com.qubikapp.app.ws.repository.RezervacijaRepository;
import com.qubikapp.app.ws.service.RezervacijaService;
import com.qubikapp.app.ws.shared.Utils;
import com.qubikapp.app.ws.shared.dto.PolazakDto;
import com.qubikapp.app.ws.shared.dto.RezervacijaDto;

@Service
public class RezervacijaServiceImpl implements RezervacijaService{
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	@Autowired
	private Utils utils;
	@Override
	public List<RezervacijaDto> vratiSveRezervacije(String userId,String aktivne) {
		// TODO Auto-generated method stub
		List<RezervacijaDto> returnValue = new ArrayList<>();
		Iterable<RezervacijaEntity> rezervacijeIterable = rezervacijaRepository.findAll();
		List<RezervacijaEntity> rezervacije = new ArrayList<>();
		rezervacijeIterable.forEach(rezervacije::add);
		ModelMapper modelMapper = new ModelMapper();
		for(RezervacijaEntity re: rezervacije) {
			System.out.println("Rezervacija-----------------");
			System.out.println("Aktivne?"+aktivne);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String sadasnjiDatum = sdf.format(new Date(Calendar.getInstance().getTimeInMillis()));
			String rezDatum = sdf.format(new Date(re.getDatum().getTimeInMillis()));
			System.out.println("Sadasnje vreme:"+sadasnjiDatum);
			System.out.println("Vreme rezervacije:"+rezDatum);
			System.out.println("Datum odgovara"+re.getDatum().after(Calendar.getInstance()));
			System.out.println("Tip polaska"+re.getPolazak().getTipPolaska().name());
			if(re.getUser().getUserId().equals(userId)) {
				if((aktivne.equals("aktivne")&&re.getDatum().after(Calendar.getInstance())) || (aktivne.equals("realizovane")&& re.getDatum().before(Calendar.getInstance()))) {
					RezervacijaDto rd=modelMapper.map(re, RezervacijaDto.class);
					returnValue.add(rd);
				}
			}
		}
		
		java.util.Collections.sort(returnValue, new Comparator<RezervacijaDto>() {

			@Override
			public int compare(RezervacijaDto r1, RezervacijaDto r2) {
				// TODO Auto-generated method stub
				return r1.getDatum().getTime().compareTo(r2.getDatum().getTime());

			}
		});
		return returnValue;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public RezervacijaDto kreirajRezervaciju(RezervacijaDto rezervacija) {
		
		// TODO Auto-generated method stub
		ModelMapper modelMapper = new ModelMapper();
		RezervacijaEntity rezervacijaEntity = modelMapper.map(rezervacija, RezervacijaEntity.class);
		String rezervacijaId = utils.generateUserId(30);
		rezervacijaEntity.setRezervacijaId(rezervacijaId);
		rezervacijaEntity = rezervacijaRepository.saveAndFlush(rezervacijaEntity);
		return modelMapper.map(rezervacijaEntity, RezervacijaDto.class);
		 
	}
	@Override
	public RezervacijaDto izmeniRezervaciju(RezervacijaDto rezervacija,String rezervacijaId) {
		// TODO Auto-generated method stub
		RezervacijaEntity rezervacijaEntity = rezervacijaRepository.findByRezervacijaId(rezervacijaId);
			if(rezervacijaEntity==null)throw new RuntimeException(rezervacijaId);
		rezervacijaEntity.setUser(rezervacija.getUser());
		rezervacijaEntity.setPolazak(rezervacija.getPolazak());
		RezervacijaEntity izmenjenaRezervacija = rezervacijaRepository.save(rezervacijaEntity);
		ModelMapper modelMapper = new ModelMapper();
		RezervacijaDto returnValue = modelMapper.map(izmenjenaRezervacija, RezervacijaDto.class);
		return returnValue;
	}
	@Override
	public void izbrisiRezervaciju(String id) {
		// TODO Auto-generated method stub
		RezervacijaEntity rezervacijaEntity = rezervacijaRepository.findByRezervacijaId(id);
		if(rezervacijaEntity==null)
			throw new RuntimeException(id);
		rezervacijaRepository.delete(rezervacijaEntity);
		
		
	}

}
