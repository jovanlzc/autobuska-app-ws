package com.qubikapp.app.ws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "mesto")
public class MestoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mesto_id")
	private Long mestoId;
	@Column(name = "naziv")
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
