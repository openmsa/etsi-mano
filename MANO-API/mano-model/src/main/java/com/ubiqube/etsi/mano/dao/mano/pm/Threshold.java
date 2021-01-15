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
import java.util.Set;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;

import lombok.Data;

@Data
@Indexed
@Entity
public class Threshold {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;

	/**
	 * Type of the measured object.  The applicable measured object type for a measurement is defined in clause 7.2 of ETSI GS NFV-IFA 027 [5].
	 */
	@FullTextField
	String objectType;

	/**
	 * Identifier of the measured object (i.e. VNF instance) associated with the threshold. 
	 */
	@FullTextField
	UUID objectInstanceId;

	/**
	 * Identifiers of the sub-object instances of the measured object instance associated with the threshold. May be present if a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 [5] for the related measurement type. If this attribute is absent and a sub-object is defined in clause 6.2 of ETSI GS NFV-IFA 027 [5] for the measured object type, measurements will be taken for all sub-object instances of the measured object instance. 
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	Set<UUID> subObjectInstanceIds;

	/**
	 * Criteria that define this threshold. 
	 */
	@Embedded
	ThresholdCriteria criteria;

	/**
	 * The URI of the endpoint to send the notification to.
	 */
	URI callbackUri;
	
	@Embedded
	private AuthentificationInformations subscription;
}
