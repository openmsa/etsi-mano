package com.ubiqube.etsi.mano.service.pkg;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.ScalingAspect;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.service.event.ProviderData;

import tosca.policies.nfv.InstantiationLevels;
import tosca.policies.nfv.VduInitialDelta;
import tosca.policies.nfv.VduInstantiationLevels;
import tosca.policies.nfv.VduScalingAspectDeltas;

public interface PackageProvider {

	@Nonnull
	Set<SoftwareImage> getSoftwareImages(Map<String, String> parameters);

	@Nonnull
	ProviderData getProviderPadata();

	@Nonnull
	Set<AdditionalArtifact> getAdditionalArtefacts(Map<String, String> parameters);

	@Nonnull
	Set<VnfCompute> getVnfComputeNodes(Map<String, String> parameters);

	@Nonnull
	Set<VnfStorage> getVnfStorages(Map<String, String> parameters);

	@Nonnull
	Set<VnfVl> getVnfVirtualLinks(Map<String, String> parameters);

	@Nonnull
	Set<VnfLinkPort> getVnfVduCp(Map<String, String> parameters);

	@Nonnull
	Set<VnfExtCp> getVnfExtCp(Map<String, String> parameters);

	@Nonnull
	Set<ScalingAspect> getScalingAspects(Map<String, String> parameters);

	@Nonnull
	List<InstantiationLevels> getInstatiationLevels(Map<String, String> parameters);

	@Nonnull
	List<VduInstantiationLevels> getVduInstantiationLevels(Map<String, String> parameters);

	@Nonnull
	List<VduInitialDelta> getVduInitialDelta(Map<String, String> parameters);

	@Nonnull
	List<VduScalingAspectDeltas> getVduScalingAspectDeltas(Map<String, String> parameters);

	@Nonnull
	NsInformations getNsInformations(Map<String, String> userData);

	@Nonnull
	Set<NsVirtualLink> getNsVirtualLink(Map<String, String> userData);

	@Nonnull
	Set<NsSap> getNsSap(Map<String, String> userData);

	@Nonnull
	Set<SecurityGroupAdapter> getSecurityGroups(Map<String, String> userData);

}
