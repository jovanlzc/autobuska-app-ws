package com.qubikapp.app.ws.shared;

import org.modelmapper.PropertyMap;

import com.qubikapp.app.ws.model.request.LinijaRequestModel;
import com.qubikapp.app.ws.shared.dto.LinijaDto;

public class LinijaMapper extends PropertyMap<LinijaRequestModel, LinijaDto> {

	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		map().getMestoPolaska().setMestoId(source.getMestoPolaska());
		map().getMestoDolaska().setMestoId(source.getMestoDolaska());
		map().getPrevoznik().setId(source.getPrevoznik());
	}
}