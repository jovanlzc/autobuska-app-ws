package com.qubikapp.app.ws.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.domain.Persistable;
/**
 * Klasa koja predstavlja rezervisani polazak od strane korisnika.
 * @author Jovan Lazic
 * @version 1.0
 * @see PolazakEntity
 * @see UserEntity
 */
@Entity(name = "rezervacija")
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"user_id","datum","polazak_id"})
})
public class RezervacijaEntity implements Serializable{

//	@EmbeddedId
//	private RezervacijaId id;

	/**
	 * 
	 */
	private static final long serialVersionUID = -8113344194702055186L;
	/**
	 * Jedinstveni identifikator rezervacije kao String.
	 */
	@Id
	@Column(name="id")
	private String rezervacijaId;
	/**
	 * Korisnik koji rezervise polazak kao UserEntity.
	 */
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	/**
	 * Polazak koji rezervisan od strane korisnika kao PolazakEntity.
	 */
	@ManyToOne
	@JoinColumn(name="polazak_id")
	private PolazakEntity polazak;
	@Column(name="datum",nullable = false)
	/**
	 * Datum i vreme rezervacije polaska od strane korisnika kao Calendar.
	 */
	private Calendar datum;
	
	
	public RezervacijaEntity() {
	}
	
	/**
	 * Inicijalizacija rezervacije.
	 * @param user Korisnik koji vrsi rezervaciju polaska.
	 * @param polazak Polazak koji se rezervise od strane korsinika.
	 */
	public RezervacijaEntity(UserEntity user, PolazakEntity polazak) {
		this.user = user;
		this.polazak = polazak;
	}

	
	/**
	 * Metoda koja vraca jedinstveni identifikator rezervacije.
	 * @return rezervacijaId kao String.
	 */
	public String getRezervacijaId() {
		return rezervacijaId;
	}
	/**
	 * Metoda koja postavlja novu vrednost jedinstvenog identifikatora rezervacije/
	 * @param rezervacijaId nova vrednost za jedinstveni identifikator rezervacije.
	 */
	public void setRezervacijaId(String rezervacijaId) {
		this.rezervacijaId = rezervacijaId;
	}
	/**
	 * Metoda koja vraca korisnika koji rezervise izabrani polazak.
	 * @return user kao UserEntity
	 */
	public UserEntity getUser() {
		return user;
	}
	/**
	 * Metoda koja postavlja novog korisnika koji rezervise izabrani polazak.
	 * @param user novi korisnik koji rezervise izabrani polazak.
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}
	/**
	 * Metoda koja vraca polazak koji je rezervisan od strane korisnika
	 * @return polazak kao PolazakEntity
	 */
	public PolazakEntity getPolazak() {
		return polazak;
	}
	/*
	 * Metoda koja postavlja novi polazak koji se rezervise od strane korisnika.
	 * @param polazak Novi polazak koji se rezervise od strane korisnika.
	 */
	public void setPolazak(PolazakEntity polazak) {
		this.polazak = polazak;
	}
	/*
	 * Metoda koja vracam datum rezervacije
	 * @return datum kao Calendar
	 */
	public Calendar getDatum() {
		return datum;
	}
	/*
	 * Metoda koja postavlja novu vrednost za datum rezervacije. 
	 * @param datum nova vrednost za datum rezervacije.
	 */
	public void setDatum(Calendar datum) {
		this.datum = datum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		RezervacijaEntity that = (RezervacijaEntity) o;
		return Objects.equals(user, that.user) && Objects.equals(polazak, that.polazak);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user, polazak);
	}

	

}
