package com.ubiqube.etsi.mano.dao.mano.ipam;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;

@Entity
public class Networks {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String start;

	private String end;

	private String cidr;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private VimConnectionInformation vimConnectionInformation;

	private String vimResource;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(final String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(final String end) {
		this.end = end;
	}

	public String getCidr() {
		return cidr;
	}

	public void setCidr(final String cidr) {
		this.cidr = cidr;
	}

	public VimConnectionInformation getVimConnectionInformation() {
		return vimConnectionInformation;
	}

	public void setVimConnectionInformation(final VimConnectionInformation vimConnectionInformation) {
		this.vimConnectionInformation = vimConnectionInformation;
	}

	public String getVimResource() {
		return vimResource;
	}

	public void setVimResource(final String vimResource) {
		this.vimResource = vimResource;
	}

}
