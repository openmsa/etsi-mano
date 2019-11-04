package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.ubiqube.etsi.mano.model.KeyValuePairs;

@Entity
public class VimConnectionInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private final String vimId = null;

	private String vimType = null;

	@Transient
	private KeyValuePairs interfaceInfo = null;
	@Transient
	private KeyValuePairs accessInfo = null;
	@Transient
	private KeyValuePairs extra = null;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVimType() {
		return vimType;
	}

	public void setVimType(final String vimType) {
		this.vimType = vimType;
	}

	public KeyValuePairs getInterfaceInfo() {
		return interfaceInfo;
	}

	public void setInterfaceInfo(final KeyValuePairs interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

	public KeyValuePairs getAccessInfo() {
		return accessInfo;
	}

	public void setAccessInfo(final KeyValuePairs accessInfo) {
		this.accessInfo = accessInfo;
	}

	public KeyValuePairs getExtra() {
		return extra;
	}

	public void setExtra(final KeyValuePairs extra) {
		this.extra = extra;
	}

}
