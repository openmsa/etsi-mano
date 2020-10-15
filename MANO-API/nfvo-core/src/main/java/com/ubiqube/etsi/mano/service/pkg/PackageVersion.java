package com.ubiqube.etsi.mano.service.pkg;

public class PackageVersion {
	private String flavorId;
	private String name;
	private String version;

	public PackageVersion(final String _version) {
		final String[] parts = _version.split("/");
		if (parts.length == 3) {
			flavorId = parts[0];
			name = parts[1];
			version = parts[2];
		} else if (parts.length == 2) {
			name = parts[1];
			version = parts[2];
		} else if (parts.length == 1) {
			name = parts[0];
		}
	}

	public String getFlavorId() {
		return flavorId;
	}

	public void setFlavorId(final String flavorId) {
		this.flavorId = flavorId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public int countPart() {
		int cnt = 0;
		if (name != null) {
			cnt++;
		}
		if (flavorId != null) {
			cnt++;
		}
		if (version != null) {
			cnt++;
		}
		return cnt;
	}

	@Override
	public String toString() {
		if (countPart() == 1) {
			return name;
		}
		if (countPart() == 2) {
			return name + "/" + version;
		}
		if (countPart() == 3) {
			return flavorId + "/" + name + "/" + version;
		}
		return "error";
	}
}
