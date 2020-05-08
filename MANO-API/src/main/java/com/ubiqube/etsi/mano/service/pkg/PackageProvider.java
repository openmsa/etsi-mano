package com.ubiqube.etsi.mano.service.pkg;

import java.util.Set;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.service.event.ProviderData;

public interface PackageProvider {

	@Nonnull
	Set<SoftwareImage> getSoftwareImages();

	@Nonnull
	ProviderData getProviderPadata();

	@Nonnull
	Set<AdditionalArtifact> getAdditionalArtefacts();

	@Nonnull
	Set<VnfCompute> getVnfComputeNodes();

	@Nonnull
	Set<VnfStorage> getVnfStorages();

	@Nonnull
	Set<VnfVl> getVnfVirtualLinks();

	@Nonnull
	Set<VnfLinkPort> getVnfVduCp();

	@Nonnull
	Set<VnfExtCp> getVnfExtCp();

	@Nonnull
	Set<ScalingAspect> getScalingAspects();

}
