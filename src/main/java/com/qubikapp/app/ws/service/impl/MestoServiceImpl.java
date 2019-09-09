package com.qubikapp.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubikapp.app.ws.entity.MestoEntity;
import com.qubikapp.app.ws.repository.MestoRepository;
import com.qubikapp.app.ws.service.MestoService;
import com.qubikapp.app.ws.shared.dto.MestoDto;

@Service
public class MestoServiceImpl implements MestoService {
	@Autowired
	MestoRepository mestoRepository;
	
	@Override
	public List<MestoDto> getAllMesta() {
		// TODO Auto-generated method stub
		Iterable<MestoEntity> mestaIterable = mestoRepository.findAll();
		List<MestoEntity> mesta = new ArrayList<>();
		mestaIterable.forEach(mesta::add);
		List<MestoDto> returnValue = new ArrayList<>();
		for(MestoEntity me : mesta) {
			MestoDto mestoDto = new MestoDto();
			BeanUtils.copyProperties(me, mestoDto);
			returnValue.add(mestoDto);
		}
		return returnValue;
	}

	@Override
	public MestoDto getMesto(Long id) {
		// TODO Auto-generated method stub
		MestoEntity mestoEntity = mestoRepository.findByMestoId(id);
		MestoDto returnValue = new MestoDto();
		BeanUtils.copyProperties(mestoEntity, returnValue);
		return returnValue;
	}

}
