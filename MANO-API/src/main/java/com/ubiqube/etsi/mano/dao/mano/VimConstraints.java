package com.ubiqube.etsi.mano.dao.mano;

import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VimConstraints {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private boolean sameResourceGroup;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<ConstraintRef> resource;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public boolean isSameResourceGroup() {
		return sameResourceGroup;
	}

	public void setSameResourceGroup(final boolean sameResourceGroup) {
		this.sameResourceGroup = sameResourceGroup;
	}

	public List<ConstraintRef> getResource() {
		return resource;
	}

	public void setResource(final List<ConstraintRef> resource) {
		this.resource = resource;
	}

}
