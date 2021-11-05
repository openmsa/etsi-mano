package com.ubiqube.etsi.mano.dao.mano.vnfm;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;

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
public class VnfPkgChange {
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> vnfConfigurableProperties;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> metadata;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> extensions;

	private String vnfdId;

	private String vnfProvider;

	private String vnfProductName;

	private String vnfSoftwareVersion;

	private String vnfdVersion;

}
