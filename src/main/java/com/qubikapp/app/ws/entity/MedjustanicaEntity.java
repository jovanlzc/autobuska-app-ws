package com.qubikapp.app.ws.entity;

import java.util.Calendar;

import javax.persistence.Entity;

public class MedjustanicaEntity {
	private int rbr;
	private MestoEntity mesto;
	private AutobuskaLinijaEntity linija;
	private double cena;
	private Calendar vremePutovanja;
	public int getRbr() {
		return rbr;
	}
	public void setRbr(int rbr) {
		this.rbr = rbr;
	}
	public MestoEntity getMesto() {
		return mesto;
	}
	public void setMesto(MestoEntity mesto) {
		this.mesto = mesto;
	}
	public AutobuskaLinijaEntity getLinija() {
		return linija;
	}
	public void setLinija(AutobuskaLinijaEntity linija) {
		this.linija = linija;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public Calendar getVremePutovanja() {
		return vremePutovanja;
	}
	public void setVremePutovanja(Calendar vremePutovanja) {
		this.vremePutovanja = vremePutovanja;
	}
	
	
}
