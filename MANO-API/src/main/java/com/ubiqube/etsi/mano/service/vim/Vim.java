package com.ubiqube.etsi.mano.service.vim;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;

import org.eclipse.jdt.annotation.NonNull;
import org.jgrapht.ListenableGraph;

import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.service.ConnectivityEdge;
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
}