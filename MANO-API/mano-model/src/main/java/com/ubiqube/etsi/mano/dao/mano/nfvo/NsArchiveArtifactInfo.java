package com.ubiqube.etsi.mano.dao.mano.nfvo;

import java.util.Map;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ubiqube.etsi.mano.dao.mano.PkgChecksum;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@Getter
@Setter
public class NsArchiveArtifactInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String artifactPath;

	private PkgChecksum checksum;

	@ElementCollection
	private Map<String, Object> metadata;

}
