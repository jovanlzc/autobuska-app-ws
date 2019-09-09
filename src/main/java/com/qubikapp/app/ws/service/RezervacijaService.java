package com.qubikapp.app.ws.service;

import java.util.List;

import com.qubikapp.app.ws.shared.dto.RezervacijaDto;

public interface RezervacijaService {
	List<RezervacijaDto> vratiSveRezervacije(String userId,String aktivne);
	RezervacijaDto kreirajRezervaciju(RezervacijaDto rezervacija);
	RezervacijaDto izmeniRezervaciju(RezervacijaDto rezervacija,String rezervacijaId);
	void izbrisiRezervaciju(String id);
}
