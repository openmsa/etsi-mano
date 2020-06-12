package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@EntityListeners(AuditListener.class)
public class VnfInstantiatedExtCp extends VnfInstantiatedBase {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	@ManyToOne
	private VnfExtCp vnfExtCp;

	private String aliasName;

	private String toscaName;

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfExtCp getVnfExtCp() {
		return vnfExtCp;
	}

	public void setVnfExtCp(final VnfExtCp vnfExtCp) {
		this.vnfExtCp = vnfExtCp;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(final String aliasName) {
		this.aliasName = aliasName;
	}

	public String getToscaName() {
		return toscaName;
	}

	public void setToscaName(final String toscaName) {
		this.toscaName = toscaName;
	}

}
