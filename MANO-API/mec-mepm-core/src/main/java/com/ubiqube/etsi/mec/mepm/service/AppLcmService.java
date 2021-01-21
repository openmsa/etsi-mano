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

import com.ubiqube.etsi.mano.dao.mec.lcm.AppBluePrint;
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

	public AppBluePrintJpa getAppBluePrintJpa() {
		return appBluePrintJpa;
	}

	public void setAppBluePrintJpa(final AppBluePrintJpa appBluePrintJpa) {
		this.appBluePrintJpa = appBluePrintJpa;
	}

	public AppBluePrint createTerminateOpOcc(final AppInstance vnfInstance) {
		final AppBluePrint appBluePrint = new AppBluePrint();
		return appBluePrintJpa.save(appBluePrint);
	}

	public AppBluePrint createOperateOpOcc(final AppInstance vnfInstance, final VnfOperateRequest req) {
		final AppBluePrint appBluePrint = new AppBluePrint();
		return appBluePrintJpa.save(appBluePrint);
	}

	public AppBluePrint createIntatiateOpOcc(final AppInstance vnfInstance) {
		final AppBluePrint appBluePrint = new AppBluePrint();
		return appBluePrintJpa.save(appBluePrint);
	}

}
