package com.qubikapp.app.ws.model.request;

import java.util.List;

public class LinijaRequestModel {
	private String naziv;
	private Long mestoPolaska;
	private Long mestoDolaska;
	private Long prevoznik;
	private List<PolazakRequestModel> polasci;
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Long getMestoPolaska() {
		return mestoPolaska;
	}
	public void setMestoPolaska(Long mestoPolaska) {
		this.mestoPolaska = mestoPolaska;
	}
	public Long getMestoDolaska() {
		return mestoDolaska;
	}
	public void setMestoDolaska(Long mestoDolaska) {
		this.mestoDolaska = mestoDolaska;
	}
	public Long getPrevoznik() {
		return prevoznik;
	}
	public void setPrevoznik(Long prevoznik) {
		this.prevoznik = prevoznik;
	}
	public List<PolazakRequestModel> getPolasci() {
		return polasci;
	}
	public void setPolasci(List<PolazakRequestModel> polasci) {
		this.polasci = polasci;
	}
	
	
	
	
}
