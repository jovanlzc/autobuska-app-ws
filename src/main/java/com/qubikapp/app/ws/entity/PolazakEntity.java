package com.qubikapp.app.ws.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "polazak")
public class PolazakEntity {
	@Id
	@Column(name="polazak_id")
	private String polazakId;
	@Column(name="datum")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datum;
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="linija_id")
	@JsonIgnoreProperties("polasci")
	private AutobuskaLinijaEntity linija;
	@Enumerated(EnumType.ORDINAL)
	private TipPolaska tipPolaska;
	


	public Calendar getDatum() {
		return datum;
	}

	public void setDatum(Calendar datum) {
		this.datum = datum;
	}
	
	public AutobuskaLinijaEntity getLinija() {
		return linija;
	}

	public void setLinija(AutobuskaLinijaEntity linija) {
		this.linija = linija;
	}

	public TipPolaska getTipPolaska() {
		return tipPolaska;
	}

	public void setTipPolaska(TipPolaska tipPolaska) {
		this.tipPolaska = tipPolaska;
	}

	public String getPolazakId() {
		return polazakId;
	}

	public void setPolazakId(String polazakId) {
		this.polazakId = polazakId;
	}

}
