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

import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfTask;

@Entity
@EntityListeners(AuditListener.class)
public class VnfLiveInstance implements BaseEntity, Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private VnfInstance vnfInstance;

	private String instantiationLevel;

	@ManyToOne
	private VnfTask task;

	@ManyToOne
	private VnfBlueprint blueprint;

	/**
	 * VIM resourceId.
	 */
	private String resourceId;

	@Embedded
	private Audit audit;

	public VnfLiveInstance() {
		// Nothing.
	}

	public VnfLiveInstance(final VnfInstance vnfInstance, final String instantiationLevel, final VnfTask _task, final VnfBlueprint _blueprint, final String _resourceId) {
		super();
		this.vnfInstance = vnfInstance;
		this.instantiationLevel = instantiationLevel;
		blueprint = _blueprint;
		task = _task;
		resourceId = _resourceId;
	}

	@Override
	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VnfInstance getVnfInstance() {
		return vnfInstance;
	}

	public void setVnfInstance(final VnfInstance vnfInstance) {
		this.vnfInstance = vnfInstance;
	}

	public String getInstantiationLevel() {
		return instantiationLevel;
	}

	public void setInstantiationLevel(final String instantiationLevel) {
		this.instantiationLevel = instantiationLevel;
	}

	public VnfTask getTask() {
		return task;
	}

	public void setTask(final VnfTask task) {
		this.task = task;
	}

	public VnfBlueprint getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(final VnfBlueprint blueprint) {
		this.blueprint = blueprint;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(final String resourceId) {
		this.resourceId = resourceId;
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
