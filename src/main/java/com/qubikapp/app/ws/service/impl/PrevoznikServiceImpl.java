package com.qubikapp.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubikapp.app.ws.entity.MestoEntity;
import com.qubikapp.app.ws.entity.PrevoznikEntity;
import com.qubikapp.app.ws.repository.PrevoznikRepository;
import com.qubikapp.app.ws.service.PrevoznikService;
import com.qubikapp.app.ws.shared.dto.MestoDto;
import com.qubikapp.app.ws.shared.dto.PrevoznikDto;

@Service
public class PrevoznikServiceImpl implements PrevoznikService{
	
	@Autowired
	private PrevoznikRepository prevoznikRepository;
	
	public List<PrevoznikDto> getAllPrevoznici(){
		Iterable<PrevoznikEntity> prevoznikIterable = prevoznikRepository.findAll();
		List<PrevoznikEntity> prevoznici = new ArrayList<>();
		prevoznikIterable.forEach(prevoznici::add);
		List<PrevoznikDto> returnValue = new ArrayList<>();
		for(PrevoznikEntity pe : prevoznici) {
			PrevoznikDto prevoznikDto = new PrevoznikDto();
			BeanUtils.copyProperties(pe, prevoznikDto);
			returnValue.add(prevoznikDto);
		}
		return returnValue;
	}
}
