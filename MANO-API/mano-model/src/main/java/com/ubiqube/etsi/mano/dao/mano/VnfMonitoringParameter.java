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

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditListener.class)
public class VnfMonitoringParameter implements Auditable, BaseEntity {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Embedded
	private Audit audit;

	/**
	 * Human readable name of the monitoring parameter, as defined in the VNFD.
	 * 
	 */
	private String name;
	/**
	 * Identifier of the VNFD. Shall be present in case the value differs from the
	 * vnfdId attribute of the VnfInstance (e.g. during a "Change current VNF
	 * package" operation or due to its final failure).
	 *
	 * @since 3.3.1
	 */
	private String vnfdId;

	/**
	 * Performance metric that is monitored. This attribute shall contain the
	 * related "Measurement Name" value as defined in clause 7.2 of ETSI GS NFV-IFA
	 * 027 [6].
	 */
	private String performanceMetric;
}
