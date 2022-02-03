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
package com.ubiqube.etsi.mano.dao.mano.v2;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.ubiqube.etsi.mano.dao.mano.ExtCpInfo;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.ScaleInfo;
import com.ubiqube.etsi.mano.dao.mano.ScaleTypeEnum;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;
import com.ubiqube.etsi.mano.dao.mano.VirtualStorageResourceInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfMonitoringParameter;
import com.ubiqube.etsi.mano.dao.mano.VnfcResourceInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsHeal;
import com.ubiqube.etsi.mano.dao.mano.nslcm.scale.NsScale;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Embeddable
public class BlueprintParameters implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = 1L;

	String instantiationLevelId;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<ScaleInfo> scaleStatus = null;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn
	private Set<ScaleInfo> maxScaleLevels;

	@Valid
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<ExtManagedVirtualLinkDataEntity> extManagedVirtualLinks = null;

	private Integer numberOfSteps;

	@Enumerated(EnumType.STRING)
	private ScaleTypeEnum scaleType;

	private String aspectId;

	private String flavourId;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NsScale nsScale;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private NsHeal nsHeal;

	@Enumerated(EnumType.STRING)
	private OperationalStateType state;

	private String localizationLanguage;

	@Transient
	private Set<VnfMonitoringParameter> vnfMonitoringParameter;

	@Transient
	private Set<VirtualStorageResourceInfo> virtualStorageResourceInfo;

	@Transient
	private Set<ExtVirtualLinkInfoEntity> extVirtualLinkInfo;

	@Transient
	private Set<ExtCpInfo> extCpInfo;

	@Transient
	private Set<VnfcResourceInfoEntity> vnfcResourceInfo;

	@Transient
	private Set<VirtualLinkInfo> virtualLinkResourceInfo;
}
