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

import java.util.Map;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EntityListeners(AuditListener.class)
public class VimConnectionInformation implements Auditable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id = null;

	private String vimId = null;

	private String vimType = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> interfaceInfo = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> accessInfo = null;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> extra = null;

	private Audit audit;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getVimType() {
		return vimType;
	}

	public void setVimType(final String vimType) {
		this.vimType = vimType;
	}

	public Map<String, String> getInterfaceInfo() {
		return interfaceInfo;
	}

	public void setInterfaceInfo(final Map<String, String> interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

	public Map<String, String> getAccessInfo() {
		return accessInfo;
	}

	public void setAccessInfo(final Map<String, String> accessInfo) {
		this.accessInfo = accessInfo;
	}

	public Map<String, String> getExtra() {
		return extra;
	}

	public void setExtra(final Map<String, String> extra) {
		this.extra = extra;
	}

	public String getVimId() {
		return vimId;
	}

	public void setVimId(final String vimId) {
		this.vimId = vimId;
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
