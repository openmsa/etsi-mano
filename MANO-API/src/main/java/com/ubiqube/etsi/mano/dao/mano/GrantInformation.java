package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition.TypeEnum;

@Entity
public class GrantInformation implements BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String resourceDefinitionId = null;

	private String reservationId = null;

	private String vimConnectionId = null;

	private String resourceProviderId = null;

	private String zoneId = null;

	private String resourceGroupId = null;

	@ManyToOne
	private VduInstantiationLevel instantiationLevel;

	@Enumerated(EnumType.STRING)
	private TypeEnum type;

	private UUID vduId;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getResourceDefinitionId() {
		return resourceDefinitionId;
	}

	public void setResourceDefinitionId(final String resourceDefinitionId) {
		this.resourceDefinitionId = resourceDefinitionId;
	}

	public String getReservationId() {
		return reservationId;
	}

	public void setReservationId(final String reservationId) {
		this.reservationId = reservationId;
	}

	public String getVimConnectionId() {
		return vimConnectionId;
	}

	public void setVimConnectionId(final String vimConnectionId) {
		this.vimConnectionId = vimConnectionId;
	}

	public String getResourceProviderId() {
		return resourceProviderId;
	}

	public void setResourceProviderId(final String resourceProviderId) {
		this.resourceProviderId = resourceProviderId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public String getResourceGroupId() {
		return resourceGroupId;
	}

	public void setResourceGroupId(final String resourceGroupId) {
		this.resourceGroupId = resourceGroupId;
	}

	public TypeEnum getType() {
		return type;
	}

	public void setType(final TypeEnum type) {
		this.type = type;
	}

	public UUID getVduId() {
		return vduId;
	}

	public void setVduId(final UUID vduId) {
		this.vduId = vduId;
	}

	public VduInstantiationLevel getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final VduInstantiationLevel instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

}
