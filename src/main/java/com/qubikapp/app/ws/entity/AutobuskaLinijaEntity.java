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
/**
 * Klasa koja predstavlja autobusku liniju ciji se polazak rezervise.
 * @author Jovan Lazic
 * @version 1.0
 * @see PolazakEntity
 */
@Entity(name = "autobuska_linija")
public class AutobuskaLinijaEntity {
	/**
	 * Jedinstveni identifikator autobuske linije kao String.
	 */
	@Id
	@Column(name = "linija_id")
	private String linijaId;
	
	/**
	 * Mesto polaska autobuske linije kao MestoEntity.
	 */
	@ManyToOne
	@JoinColumn(name = "mesto_polaska")
	private MestoEntity mestoPolaska;
	/**
	 * Mesto dolaska autobuske linije kao MestoEntity.
	 */
	@ManyToOne
	@JoinColumn(name = "mesto_dolaska")
	private MestoEntity mestoDolaska;
	
	/**
	 * Naziv autobuske linije kao String.
	 */
	private String naziv;
	/**
	 * Prevoznik autobuske linije kao PrevoznikEntity.
	 */
	@ManyToOne
	@JoinColumn(name = "prevoznik")
	private PrevoznikEntity prevoznik;
	/**
	 * Lista polazaka autobuske linije tipa PolazakEntity.
	 */
	@OneToMany(mappedBy = "linija",cascade = CascadeType.ALL)
	private List<PolazakEntity> polasci;
	
	public AutobuskaLinijaEntity() {
	}

	
	/**
	 * Inicijalizacija autobuske linije.
	 * @param mestoPolaska Mesto polaska autobuske linije.
	 * @param mestoDolaska Mesto dolaska autobuske linije.
	 * @param naziv Naziv autobuske linije.
	 * @param prevoznik Prevoznik autobuske linije.
	 */
	public AutobuskaLinijaEntity(MestoEntity mestoPolaska, MestoEntity mestoDolaska, String naziv,
			PrevoznikEntity prevoznik) {
		this.mestoPolaska = mestoPolaska;
		this.mestoDolaska = mestoDolaska;
		this.naziv = naziv;
		this.prevoznik = prevoznik;
	}


	/**
	 * Metoda koja vraca jedinstveni identifikator autobuske linije.
	 * @return linijaId kao String.
	 */
	public String getLinijaId() {
		return linijaId;
	}
	/**
	 * Metoda koja postavlja novu vrednost jedinstvenog identifikatora autobuske linije.
	 * @param linijaId nova vrednost jedinstvenog identifikatora autobuske linije.
	 */
	public void setLinijaId(String linijaId) {
		this.linijaId = linijaId;
	}
	/**
	 * Metoda koja vraca mesto polaska autobuske linije.
	 * @return mestoPolaska kao MestoEntity.
	 */
	public MestoEntity getMestoPolaska() {
		return mestoPolaska;
	}
	/**
	 * Metoda koja postavlja novo mesto polaska autobuske linije.
	 * @param mestoPolaska novo mesto polaska autobuske linije.
	 */
	public void setMestoPolaska(MestoEntity mestoPolaska) {
		this.mestoPolaska = mestoPolaska;
	}
	/**
	 * Metoda koja vraca mesto dolaska autobuske linije.
	 * @return mestoDolaska kao MestoEntity.
	 */
	public MestoEntity getMestoDolaska() {
		return mestoDolaska;
	}
	/**
	 * Metoda koja postavlja novo mesto dolaska autobuske linije.
	 * @param mestoDolaska novo mesto dolaska autobuske linije.
	 */
	public void setMestoDolaska(MestoEntity mestoDolaska) {
		this.mestoDolaska = mestoDolaska;
	}
	/**
	 * Metoda koja vraca naziv autobuske linije.
	 * @return naziv kao String.
	 */
	public String getNaziv() {
		return naziv;
	}
	/**
	 * Metoda koja postavlja novu vrednost naziva autobuske linije.
	 * @param naziv nova vrednost naziva autobuske linije.
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	/**
	 * Metoda koja vraca prevoznika autobuske linije.
	 * @return prevoznik kao PrevozniEntity.
	 */
	public PrevoznikEntity getPrevoznik() {
		return prevoznik;
	}
	/**
	 * Metoda koja postavlja novog prevoznika autobuske linije.
	 * @param prevoznik novi prevoznik autobuske linije.
	 */
	public void setPrevoznik(PrevoznikEntity prevoznik) {
		this.prevoznik = prevoznik;
	}
	/**
	 * Metoda koja vraca listu polazaka autobuske linije.
	 * @return polasci kao List<PolazakEntity>.
	 */
	@JsonIgnore
	public List<PolazakEntity> getPolasci() {
		return polasci;
	}
	/**
	 * Metoda koja postavlja novu listu polazaka autobuske linije
	 * @param polasci nova lista polazaka autobuske linije
	 */
	public void setPolasci(List<PolazakEntity> polasci) {
		this.polasci = polasci;
	}

}
