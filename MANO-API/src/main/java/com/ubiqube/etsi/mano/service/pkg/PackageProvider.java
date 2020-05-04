package com.ubiqube.etsi.mano.service.pkg;

import java.util.Set;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.service.event.ProviderData;

public interface PackageProvider {

	Set<SoftwareImage> getSoftwareImages();

	ProviderData getProviderPadata();

	Set<AdditionalArtifact> getAdditionalArtefacts();

	Set<VnfCompute> getVnfComputeNodes();

	Set<VnfStorage> getVnfStorages();

	Set<VnfVl> getVnfVirtualLinks();

	Set<VnfLinkPort> getVnfVduCp();

	Set<VnfExtCp> getVnfExtCp();

}
