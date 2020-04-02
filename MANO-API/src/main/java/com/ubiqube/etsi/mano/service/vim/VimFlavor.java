package com.ubiqube.etsi.mano.service.vim;

public class VimFlavor {
	private String id;
	private String name;
	private int vCpus;
	private int ram;
	private int disk;

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

	public int getvCpus() {
		return vCpus;
	}

	public void setvCpus(final int vCpus) {
		this.vCpus = vCpus;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(final int ram) {
		this.ram = ram;
	}

	public int getDisk() {
		return disk;
	}

	public void setDisk(final int disk) {
		this.disk = disk;
	}

}
