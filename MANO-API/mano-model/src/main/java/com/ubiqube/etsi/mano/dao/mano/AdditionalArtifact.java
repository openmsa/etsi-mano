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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.dao.mano.pkg.ArtifactClassificationType;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Embeddable
public class AdditionalArtifact implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@FullTextField
	private String artifactPath;

	@Embedded
	private Checksum checksum;

	// 2.7.1
	private Boolean isEncrypted;
	// 2.7.1
	private String nonManoArtifactSetId;
	// 2.7.1 XXX: Multiple URIs ?
	@Transient
	private List<String> artifactURI;
	// 2.7.1
	private ArtifactClassificationType artifactClassification;

	private Map<String, Object> metadata;
}
