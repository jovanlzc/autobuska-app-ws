package com.qubikapp.app.ws.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Klasa koja predstavlja korisnika 
 * 
 * @author Jovan Lazic
 * @version 1.0
 *
 */
@Entity(name = "users")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1559196142250551450L;
	/**
	 * Jedinstveni identifikator korisnika kao String.
	 */
	@Id
	@Column(name="user_id")
	private String userId;
	/**
	 * Ime korisnika kao String.
	 */
	@Column(nullable = false, length = 50)
	private String firstName;
	/**
	 * Prezime korisnika kao String.
	 */
	@Column(nullable = false, length = 50)
	private String lastName;
	/**
	 * Email korisnika kao String koji sluzi za prijavu na sistem, mora biti jedinstven.
	 */
	@Column(nullable = false, length = 100,unique = true)
	private String email;
	/**
	 * Lozinka kao String korisnika kao String koja se koristi za prijavu na sistem.
	 */
	@Column(nullable = false)
	private String encryptedPassword;
	/**
	 * Token kao String koji se koristi za verifikaciju ispravnosti emaila korisnika.
	 */
	private String emailVerificationToken;
	/**
	 * Status kao boolean koji prikazuje da li je korisnik verifikao email adresu.
	 */
	@Column(nullable = false)
	private Boolean emailVerificationStatus=false;

	
	
	public UserEntity() {
	}
	
	
	/**
	 * Inicijalizuje korisnika.
	 * 
	 * @param firstName Ime korisnika koji zahteva registraciju.
	 * @param lastName Prezime korisnika koji se registraciju.
	 * @param email Email adresa korisnika koji zahteva registraciju, mora biti jedinstvena i koristi se za prijavu na sistem.
	 * @param encryptedPassword Lozinka korisnika koji zahteva registraciju, koristi se za prijavu na sistem.
	 */
	public UserEntity(String firstName, String lastName, String email, String encryptedPassword) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
	}


	/**
	 * Metoda koja vraca generisani jedinstveni identifikator korisnika.
	 * @return userId kao String.
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * Metoda koja postavlja novi jedinstveni identifikator korisnika.
	 * 
	 * @param userId nova vrednost za userId korisnika.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * Metoda koja vraca ime korisnika.
	 * @return firstName kao String.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Metoda koja postavlja novo ime korisnika.
	 * @param firstName nova vrednost za ime korisnika.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Metoda koja vraca prezime korisnika.
	 * @return lastName kao String.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Metoda koja postavlja novo prezime korisnika.
	 * @param lastName nova vrednost za prezime korisnika.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Metoda koja vraca email adresu korisnika.
	 * @return prezime kao String.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Metoda koja postavlja novu vrednost email adrese korisnika.
	 * @param email nova vrednost za email adresu korisnika
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Metoda koja vraca lozinku korisnika
	 * @return encryptedPassword kao String.
	 */
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	/**
	 * Metoda koja postavlja novu vrednost lozinke
	 * @param encryptedPassword nova vrednost za lozinku korinisnika.
	 */
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	/**
	 * Metoda koja vraca token za verifikaciju email adrese
	 * @return emailVerificationToken kao String.
	 */
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	/**
	 * Metoda koja postavlja novu vrednost verifikacionog tokena za email adresu
	 * @param emailVerificationToken nova vrednost za verifikacioni token za email adresu
	 */
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	/**
	 * Metoda koja vraca verifikacioni status da li je korisnik verifikao email adresu
	 * @return emailVerificationStatus kao Boolean.
	 */
	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}
	/**
	 * Metoda koja postavlja novu vrednost verifikacionog statusa email adrese korisnika.
	 * @param emailVerificationStatus nova vrednost za verifikacioni status email adrese korisnika.
	 */
	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

}
