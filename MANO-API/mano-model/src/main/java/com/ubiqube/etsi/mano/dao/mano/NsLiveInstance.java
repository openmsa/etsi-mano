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
package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.nfvo.NsTask;

@Entity
@EntityListeners(AuditListener.class)
public class NsLiveInstance implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private NsTask nsTask;

	@ManyToOne
	private NsBlueprint nsBlueprint;

	private String resourceId;

	@ManyToOne
	private NsdInstance nsInstance;

	@Embedded
	private Audit audit;

	public NsLiveInstance() {
		// Nothing.
	}

	public NsLiveInstance(final String vnfInstanceId, final NsTask nsTask, final NsBlueprint nsBlueprint, final NsdInstance nsInstance) {
		super();
		this.resourceId = vnfInstanceId;
		this.nsTask = nsTask;
		this.nsBlueprint = nsBlueprint;
		this.nsInstance = nsInstance;
	}

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public NsTask getNsTask() {
		return nsTask;
	}

	public void setNsTask(final NsTask nsTask) {
		this.nsTask = nsTask;
	}

	public NsBlueprint getNsBlueprint() {
		return nsBlueprint;
	}

	public void setNsBlueprint(final NsBlueprint nsBlueprint) {
		this.nsBlueprint = nsBlueprint;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
	}

	public NsdInstance getNsInstance() {
		return nsInstance;
	}

	public void setNsInstance(final NsdInstance nsInstance) {
		this.nsInstance = nsInstance;
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
