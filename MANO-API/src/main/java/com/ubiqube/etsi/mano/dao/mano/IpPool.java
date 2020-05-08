package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IpPool implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String startIpAddress;

	private String endIpAddress;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getStartIpAddress() {
		return startIpAddress;
	}

	public void setStartIpAddress(final String startIpAddress) {
		this.startIpAddress = startIpAddress;
	}

	public String getEndIpAddress() {
		return endIpAddress;
	}

	public void setEndIpAddress(final String endIpAddress) {
		this.endIpAddress = endIpAddress;
	}

}
