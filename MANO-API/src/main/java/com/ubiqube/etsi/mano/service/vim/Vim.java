package com.ubiqube.etsi.mano.service.vim;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.eclipse.jdt.annotation.NonNull;
import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.UnitOfWork;

public interface Vim {

	String onVnfInstanceTerminate(Map<String, String> userData);

	String onVnfInstantiate(GrantInformation grantInformation, VnfPackage vnfPackage);

	String onNsInstantiate(String nsdId, Map<String, Object> userData);

	String onNsInstanceTerminate(String processId, Map<String, Object> userData);

	@Nonnull
	VimStatus waitForCompletion(String processId, int seconds);

	void allocateResources(VimConnectionInformation vimConnectionInformation, GrantInformation grantInformation);

	void freeResources(VimConnectionInformation vimConnectionInformation, GrantInformation grantInformation);

	String getType();

	@NonNull
	VimImage getImagesInformations(VimConnectionInformation vimConnectionInformation, String name);

	String createNetwork(final VimConnectionInformation vimConnectionInformation, final VlProtocolData vl, String name);

	void refineExecutionPlan(@Nonnull final ListenableGraph<UnitOfWork, ConnectivityEdge> g);

	Optional<SoftwareImage> getSwImageMatching(VimConnectionInformation vimConnectionInformation, SoftwareImage img);

	SoftwareImage uploadSoftwareImage(VimConnectionInformation vimConnectionInformation, SoftwareImage img);

	@Nonnull
	String getOrCreateFlavor(VimConnectionInformation vimConnectionInformation, String name, int numVcpu, long virtualMemorySize, long disk);

	String createStorage(VimConnectionInformation vimConnectionInformation, VnfStorage vnfStorage);

	String createCompute(VimConnectionInformation vimConnectionInformation, VnfCompute vnfCompute, String flavorId, String imageId, List<String> networks, List<String> storages);

	String createObjectStorage(final VimConnectionInformation vimConnectionInformation, final VnfStorage vnfStorage);

	List<String> getZoneAvailableList(VimConnectionInformation vimConnectionInformation);

	void deleteCompute(VimConnectionInformation vimConnectionInformation, String resourceId);

	void deleteVirtualLink(VimConnectionInformation vimConnectionInformation, String resourceId);

	void deleteStorage(VimConnectionInformation vimConnectionInformation, String resourceId);

	void deleteObjectStorage(VimConnectionInformation vimConnectionInformation, String resourceId);
}