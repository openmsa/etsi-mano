package com.ubiqube.etsi.mano.vnfm.service.event;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.OnboardingStateType;
import com.ubiqube.etsi.mano.dao.mano.PackageOperationalState;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.VnfPackageJpa;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.pkg.vnf.VnfPackageOnboardingImpl;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfPackageOnboardingNotificationJpa;
import com.ubiqube.etsi.mano.vnfm.service.VnfmVersionManager;

@Service
public class NotificationActions {
	private final VnfPackageJpa vnfPackageJpa;
	private final VnfPackageOnboardingNotificationJpa vnfPackageOnboardingNotificationJpa;
	private final VnfmVersionManager vnfmVersionManager;
	private final VnfPackageRepository vnfPackageRepository;
	private final VnfPackageOnboardingImpl vnfPackageOnboarding;

	public NotificationActions(final VnfPackageJpa vnfPackageJpa, final VnfPackageOnboardingNotificationJpa vnfPackageOnboardingNotificationJpa,
			final VnfmVersionManager vnfmVersionManager, final VnfPackageRepository vnfPackageRepository, final VnfPackageOnboardingImpl vnfPackageOnboarding) {
		super();
		this.vnfPackageJpa = vnfPackageJpa;
		this.vnfPackageOnboardingNotificationJpa = vnfPackageOnboardingNotificationJpa;
		this.vnfmVersionManager = vnfmVersionManager;
		this.vnfPackageRepository = vnfPackageRepository;
		this.vnfPackageOnboarding = vnfPackageOnboarding;
	}

	public void onPkgOnbarding(@NotNull final UUID objectId) {
		final VnfPackageOnboardingNotification event = vnfPackageOnboardingNotificationJpa.findById(objectId).orElseThrow();
		final String pkgId = event.getVnfPkgId();
		final VnfPackage vnfPkg = vnfmVersionManager.findVnfPkgById(pkgId);
		VnfPackage localPackage = getPackage(vnfPkg);
		localPackage = vnfPackageJpa.save(localPackage);
		try {
			final Path file = Files.createTempFile(Paths.get("/tmp/"), "mano", "vnfm");
			vnfmVersionManager.getPackageContent(localPackage.getNfvoId(), file);
			try (FileInputStream fis = new FileInputStream(file.toFile())) {
				vnfPackageRepository.storeBinary(localPackage.getId(), "vnfd", fis);
			}
			Files.delete(file);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		vnfPackageOnboarding.vnfPackagesVnfPkgIdPackageContentPut(localPackage.getId().toString());
	}

	private VnfPackage getPackage(final VnfPackage vnfPkg) {
		final VnfPackage localPackage = new VnfPackage();
		localPackage.setNfvoId(vnfPkg.getId().toString());
		localPackage.setUserDefinedData(vnfPkg.getUserDefinedData());
		localPackage.setOnboardingState(OnboardingStateType.CREATED);
		localPackage.setOperationalState(PackageOperationalState.DISABLED);
		return localPackage;
	}
}
