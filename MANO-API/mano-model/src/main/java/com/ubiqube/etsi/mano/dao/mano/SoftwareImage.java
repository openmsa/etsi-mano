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
package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.annotation.Nullable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ubiqube.etsi.mano.dao.mano.common.Checksum;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
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

	@Enumerated(EnumType.STRING)
	private ContainerFormatType containerFormat;

	@Enumerated(EnumType.STRING)
	private DiskFormatType diskFormat;

	@Nullable
	private Long minDisk;

	@Nullable
	private Long minRam;

	@Nullable
	private Long size;

	/**
	 * Path in archive
	 */
	@Nullable
	private String imagePath;

	/**
	 * Path in NFVO repository.
	 */
	private String nfvoPath;

	@Nullable
	private String architecture;

	private Audit audit;

}
