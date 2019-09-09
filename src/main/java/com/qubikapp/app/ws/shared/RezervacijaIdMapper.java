package com.qubikapp.app.ws.shared;

import org.modelmapper.PropertyMap;

import com.qubikapp.app.ws.model.response.RezervacijaRest;
import com.qubikapp.app.ws.shared.dto.RezervacijaDto;

public class RezervacijaIdMapper extends PropertyMap<RezervacijaDto, RezervacijaRest>{

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		skip().setRezervacijaId("");
	}

}
