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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@EntityListeners(AuditListener.class)
public class NsVirtualLink implements BaseEntity, Auditable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NsVlProfile nsVlProfile;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NsVlConnectivityType vlConnectivityType;

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

	public NsVlProfile getNsVlProfile() {
		return nsVlProfile;
	}

	public void setNsVlProfile(final NsVlProfile nsVlProfile) {
		this.nsVlProfile = nsVlProfile;
	}

	public NsVlConnectivityType getVlConnectivityType() {
		return vlConnectivityType;
	}

	public void setVlConnectivityType(final NsVlConnectivityType vlConnectivityType) {
		this.vlConnectivityType = vlConnectivityType;
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
