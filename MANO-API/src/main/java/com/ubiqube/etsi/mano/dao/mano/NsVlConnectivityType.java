package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class NsVlConnectivityType implements BaseEntity, Auditable, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> layerProtocols;

	private String flowPattern;

	@Embedded
	private Audit audit;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public Set<String> getLayerProtocols() {
		return layerProtocols;
	}

	public void setLayerProtocols(final Set<String> layerProtocols) {
		this.layerProtocols = layerProtocols;
	}

	public String getFlowPattern() {
		return flowPattern;
	}

	public void setFlowPattern(final String flowPattern) {
		this.flowPattern = flowPattern;
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
