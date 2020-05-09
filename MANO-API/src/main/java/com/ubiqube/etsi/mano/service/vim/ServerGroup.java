package com.ubiqube.etsi.mano.service.vim;

public class ServerGroup {
	private String id;

	private String name;

	private String availabilityZone;

	public ServerGroup() {
		// Nothing.
	}

	public ServerGroup(final String id, final String name, final String _availabilityZone) {
		super();
		this.id = id;
		this.name = name;
		availabilityZone = _availabilityZone;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAvailabilityZone() {
		return availabilityZone;
	}

	public void setAvailabilityZone(final String availabilityZone) {
		this.availabilityZone = availabilityZone;
	}

}
