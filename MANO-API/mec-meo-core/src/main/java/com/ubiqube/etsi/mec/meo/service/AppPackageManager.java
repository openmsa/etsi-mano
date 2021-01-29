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
package com.ubiqube.etsi.mec.meo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.Constants;
import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.dao.mano.common.Checksum;
import com.ubiqube.etsi.mano.dao.mano.dto.AppPkgDto;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppExternalCpd;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppPkg;
import com.ubiqube.etsi.mano.dao.mec.pkg.DNSRuleDescriptor;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDependency;
import com.ubiqube.etsi.mano.dao.mec.pkg.ServiceDescriptor;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.event.NotificationEvent;
import com.ubiqube.etsi.mano.service.pkg.mec.AppPackageProvider;
import com.ubiqube.etsi.mec.meo.event.MeoEventManager;
import com.ubiqube.etsi.mec.meo.event.MeoEventManagerImpl;
import com.ubiqube.etsi.mec.repositories.AppPackageRepository;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AppPackageManager {
	private final AppPackageRepository appPackageRepository;
	private final AppPackagingManager appPackagingManager;
	private final MeoEventManager meoEventManager;
	private final MapperFacade mapper;

	public AppPackageManager(final AppPackageRepository _appPackageRepository, final AppPackagingManager _appPackagingManager, final MeoEventManagerImpl _meoEventManager, final MapperFacade _mapper) {
		appPackageRepository = _appPackageRepository;
		appPackagingManager = _appPackagingManager;
		meoEventManager = _meoEventManager;
		mapper = _mapper;
	}

	public void onboardApp(@NotNull final UUID objectId) {
		final AppPkg app = appPackageRepository.get(objectId);
		final byte[] data = appPackageRepository.getBinary(objectId, "appd");
		startOnboarding(app);
		uploadAndFinishOnboarding(app, data);
	}

	private void uploadAndFinishOnboarding(final AppPkg app, final byte[] data) {
		//
		app.setChecksum(getChecksum(data));
		final AppPackageProvider provider = appPackagingManager.getProviderFor(data);
		if (null != provider) {
			map(provider, app);
		}
		finishOnboarding(app);
		meoEventManager.sendNotification(NotificationEvent.VNF_PKG_ONBOARDING, app.getId());
	}

	private void finishOnboarding(final AppPkg app) {
		app.setOnboardingState(OnboardingStateType.ONBOARDED);
		app.setOperationalState(PackageOperationalState.ENABLED);
		appPackageRepository.save(app);
	}

	private void map(final AppPackageProvider provider, final AppPkg app) {
		final AppPkgDto meaPkg = provider.getMea();
		mapper.map(meaPkg, app);
		final Set<DNSRuleDescriptor> appDNSRule = provider.getDnsRuleDescriptors(new HashMap<>());
		app.setAppDNSRule(appDNSRule);

		final Set<ServiceDescriptor> appServiceProduced = provider.getServiceProduced(new HashMap<>());
		app.setAppServiceProduced(appServiceProduced);

		final Set<ServiceDependency> appServiceOptional = provider.getOptionalServiceDependency(new HashMap<>());
		app.setAppServiceOptional(appServiceOptional);

		final Set<ServiceDependency> appServiceRequired = provider.getRequiredServiceDependency(new HashMap<>());
		app.setAppServiceRequired(appServiceRequired);

		final Set<AppExternalCpd> extCp = provider.getExtCp(new HashMap<>());
		app.setAppExtCpd(extCp);

		final Set<VnfVl> vl = provider.getVl(new HashMap<>());
		app.setVnfVl(vl);

	}

	private static Checksum getChecksum(final byte[] bytes) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(Constants.HASH_ALGORITHM);
		} catch (final NoSuchAlgorithmException e) {
			throw new GenericException(e);
		}
		final byte[] hashbytes = digest.digest(bytes);
		final String sha3_256hex = bytesToHex(hashbytes);
		final Checksum checksum = new Checksum();

		checksum.setAlgorithm(Constants.HASH_ALGORITHM);
		checksum.setHash(sha3_256hex);
		return checksum;
	}

	private void startOnboarding(final AppPkg app) {
		app.setOnboardingState(OnboardingStateType.PROCESSING);
		appPackageRepository.save(app);
	}

	private static String bytesToHex(final byte[] hash) {
		final StringBuilder hexString = new StringBuilder();
		for (final byte element : hash) {
			final String hex = Integer.toHexString(0xff & element);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}
