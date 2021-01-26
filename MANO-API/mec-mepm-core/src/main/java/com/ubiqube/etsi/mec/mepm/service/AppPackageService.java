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

import com.ubiqube.etsi.mano.dao.mec.pkg.AppPkg;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mec.repositories.AppPkgJpa;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AppPackageService {
	private final AppPkgJpa appPkgJpa;

	public AppPackageService(final AppPkgJpa _appPkgJpa) {
		appPkgJpa = _appPkgJpa;
	}

	/**
	 * XXX: Could be generic with UUID.
	 *
	 * @param appPkg
	 * @return
	 */
	public AppPkg findById(final AppPkg appPkg) {
		return appPkgJpa.findById(appPkg.getId()).orElseThrow();
	}

	public AppPkg findByAppdId(final String id) {
		return appPkgJpa.findByAppDId(id).orElseThrow(() -> new NotFoundException("Could not find AppPkg with id: " + id));
	}

}
