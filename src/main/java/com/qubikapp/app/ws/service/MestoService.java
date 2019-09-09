package com.qubikapp.app.ws.service;

import java.util.List;

import com.qubikapp.app.ws.shared.dto.MestoDto;

public interface MestoService {
	List<MestoDto> getAllMesta();
	MestoDto getMesto(Long id);
}
