package com.ubiqube.etsi.mano.dao.mano;

import java.util.Map;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VimConnectionInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vimId = null;

	private String vimType = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> interfaceInfo = null;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> accessInfo = null;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> extra = null;

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

	public Map<String, String> getInterfaceInfo() {
		return interfaceInfo;
	}

	public void setInterfaceInfo(final Map<String, String> interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

	public Map<String, String> getAccessInfo() {
		return accessInfo;
	}

	public void setAccessInfo(final Map<String, String> accessInfo) {
		this.accessInfo = accessInfo;
	}

	public Map<String, String> getExtra() {
		return extra;
	}

	public void setExtra(final Map<String, String> extra) {
		this.extra = extra;
	}

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
	}

}
