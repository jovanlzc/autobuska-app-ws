package com.qubikapp.app.ws.shared.dto;

import java.io.Serializable;
import java.util.List;

import com.qubikapp.app.ws.entity.MestoEntity;
import com.qubikapp.app.ws.entity.PolazakEntity;
import com.qubikapp.app.ws.entity.PrevoznikEntity;

public class LinijaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3983558119449214936L;
	private String linijaId;
	private MestoEntity mestoPolaska;
	private MestoEntity mestoDolaska;
	private PrevoznikEntity prevoznik;
	private String naziv;
	private List<PolazakDto> polasci;

	public String getLinijaId() {
		return linijaId;
	}

	public void setLinijaId(String linijaId) {
		this.linijaId = linijaId;
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

	public PrevoznikEntity getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(PrevoznikEntity prevoznik) {
		this.prevoznik = prevoznik;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<PolazakDto> getPolasci() {
		return polasci;
	}

	public void setPolasci(List<PolazakDto> polasci) {
		this.polasci = polasci;
	}

	

}
