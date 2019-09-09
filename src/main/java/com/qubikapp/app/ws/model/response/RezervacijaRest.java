package com.qubikapp.app.ws.model.response;

import java.util.Calendar;

public class RezervacijaRest {
	private String rezervacijaId;
	private UserRest user;
	private PolazakRest polazak;
	private Calendar datum;

	public RezervacijaRest() {
	}

	public RezervacijaRest(String rezervacijaId, UserRest user, PolazakRest polazak, Calendar datum) {
		this.rezervacijaId = rezervacijaId;
		this.user = user;
		this.polazak = polazak;
		this.datum = datum;
	}

	public String getRezervacijaId() {
		return rezervacijaId;
	}

	public void setRezervacijaId(String rezervacijaId) {
		this.rezervacijaId = rezervacijaId;
	}

	public UserRest getUser() {
		return user;
	}

	public void setUser(UserRest user) {
		this.user = user;
	}

	public PolazakRest getPolazak() {
		return polazak;
	}

	public void setPolazak(PolazakRest polazak) {
		this.polazak = polazak;
	}

	public Calendar getDatum() {
		return datum;
	}

	public void setDatum(Calendar datum) {
		this.datum = datum;
	}

}
