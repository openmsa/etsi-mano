package com.ubiqube.etsi.mano.dao.mano.common;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public enum ApiVersionType {
	SOL003_VNFPKGM("vnfpkgm"),
	SOL005_VNFPKGM("vnfpkgm"),
	SOL003_VNFSNAPSHOTPKGM("vnfsnapshotpkgm"),
	SOL003_GRANT("grant"),
	SOL002_VNFPM("vnfpm"),
	SOL003_VNFPM("vnfpm"),
	SOL002_VNFLCM("vnflcm"),
	SOL003_VNFLCM("vnflcm"),
	SOL002_VNFIND("vnfind"),
	SOL003_VNFIND("vnfind"),
	SOL002_VNFFM("vnffm"),
	SOL003_VNFFM("vnffm"),
	SOL003_VRQAN("vrqan"),
	SOL005_NSD("nsd"),
	SOL005_NSFM("nsfm"),
	SOL005_NSLCM("nslcm"),
	SOL005_NSPM("nspm"),
	SOL002_VNFCONFIG("vnfconfig");

	private String value;

	ApiVersionType(final String string) {
		value = string;
	}

	@Override
	public String toString() {
		return value;
	}

}
