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
package com.ubiqube.etsi.mano.dao.mano.nsd.wan;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import com.ubiqube.etsi.mano.dao.mano.nsd.wan.type.ConnectionType;
import com.ubiqube.etsi.mano.dao.mano.nsd.wan.type.EncapsulationType;
import com.ubiqube.etsi.mano.dao.mano.nsd.wan.type.InterfaceTaggingType;
import com.ubiqube.etsi.mano.dao.mano.nsd.wan.type.InterfaceType;
import com.ubiqube.etsi.mano.dao.mano.nsd.wan.type.VlanTaggingType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Layer2ConnectionInformation implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;
	@Enumerated(EnumType.STRING)
	private ConnectionType connectionType;
	@Enumerated(EnumType.STRING)
	private InterfaceType interfaceType;
	@Enumerated(EnumType.STRING)
	private InterfaceTaggingType interfaceTagging;
	@Enumerated(EnumType.STRING)
	private EncapsulationType encapsulationType;
	@Enumerated(EnumType.STRING)
	private VlanTaggingType vlanTaggingType;

	private WanSegmentId wanSegmentIds;

	private VxLanConfig vxlanConfig;

	private LagInterfaceData lagInterfaceData;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> layer2ControlProtocol;
}
