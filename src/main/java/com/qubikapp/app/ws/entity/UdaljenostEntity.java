package com.qubikapp.app.ws.entity;

import javax.persistence.Entity;

public class UdaljenostEntity {
	private Long udaljenostId;
	private MestoEntity mestoPolaska;
	private MestoEntity mestoDolaska;
	private double udaljenost;
	private String naziv;
	public Long getUdaljenostId() {
		return udaljenostId;
	}
	public void setUdaljenostId(Long udaljenostId) {
		this.udaljenostId = udaljenostId;
	}
	public MestoEntity getMestoPolaska() {
		return mestoPolaska;
	}
	public void setMestoPolaska(MestoEntity mestoPolaska) {
		this.mestoPolaska = mestoPolaska;
	}
	public MestoEntity getMestoDolaska() {
		return mestoDolaska;
	}
	public void setMestoDolaska(MestoEntity mestoDolaska) {
		this.mestoDolaska = mestoDolaska;
	}
	public double getUdaljenost() {
		return udaljenost;
	}
	public void setUdaljenost(double udaljenost) {
		this.udaljenost = udaljenost;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
	
}
