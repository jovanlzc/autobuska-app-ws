package com.qubikapp.app.ws.shared.dto;

import java.io.Serializable;
import java.util.Calendar;

import com.qubikapp.app.ws.entity.AutobuskaLinijaEntity;
import com.qubikapp.app.ws.entity.TipPolaska;

public class PolazakDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4848765903090226071L;
	private String polazakId;
	private AutobuskaLinijaEntity linija;
	private Calendar datum;
	private TipPolaska tipPolaska;

	


	public String getPolazakId() {
		return polazakId;
	}

	public void setPolazakId(String polazakId) {
		this.polazakId = polazakId;
	}

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

}
