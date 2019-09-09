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

	@Id
	@Column(name="id")
	private String rezervacijaId;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	@ManyToOne
	@JoinColumn(name="polazak_id")
	private PolazakEntity polazak;
	@Column(name="datum",nullable = false)
	private Calendar datum;
	
	
	public RezervacijaEntity() {
	}

	public RezervacijaEntity(UserEntity user, PolazakEntity polazak) {
		this.user = user;
		this.polazak = polazak;
	}

	

	public String getRezervacijaId() {
		return rezervacijaId;
	}

	public void setRezervacijaId(String rezervacijaId) {
		this.rezervacijaId = rezervacijaId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public PolazakEntity getPolazak() {
		return polazak;
	}

	public void setPolazak(PolazakEntity polazak) {
		this.polazak = polazak;
	}

	public Calendar getDatum() {
		return datum;
	}

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
