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
/**
 * Klasa predstavlja polazak autobuske linije
 * @author Jovan Lazic
 * @version 1.0
 * @see AutobuskaLinijaEntity
 */
@Entity(name = "polazak")
public class PolazakEntity {
	/**
	 * Jedinstveni identifikator polaska kao String.
	 */
	@Id
	@Column(name="polazak_id")
	private String polazakId;
	/**
	 * Datum i vreme polaska kao Calendar.
	 */
	@Column(name="datum")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datum;
	/**
	 * Autobuska linija kao AutobuskaLinijaEntity na koju se polazak odnosi.
	 */
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="linija_id")
	@JsonIgnoreProperties("polasci")
	private AutobuskaLinijaEntity linija;
	/**
	 * Tip polaska kao TipPolaska,moze biti svakodnevno, radni dan, vikend, specificno
	 */
	@Enumerated(EnumType.ORDINAL)
	private TipPolaska tipPolaska;
	

	
	public PolazakEntity() {
	}

	/**
	 * Inicijalizacija polaska.
	 * @param polazakId Jedinstveni identifikator polaska.
	 * @param datum Datum i vreme polaska.
	 * @param linija Autobuska linija na koju se polazak odnosi.
	 * @param tipPolaska Tip polaska.
	 */
	public PolazakEntity(String polazakId, Calendar datum, AutobuskaLinijaEntity linija, TipPolaska tipPolaska) {
		this.polazakId = polazakId;
		this.datum = datum;
		this.linija = linija;
		this.tipPolaska = tipPolaska;
	}

	/**
	 * Metoda koja vraca datum polaska.
	 * @return datum kao Calendar.
	 */
	public Calendar getDatum() {
		return datum;
	}
	/**
	 * Metoda koja postavlja novu vrednost datuma polaska
	 * @param datum nova vrednost datum polaska
	 */
	public void setDatum(Calendar datum) {
		this.datum = datum;
	}
	/**
	 * Metoda koja vraca autobusku liniju na koju se polazak odnosi.
	 * @return linija kao AutobuskaLinijaEntity.
	 */
	public AutobuskaLinijaEntity getLinija() {
		return linija;
	}
	/**
	 * Metoda koja postavlja novu autobusku liniju na koju se polazak odnosi.
	 * @param linija nova autobuska linija na koju se polazak odnosi.
	 */
	public void setLinija(AutobuskaLinijaEntity linija) {
		this.linija = linija;
	}
	/**
	 * Metoda koja vraca tip polaska
	 * @return tipPolaska kao TipPolaska
	 */
	public TipPolaska getTipPolaska() {
		return tipPolaska;
	}
	/**
	 * Metoda koja postavlja novu vrednost za tip polaska
	 * @param tipPolaska nova vrednost za tip polaska
	 */
	public void setTipPolaska(TipPolaska tipPolaska) {
		this.tipPolaska = tipPolaska;
	}
	/**
	 * Metoda koja vraca jedinstveni identifikator polaska
	 * @return polazakId kao String.
	 */
	public String getPolazakId() {
		return polazakId;
	}
	/**
	 * Metoda koja postavlja novu vrednost jedinstvenog identifikatora polaska
	 * @param polazakId nova vrednost jedinstvenog identifikatora polaska
	 */
	public void setPolazakId(String polazakId) {
		this.polazakId = polazakId;
	}

}
