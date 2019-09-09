package com.qubikapp.app.ws.shared.dto;

import java.io.Serializable;
import java.util.Calendar;

import com.qubikapp.app.ws.entity.PolazakEntity;
import com.qubikapp.app.ws.entity.UserEntity;

public class RezervacijaDto implements Serializable {
	private String rezervacijaId;
	private UserEntity user;
	private PolazakEntity polazak;
	private Calendar datum;

	public RezervacijaDto() {
	}

	public Calendar getDatum() {
		return datum;
	}

	public void setDatum(Calendar datum) {
		this.datum = datum;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public PolazakEntity getPolazak() {
		return polazak;
	}

	public void setPolazak(PolazakEntity polazak) {
		this.polazak = polazak;
	}

	public String getRezervacijaId() {
		return rezervacijaId;
	}

	public void setRezervacijaId(String rezervacijaId) {
		this.rezervacijaId = rezervacijaId;
	}

}
