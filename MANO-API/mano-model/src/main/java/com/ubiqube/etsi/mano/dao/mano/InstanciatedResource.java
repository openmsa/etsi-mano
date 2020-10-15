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

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class InstanciatedResource implements Serializable {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private VimConnectionInformation vimConnectionInformation;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private VnfInstance instance;

	private String vimResourceId;

	private String vimResourceType;

	@Enumerated(EnumType.STRING)
	private InstantiationStatusType status;

	private Date startTime;

	private Date endTime;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public VimConnectionInformation getVimConnectionInformation() {
		return vimConnectionInformation;
	}

	public void setVimConnectionInformation(final VimConnectionInformation vimConnectionInformation) {
		this.vimConnectionInformation = vimConnectionInformation;
	}

	public VnfInstance getInstance() {
		return instance;
	}

	public void setInstance(final VnfInstance instance) {
		this.instance = instance;
	}

	public String getVimResourceId() {
		return vimResourceId;
	}

	public void setVimResourceId(final String vimResourceId) {
		this.vimResourceId = vimResourceId;
	}

	public String getVimResourceType() {
		return vimResourceType;
	}

	public void setVimResourceType(final String vimResourceType) {
		this.vimResourceType = vimResourceType;
	}

	public InstantiationStatusType getStatus() {
		return status;
	}

	public void setStatus(final InstantiationStatusType status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

}
