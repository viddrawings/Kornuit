package com.kornuit.calendar;

import java.sql.Timestamp;

public class Afspraak {
	
	private int id;
	private String user;
	private Timestamp datumTijd;
	private String locatie;
	private String activiteit;
	private String facebookVriendId;
	private String facebookVriendNaam;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Timestamp getDatumTijd() {
		return datumTijd;
	}
	public void setDatumTijd(Timestamp datumTijd) {
		this.datumTijd = datumTijd;
	}
	public String getLocatie() {
		return locatie;
	}
	public void setLocatie(String locatie) {
		this.locatie = locatie;
	}
	public String getActiviteit() {
		return activiteit;
	}
	public void setActiviteit(String activiteit) {
		this.activiteit = activiteit;
	}
	public String getFacebookVriendId() {
		return facebookVriendId;
	}
	public void setFacebookVriendId(String facebookVriendId) {
		this.facebookVriendId = facebookVriendId;
	}
	public String getFacebookVriendNaam() {
		return facebookVriendNaam;
	}
	public void setFacebookVriendNaam(String facebookVriendNaam) {
		this.facebookVriendNaam = facebookVriendNaam;
	}
	
}
