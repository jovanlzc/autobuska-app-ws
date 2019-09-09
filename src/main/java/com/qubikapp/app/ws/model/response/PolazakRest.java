package com.qubikapp.app.ws.model.response;

import java.util.Calendar;

import com.qubikapp.app.ws.entity.AutobuskaLinijaEntity;
import com.qubikapp.app.ws.entity.TipPolaska;

public class PolazakRest {
	private String polazakId;
	private AutobuskaLinijaEntity linija;
	private Calendar datum;
	private TipPolaska tipPolaska;
	
	public AutobuskaLinijaEntity getLinija() {
		return linija;
	}
	public void setLinija(AutobuskaLinijaEntity linija) {
		this.linija = linija;
	}
	public Calendar getDatum() {
		return datum;
	}
	public void setDatum(Calendar datum) {
		this.datum = datum;
	}
	public TipPolaska getTipPolaska() {
		return tipPolaska;
	}
	public void setTipPolaska(TipPolaska tipPolaska) {
		this.tipPolaska = tipPolaska;
	}
	public String getPolazakId() {
		return polazakId;
	}
	public void setPolazakId(String polazakId) {
		this.polazakId = polazakId;
	}
	
	
}
