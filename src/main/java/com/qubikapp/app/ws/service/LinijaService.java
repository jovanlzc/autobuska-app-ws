package com.qubikapp.app.ws.service;

import com.qubikapp.app.ws.entity.AutobuskaLinijaEntity;
import com.qubikapp.app.ws.shared.dto.LinijaDto;

public interface LinijaService {
	LinijaDto getLinija(String id);
	AutobuskaLinijaEntity createLinija(LinijaDto linijaDto);
}
