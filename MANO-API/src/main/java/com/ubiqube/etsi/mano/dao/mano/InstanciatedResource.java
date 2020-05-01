package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class InstanciatedResource implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private VimConnectionInformation vimConnectionInformation;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private VnfInstance instance;

	private String vimResourceId;

	private String vimResourceType;

	@Enumerated(EnumType.STRING)
	private InstantiationStatusType status;

	private Date startTime;

	private Date endTime;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VimConnectionInformation getVimConnectionInformation() {
		return vimConnectionInformation;
	}

	public void setVimConnectionInformation(final VimConnectionInformation vimConnectionInformation) {
		this.vimConnectionInformation = vimConnectionInformation;
	}

	public VnfInstance getInstance() {
		return instance;
	}

	public void setInstance(final VnfInstance instance) {
		this.instance = instance;
	}

	public String getVimResourceId() {
		return vimResourceId;
	}

	public void setVimResourceId(final String vimResourceId) {
		this.vimResourceId = vimResourceId;
	}

	public String getVimResourceType() {
		return vimResourceType;
	}

	public void setVimResourceType(final String vimResourceType) {
		this.vimResourceType = vimResourceType;
	}

	public InstantiationStatusType getStatus() {
		return status;
	}

	public void setStatus(final InstantiationStatusType status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

}
