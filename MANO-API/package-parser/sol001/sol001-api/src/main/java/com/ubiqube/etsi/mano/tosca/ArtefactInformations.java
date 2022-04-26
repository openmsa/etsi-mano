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
package com.ubiqube.etsi.mano.tosca;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArtefactInformations {
	private String checksum;
	private String path;
	private Map<String, String> metadata;
	private String algorithm;
	private String signature;
	private String certificate;
	private String nonManoSetIndentifier;
	private String classifier;

	public boolean isEncrypted() {
		return path.endsWith(".enc.cms");
	}

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(final String checksum) {
		this.checksum = checksum;
	}

	public String getPath() {
		return path;
	}

	public void setPath(final String path) {
		this.path = path;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(final Map<String, String> metadata) {
		this.metadata = metadata;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(final String algorithm) {
		this.algorithm = algorithm;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(final String signature) {
		this.signature = signature;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(final String certificate) {
		this.certificate = certificate;
	}

	public String getNonManoSetIndentifier() {
		return nonManoSetIndentifier;
	}

	public void setNonManoSetIndentifier(final String nonManoSetIndentifier) {
		this.nonManoSetIndentifier = nonManoSetIndentifier;
	}

	public String getClassifier() {
		return classifier;
	}

	public void setClassifier(final String classifier) {
		this.classifier = classifier;
	}

}
