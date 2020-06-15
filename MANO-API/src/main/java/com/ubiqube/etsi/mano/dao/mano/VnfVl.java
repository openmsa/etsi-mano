package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
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
public class VnfVl implements ToscaEntity, Auditable, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String toscaId;

	private String toscaName;

	private String state;

	private String description;

	private int initialBrRoot;

	private int initialBrLeaf;

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

	@Override
	public String getToscaId() {
		return toscaId;
	}

	@Override
	public void setToscaId(final String toscaId) {
		this.toscaId = toscaId;
	}

	@Override
	public String getToscaName() {
		return toscaName;
	}

	@Override
	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(final String state) {
		this.state = state;
	}

	public VlProfileEntity getVlProfileEntity() {
		return vlProfileEntity;
	}

	public void setVlProfileEntity(final VlProfileEntity vlProfileEntity) {
		this.vlProfileEntity = vlProfileEntity;
	}

	public int getInitialBrRoot() {
		return initialBrRoot;
	}

	public void setInitialBrRoot(final int initialBrRoot) {
		this.initialBrRoot = initialBrRoot;
	}

	public int getInitialBrLeaf() {
		return initialBrLeaf;
	}

	public void setInitialBrLeaf(final int initialBrLeaf) {
		this.initialBrLeaf = initialBrLeaf;
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
