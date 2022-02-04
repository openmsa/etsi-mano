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

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ubiqube.etsi.mano.dao.mano.common.ListKeyPair;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
public class NsVnfInstantiateTask extends NsTask {

	/** Serial. */
	private static final long serialVersionUID = 1L;

	/**
	 * VNFM to use if any.
	 */
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Servers server;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ExternalPortRecord> externalNetworks = new LinkedHashSet<>();

	private String flavourId;

	private String instantiationLevelId;

	private String localizationLanguage;

	private Set<ListKeyPair> virtualLinks;
}
