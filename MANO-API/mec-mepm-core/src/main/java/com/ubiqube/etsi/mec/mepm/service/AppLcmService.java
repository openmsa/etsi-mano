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
package com.ubiqube.etsi.mec.mepm.service;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mec.lcm.AppBlueprint;
import com.ubiqube.etsi.mano.dao.mec.lcm.AppInstance;
import com.ubiqube.etsi.mano.model.VnfOperateRequest;
import com.ubiqube.etsi.mec.mepm.repositories.AppBluePrintJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AppLcmService {
	private AppBluePrintJpa appBluePrintJpa;

	public AppLcmService(final AppBluePrintJpa _appBluePrintJpa) {
		appBluePrintJpa = _appBluePrintJpa;
	}

	public void setAppBluePrintJpa(final AppBluePrintJpa appBluePrintJpa) {
		this.appBluePrintJpa = appBluePrintJpa;
	}

	public AppBlueprint createTerminateOpOcc(final AppInstance vnfInstance) {
		final AppBlueprint appBluePrint = new AppBlueprint();
		return appBluePrintJpa.save(appBluePrint);
	}

	public AppBlueprint createOperateOpOcc(final AppInstance vnfInstance, final VnfOperateRequest req) {
		final AppBlueprint appBluePrint = new AppBlueprint();
		return appBluePrintJpa.save(appBluePrint);
	}

	public AppBlueprint createIntatiateOpOcc(final AppInstance vnfInstance) {
		final AppBlueprint appBluePrint = new AppBlueprint();
		return appBluePrintJpa.save(appBluePrint);
	}

}
