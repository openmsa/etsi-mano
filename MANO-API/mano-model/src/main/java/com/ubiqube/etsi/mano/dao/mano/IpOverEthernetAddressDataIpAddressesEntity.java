package com.ubiqube.etsi.mano.dao.mano;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
public class IpOverEthernetAddressDataIpAddressesEntity implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Enumerated(EnumType.STRING)
	private IpType type = null;

	@Valid
	@ElementCollection
	private List<String> fixedAddresses = null;

	private Integer numDynamicAddresses = null;

	private IpOverEthernetAddressDataAddressRangeEntity addressRange = null;

	private String subnetId = null;

	@OneToOne
	private IpOverEthernetAddressDataEntity ipOverEthernetAddressDataEntity;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public IpType getType() {
		return type;
	}

	public void setType(final IpType type) {
		this.type = type;
	}

	public List<String> getFixedAddresses() {
		return fixedAddresses;
	}

	public void setFixedAddresses(final List<String> fixedAddresses) {
		this.fixedAddresses = fixedAddresses;
	}

	public Integer getNumDynamicAddresses() {
		return numDynamicAddresses;
	}

	public void setNumDynamicAddresses(final Integer numDynamicAddresses) {
		this.numDynamicAddresses = numDynamicAddresses;
	}

	public IpOverEthernetAddressDataAddressRangeEntity getAddressRange() {
		return addressRange;
	}

	public void setAddressRange(final IpOverEthernetAddressDataAddressRangeEntity addressRange) {
		this.addressRange = addressRange;
	}

	public String getSubnetId() {
		return subnetId;
	}

	public void setSubnetId(final String subnetId) {
		this.subnetId = subnetId;
	}

	public IpOverEthernetAddressDataEntity getIpOverEthernetAddressDataEntity() {
		return ipOverEthernetAddressDataEntity;
	}

	public void setIpOverEthernetAddressDataEntity(final IpOverEthernetAddressDataEntity ipOverEthernetAddressDataEntity) {
		this.ipOverEthernetAddressDataEntity = ipOverEthernetAddressDataEntity;
	}

}
