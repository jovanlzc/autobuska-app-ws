package com.qubikapp.app.ws.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "autobuska_linija")
public class AutobuskaLinijaEntity {

	@Id
	@Column(name = "linija_id")
	private String linijaId;

	@ManyToOne
	@JoinColumn(name = "mesto_polaska")
	private MestoEntity mestoPolaska;
	@ManyToOne
	@JoinColumn(name = "mesto_dolaska")
	private MestoEntity mestoDolaska;
	private String naziv;
	@ManyToOne
	@JoinColumn(name = "prevoznik")
	private PrevoznikEntity prevoznik;

	@OneToMany(mappedBy = "linija",cascade = CascadeType.ALL)
	private List<PolazakEntity> polasci;

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

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public PrevoznikEntity getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(PrevoznikEntity prevoznik) {
		this.prevoznik = prevoznik;
	}
	@JsonIgnore
	public List<PolazakEntity> getPolasci() {
		return polasci;
	}

	public void setPolasci(List<PolazakEntity> polasci) {
		this.polasci = polasci;
	}

}
