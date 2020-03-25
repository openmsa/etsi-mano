package com.ubiqube.etsi.mano.service.rest;

public class Authentificate {

	private String username;
	private String password;

	public Authentificate() {
		// Nothing.
	}

	public Authentificate(final String user, final String password2) {
		username = user;
		password = password2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
