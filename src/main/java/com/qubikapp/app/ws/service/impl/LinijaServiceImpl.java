package com.qubikapp.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.qubikapp.app.ws.entity.AutobuskaLinijaEntity;
import com.qubikapp.app.ws.entity.PolazakEntity;
import com.qubikapp.app.ws.repository.LinijaRepository;
import com.qubikapp.app.ws.service.LinijaService;
import com.qubikapp.app.ws.shared.Utils;
import com.qubikapp.app.ws.shared.dto.LinijaDto;
import com.qubikapp.app.ws.shared.dto.PolazakDto;

@Service
public class LinijaServiceImpl implements LinijaService{
	@Autowired
	private LinijaRepository linijaRepository;
	@Autowired
	private Utils utils;
	@Override
	public LinijaDto getLinija(String id) {
		// TODO Auto-generated method stub
		AutobuskaLinijaEntity linijaEntity = linijaRepository.findByLinijaId(id);
		if(linijaEntity==null)
			throw new RuntimeException(id);
		ModelMapper modelMapper = new ModelMapper();
		LinijaDto linijaDto=modelMapper.map(linijaEntity, LinijaDto.class);
		return linijaDto;
	}
	@Override
	public AutobuskaLinijaEntity createLinija(LinijaDto linijaDto) {
		// TODO Auto-generated method stub
		ModelMapper modelMapper = new ModelMapper();
		AutobuskaLinijaEntity linija = modelMapper.map(linijaDto, AutobuskaLinijaEntity.class);
		List<PolazakEntity> polasci = new ArrayList<>();
		linija.setLinijaId(utils.generateUserId(30));
		for(PolazakDto pd : linijaDto.getPolasci()) {
			PolazakEntity pe = modelMapper.map(pd, PolazakEntity.class);
			pe.setDatum(pd.getDatum());
			pe.setLinija(linija);
			String polazakId = utils.generateUserId(30);
			pe.setPolazakId(polazakId);
			polasci.add(pe);
			
		}
		linija.setPolasci(polasci);
		AutobuskaLinijaEntity al=linijaRepository.save(linija);
		return al;
	}
	

}
