package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@EntityListeners(AuditListener.class)
public class VnfVl implements BaseEntity, Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String toscaId;
	private String toscaName;
	private String state;

	private String description;

	@Embedded
	private Audit audit;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private VlProfileEntity vlProfileEntity;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getToscaId() {
		return toscaId;
	}

	public void setToscaId(final String toscaId) {
		this.toscaId = toscaId;
	}

	public String getToscaName() {
		return toscaName;
	}

	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public VlProfileEntity getVlProfileEntity() {
		return vlProfileEntity;
	}

	public void setVlProfileEntity(final VlProfileEntity vlProfileEntity) {
		this.vlProfileEntity = vlProfileEntity;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

}
