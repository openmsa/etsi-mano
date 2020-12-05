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
package com.ubiqube.etsi.mano.dao.mano.v2.nfvo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
public class NsVirtualLinkTask extends NsTask {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private NsVirtualLink nsVirtualLink;

	public NsVirtualLink getNsVirtualLink() {
		return nsVirtualLink;
	}

	public void setNsVirtualLink(final NsVirtualLink nsVirtualLink) {
		this.nsVirtualLink = nsVirtualLink;
	}

}
