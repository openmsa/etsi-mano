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
package com.ubiqube.etsi.mano.dao.mano.ipam;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;

@Entity
public class Networks {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String startRange;

	private String endRange;

	private String cidr;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private VimConnectionInformation vimConnectionInformation;

	private String vimResource;

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

	public String getStartRange() {
        return startRange;
    }

    public void setStartRange(String startRange) {
        this.startRange = startRange;
    }

    public String getEndRange() {
        return endRange;
    }

    public void setEndRange(String endRange) {
        this.endRange = endRange;
    }

    public String getCidr() {
		return cidr;
	}

	public void setCidr(final String cidr) {
		this.cidr = cidr;
	}

	public VimConnectionInformation getVimConnectionInformation() {
		return vimConnectionInformation;
	}

	public void setVimConnectionInformation(final VimConnectionInformation vimConnectionInformation) {
		this.vimConnectionInformation = vimConnectionInformation;
	}

	public String getVimResource() {
		return vimResource;
	}

	public void setVimResource(final String vimResource) {
		this.vimResource = vimResource;
	}

}
