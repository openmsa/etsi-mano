package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ubiqube.etsi.mano.dao.mano.common.Checksum;

@Entity
@EntityListeners(AuditListener.class)
public class SoftwareImage implements Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vimId;

	private String name;

	private String provider;

	private String version;

	@Embedded
	private Checksum checksum;

	// XXX: Probably an Enum.
	private String containerFormat;

	// XXX: Probably an Enum.
	private String diskFormat;

	private long minDisk;

	private long minRam;

	private long size;

	private String imagePath;

	private Audit audit;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
	}

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

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void setAudit(final Audit audit) {
		this.audit = audit;
	}

}
