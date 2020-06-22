package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class VimConstraints implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private boolean sameResourceGroup;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<ConstraintRef> resources;

	@ManyToOne
	private GrantsRequest grants;

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

	public Set<ConstraintRef> getResources() {
		return resources;
	}

	public void setResources(final Set<ConstraintRef> resources) {
		this.resources = resources;
	}

	public GrantsRequest getGrants() {
		return grants;
	}

	public void setGrants(final GrantsRequest grants) {
		this.grants = grants;
	}

}
