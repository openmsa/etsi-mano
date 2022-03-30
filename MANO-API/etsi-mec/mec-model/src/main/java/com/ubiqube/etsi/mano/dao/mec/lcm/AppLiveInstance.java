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
package com.ubiqube.etsi.mano.dao.mec.lcm;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.AuditListener;
import com.ubiqube.etsi.mano.dao.mano.Auditable;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
@Entity
@Table(schema = "mec_meo")
@EntityListeners(AuditListener.class)
public class AppLiveInstance implements BaseEntity, Auditable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne
	private AppInstance appInstance;

	private String instantiationLevel;

	@ManyToOne
	private AppTask task;

	@ManyToOne
	private AppBlueprint blueprint;

	/**
	 * VIM resourceId.
	 */
	private String resourceId;

	@Embedded
	private Audit audit;

	public AppLiveInstance() {
		// Nothing.
	}

	public AppLiveInstance(@NotNull final AppInstance vnfInstance, final String il, final AppTask appTask, @NotNull final AppBlueprint blueprint, final String vimResourceId) {
		this.appInstance = vnfInstance;
		this.task = appTask;
		this.blueprint = blueprint;
		this.resourceId = vimResourceId;
	}

}
