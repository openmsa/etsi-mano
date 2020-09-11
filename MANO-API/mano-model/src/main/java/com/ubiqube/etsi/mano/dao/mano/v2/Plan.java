package com.ubiqube.etsi.mano.dao.mano.v2;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.Auditable;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;

@Entity
@EntityListeners(AuditListener.class)
public class Plan implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Audit audit;

	@ManyToOne
	VnfInstance vnfInstance;

	@ManyToOne
	VimConnectionInformation vimConnectionInformation;

	@ManyToOne
	GrantsRequest grantsRequest;

	PlanOperationType operation;

	OperationStatusType status;

	@OneToMany
	Set<Task> tasks;

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

	public VnfInstance getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final VnfInstance vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public VimConnectionInformation getVimConnectionInformation() {
		return vimConnectionInformation;
	}

	public void setVimConnectionInformation(final VimConnectionInformation vimConnectionInformation) {
		this.vimConnectionInformation = vimConnectionInformation;
	}

	public GrantsRequest getGrantsRequest() {
		return grantsRequest;
	}

	public void setGrantsRequest(final GrantsRequest grantsRequest) {
		this.grantsRequest = grantsRequest;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(final Set<Task> tasks) {
		this.tasks = tasks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
