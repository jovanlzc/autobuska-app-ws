package com.qubikapp.app.ws.model.request;

public class RezervacijaRequestModel {
	private String datum;
	private String userId;
	private String polazakId;

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPolazakId() {
		return polazakId;
	}

	public void setPolazakId(String polazakId) {
		this.polazakId = polazakId;
	}

	
	
}
