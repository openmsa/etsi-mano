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
package com.ubiqube.etsi.mec.controller.pkg;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppPkg;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.repository.jpa.SearchQueryer;
import com.ubiqube.etsi.mano.service.event.ActionType;
import com.ubiqube.etsi.mec.meo.event.MeoEventManager;
import com.ubiqube.etsi.mec.repositories.AppPackageRepository;
import com.ubiqube.etsi.mec.repositories.AppPkgJpa;

@Service
public class AppPackageMeoControllerImpl implements AppPackageMeoController {
	private final AppPkgJpa appPkgJpa;
	private final EntityManager em;
	private final AppPackageRepository appPackageRepository;
	private final MeoEventManager eventManager;

	public AppPackageMeoControllerImpl(final AppPkgJpa _appPkgJpa, final EntityManager _em, final AppPackageRepository _appPackageRepository, final MeoEventManager _eventManager) {
		appPkgJpa = _appPkgJpa;
		em = _em;
		appPackageRepository = _appPackageRepository;
		eventManager = _eventManager;
	}

	@Override
	public AppPkg findById(final UUID id) {
		return appPkgJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find AppPkg with id: " + id));
	}

	@Override
	public AppPkg save(final AppPkg appPkg) {
		return appPackageRepository.save(appPkg);
	}

	@Override
	public AppPkg getAppd(final UUID appPkgId) {
		return appPkgJpa.findById(appPkgId).orElseThrow(() -> new NotFoundException("Could not find AppPkg with id: " + appPkgId));
	}

	@Override
	public AppPkg setOperationState(final UUID fromString, @NotNull final OperationalStateType operationState) {
		final AppPkg app = appPkgJpa.findById(fromString).orElseThrow();
		app.setOperationalState(PackageOperationalState.valueOf(operationState.toString()));
		return appPkgJpa.save(app);
	}

	@Override
	public List<AppPkg> query(final String filter) {
		final SearchQueryer sq = new SearchQueryer(em);
		return sq.getCriteria(filter, AppPkg.class);
	}

	@Override
	public ResponseEntity<Resource> getPackage(final UUID fromString) {
		final byte[] content = appPackageRepository.getBinary(fromString, "appd");
		final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
		final BodyBuilder bodyBuilder = ResponseEntity.ok();
		return bodyBuilder.body(resource);
	}

	@Override
	public void deleteById(final UUID fromString) {
		appPkgJpa.deleteById(fromString);
	}

	@Override
	public void store(final UUID fromString, final byte[] bytes) {
		final ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		final AppPkg appPkg = appPkgJpa.findById(fromString).orElseThrow(() -> new NotFoundException("App package " + fromString + " could not be found."));
		appPackageRepository.storeBinary(fromString, "appd", bais);
		appPkg.setOnboardingState(OnboardingStateType.ONBOARDED);
		appPackageRepository.save(appPkg);
		eventManager.sendMeoEvent(ActionType.MEO_ONBOARDING, fromString, new HashMap<>());
	}

}
