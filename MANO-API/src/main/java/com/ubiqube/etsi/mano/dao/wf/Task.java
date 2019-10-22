package com.ubiqube.etsi.mano.dao.wf;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ubiqube.etsi.mano.model.nslcm.LcmOperationStateType;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String nsInstanceId;
	private LcmOperationStateType status;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getNsInstanceId() {
		return nsInstanceId;
	}

	public void setNsInstanceId(final String nsInstanceId) {
		this.nsInstanceId = nsInstanceId;
	}

	public LcmOperationStateType getStatus() {
		return status;
	}

	public void setStatus(final LcmOperationStateType status) {
		this.status = status;
	}

}
