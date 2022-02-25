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
package com.ubiqube.etsi.mano.dao.mano.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Embedded;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.Auditable;
import com.ubiqube.etsi.mano.dao.mano.BaseEntity;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.VduInstantiationLevel;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
public class VnfInstantiatedBase implements Auditable, BaseEntity {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	private UUID id ;

	private VduInstantiationLevel instantiationLevel;

	/*
	 * Vnf Compute.
	 */
	private UUID vduId ;

	// 3.3.1
	private String vnfdId;
	/**
	 * Point to a VnfInstantiated row.
	 */
	private UUID removedInstantiated;

	/**
	 * Grant Info
	 */
	private String reservationId;

	/**
	 * Grant Info
	 */
	private String resourceProviderId ;

	/**
	 * Grant Info
	 */
	private String zoneId ;

	/**
	 * Grant Info
	 */
	private String resourceGroupId ;

	/**
	 * VIM Resources.
	 */
	private VimConnectionInformation vimConnectionInformation;

	private String resourceId ;

	private String vimLevelResourceType ;

	private InstantiationStatusType status = InstantiationStatusType.PROCESSING;

	private ChangeType changeType;

	private Date startTime;

	private Date endTime;

	private UUID manoResourceId;

	private VnfLcmOpOccs vnfLcmOpOccs;

	private String aliasName;

	private String toscaName;

	private Map<String, String> metadata = new HashMap<>();
	// @Since 2.7.1
	private String resourceDefinitionId;

	@Embedded
	private Audit audit;
}
