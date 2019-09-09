package com.qubikapp.app.ws.shared;

import java.text.ParseException;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.modelmapper.PropertyMap;

import com.qubikapp.app.ws.entity.TipPolaska;
import com.qubikapp.app.ws.model.request.PolazakRequestModel;
import com.qubikapp.app.ws.shared.dto.PolazakDto;

public class PolazakDatumMapper extends PropertyMap<PolazakRequestModel, PolazakDto> {
	
	@Override
	protected void configure() {
		// TODO Auto-generated method stub
		String date= source.getDatum();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date1 = sdf.parse(date);
			Calendar datum = Calendar.getInstance();
			datum.setTime(date1);
			map().setDatum(datum);
			map().setTipPolaska(TipPolaska.values()[source.getTipPolaska()]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
