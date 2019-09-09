package com.qubikapp.app.ws.model.request;

import java.util.Calendar;

import com.qubikapp.app.ws.entity.MestoEntity;
import com.qubikapp.app.ws.entity.TipPolaska;

public class PolazakRequestModel {
	private Long linijaId;
	private int brojMesta;
	private String datum;
	private int tipPolaska;

	

	public Long getLinijaId() {
		return linijaId;
	}

	public void setLinijaId(Long linijaId) {
		this.linijaId = linijaId;
	}

	public int getTipPolaska() {
		return tipPolaska;
	}

	public void setTipPolaska(int tipPolaska) {
		this.tipPolaska = tipPolaska;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	
}
