package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import com.ubiqube.etsi.mano.dao.mano.common.Checksum;

@Embeddable
public class SoftwareImage {
	private String name;
	private String provider;
	private String version;
	@Embedded
	private Checksum checksum;
	// Probably an Enum.
	private String containerFormat;
	// Probably an Enum.
	private String diskFormat;
	private long minDisk;
	private long minRam;
	private long size;
	private String imagePath;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(final String provider) {
		this.provider = provider;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public Checksum getChecksum() {
		return checksum;
	}

	public void setChecksum(final Checksum checksum) {
		this.checksum = checksum;
	}

	public String getContainerFormat() {
		return containerFormat;
	}

	public void setContainerFormat(final String containerFormat) {
		this.containerFormat = containerFormat;
	}

	public String getDiskFormat() {
		return diskFormat;
	}

	public void setDiskFormat(final String diskFormat) {
		this.diskFormat = diskFormat;
	}

	public long getMinDisk() {
		return minDisk;
	}

	public void setMinDisk(final long minDisk) {
		this.minDisk = minDisk;
	}

	public long getMinRam() {
		return minRam;
	}

	public void setMinRam(final long minRam) {
		this.minRam = minRam;
	}

	public long getSize() {
		return size;
	}

	public void setSize(final long size) {
		this.size = size;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(final String imagePath) {
		this.imagePath = imagePath;
	}

}
