package com.qubikapp.app.ws.service;

import java.util.List;

import com.qubikapp.app.ws.shared.dto.PolazakDto;

public interface PolazakService {
	List<PolazakDto> vratiSvePolaske(String mestoPolaskaId, String mestoDolaskaId, String prevoznikId, String datum);
	PolazakDto kreirajPolazak(PolazakDto polazak);
	PolazakDto updatePolazak(PolazakDto polazak);
	void deletePolazak(PolazakDto polazak);
}
