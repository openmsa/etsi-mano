package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@EntityListeners(AuditListener.class)
public class NsSap implements BaseEntity, Auditable, Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String toscaId;

	private String toscaName;

	private String state;

	private String role;

	private String description;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> layerProtocols;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn
	private Set<NsCpProtocolData> protocol;

	private boolean trunkMode;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<SecurityGroup> securityGroups;

	@ManyToOne
	private NsdPackage nsdPackage;

	@Embedded
	private Audit audit;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Set<String> getLayerProtocols() {
		return layerProtocols;
	}

	public void setLayerProtocols(final Set<String> layerProtocols) {
		this.layerProtocols = layerProtocols;
	}

	public boolean isTrunkMode() {
		return trunkMode;
	}

	public void setTrunkMode(final boolean trunkMode) {
		this.trunkMode = trunkMode;
	}

	public Set<NsCpProtocolData> getProtocol() {
		return protocol;
	}

	public void setProtocol(final Set<NsCpProtocolData> protocol) {
		this.protocol = protocol;
	}

	public Set<SecurityGroup> getSecurityGroups() {
		return securityGroups;
	}

	public void addSecurityGroups(final SecurityGroup securityGroup) {
		if (null == securityGroups) {
			securityGroups = new HashSet<>();
		}
		securityGroups.add(securityGroup);
	}

	public void setSecurityGroups(final Set<SecurityGroup> securityGroup) {
		securityGroups = securityGroup;
	}

	public NsdPackage getNsdPackage() {
		return nsdPackage;
	}

	public void setNsdPackage(final NsdPackage nsdPackage) {
		this.nsdPackage = nsdPackage;
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
