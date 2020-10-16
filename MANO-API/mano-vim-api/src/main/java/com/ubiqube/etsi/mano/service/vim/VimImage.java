package com.ubiqube.etsi.mano.service.vim;

public class VimImage {
	private String id;
	private String name;
	private long size;
	private long mindisk;
	private long minRam;

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

	public long getSize() {
		return size;
	}

	public void setSize(final long size) {
		this.size = size;
	}

	public long getMindisk() {
		return mindisk;
	}

	public void setMindisk(final long mindisk) {
		this.mindisk = mindisk;
	}

	public long getMinRam() {
		return minRam;
	}

	public void setMinRam(final long minRam) {
		this.minRam = minRam;
	}

}
