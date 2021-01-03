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
package com.ubiqube.etsi.mano.dao.mano.pm;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;

import lombok.Data;

@Data
@Entity
public class PmJob {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	/**
	 * Type of the measured object.  The applicable measured object type for a measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027 [5]. 
	 */
	private String objectType;

	/**
	 * Identifiers of the measured object instances for which performance information is collected.
	 */
	@ElementCollection
	private List<UUID> objectInstanceIds;

	/**
	 * Identifiers of the sub-object instances of the measured object instance for which performance information is requested to be collected.
	 * May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 [5] for the related measured object type If this attribute is present, the cardinality of the "objectInstanceIds" attribute shall be 1. 
	 * If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 [5] for the related measured object type, measurements will be taken for all sub-object instances of the measured object instance.
	 */
	@ElementCollection
	private List<UUID> subObjectInstanceIds;

	/**
	 * Criteria of the collection of performance information. 
	 */
	@Embedded
	private PmJobCriteria criteria;

	/**
	 * The URI of the endpoint to send the notification to.
	 */
	private URI callbackUri;

	/**
	 * Information about available reports collected by this PM job.
	 */
	@OneToMany
	private List<PmReport> reports;

	@Embedded
	private AuthentificationInformations subscription;
}
