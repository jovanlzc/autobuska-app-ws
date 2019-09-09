package com.qubikapp.app.ws.shared.dto;

import java.io.Serializable;

public class MestoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2426054053378840981L;
	private Long mestoId;
	private String naziv;

	

	

	public Long getMestoId() {
		return mestoId;
	}

	public void setMestoId(Long mestoId) {
		this.mestoId = mestoId;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
