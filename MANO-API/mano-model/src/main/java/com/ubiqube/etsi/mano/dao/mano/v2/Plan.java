/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
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
