package com.ubiqube.etsi.mano.service.graph;

import java.util.List;
import java.util.Set;

import org.jgrapht.ListenableGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.controller.nslcm.NsInstanceControllerService;
import com.ubiqube.etsi.mano.dao.mano.InstantiationStatusType;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedNs;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedSap;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedVl;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedVnf;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.NsLcmOpOccsResourceChanges;
import com.ubiqube.etsi.mano.dao.mano.NsSap;
import com.ubiqube.etsi.mano.dao.mano.NsVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.NsdChangeType;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageNsdPackage;
import com.ubiqube.etsi.mano.dao.mano.NsdPackageVnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.factory.NsInstanceFactory;
import com.ubiqube.etsi.mano.jpa.NsdPackageJpa;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.InstantiateNsRequest;
import com.ubiqube.etsi.mano.service.IpamService;
import com.ubiqube.etsi.mano.service.NsInstanceService;
import com.ubiqube.etsi.mano.service.NsLcmOpOccsService;
import com.ubiqube.etsi.mano.service.NsdPackageService;
import com.ubiqube.etsi.mano.service.VnfPackageService;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsEndUow;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsStartUow;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUnitOfWork;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUow;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsVlUow;
import com.ubiqube.etsi.mano.service.graph.nfvo.PnfUow;
import com.ubiqube.etsi.mano.service.graph.nfvo.SapUow;
import com.ubiqube.etsi.mano.service.graph.nfvo.VnfUow;
import com.ubiqube.etsi.mano.service.graph.nfvo.VnffgUow;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.InstantiateVnfRequest;

@Service
public class NsExecutionPlanner {

	private static final Logger LOG = LoggerFactory.getLogger(NsExecutionPlanner.class);

	private final NsInstanceService nsInstanceService;

	private final IpamService ipamService;

	private final NsdPackageJpa nsdPackageJpa;

	private final NsdPackageService nsdPackageService;

	private final VnfmInterface vnfm;

	private final NsInstanceControllerService nsInstanceControllerService;

	private final NsLcmOpOccsService nsLcmOpOccsService;

	private final VnfPackageService vnfPackageService;

	public NsExecutionPlanner(final NsInstanceService nsInstanceService, final IpamService ipamService, final NsdPackageJpa nsdPackageJpa, final NsdPackageService nsdPackageService, final VnfmInterface vnfm, final NsInstanceControllerService _nsInstanceControllerService, final NsLcmOpOccsService _nsLcmOpOccsService, final VnfPackageService _vnfPackageService) {
		super();
		this.nsInstanceService = nsInstanceService;
		this.ipamService = ipamService;
		this.nsdPackageJpa = nsdPackageJpa;
		this.nsdPackageService = nsdPackageService;
		this.vnfm = vnfm;
		nsInstanceControllerService = _nsInstanceControllerService;
		nsLcmOpOccsService = _nsLcmOpOccsService;
		vnfPackageService = _vnfPackageService;
	}

	public void makePrePlan(final NsdInstance nsInstance, final NsdPackage nsdInfo, final NsLcmOpOccs lcmOpOccs) {
		final NsLcmOpOccsResourceChanges changes = lcmOpOccs.getResourceChanges();
		final Set<NsSap> saps = nsInstanceService.findSapsByNsInstance(nsdInfo);
		saps.forEach(x -> {
			final int c = nsInstanceService.countLiveInstanceOfSap(nsInstance, x.getId());
			if (c == 0) {
				final NsInstantiatedSap sap = new NsInstantiatedSap();
				sap.setSapd(x);
				sap.setSapName(x.getToscaName());
				sap.setChangeType(NsdChangeType.ADD);
				changes.addInstantiatedSap(sap);
			}
		});
		final Set<NsVirtualLink> vls = nsInstanceService.findVlsByNsInstance(nsdInfo);
		vls.forEach(x -> {
			final int c = nsInstanceService.countLiveInstanceOfVirtualLink(nsInstance, x.getId());
			if (c == 0) {
				final NsInstantiatedVl sap = new NsInstantiatedVl();
				sap.setNsVirtualLinkDesc(x);
				sap.setVlProfileId(x.getNsVlProfile().getId());
				sap.setChangeType(NsdChangeType.ADD);
				changes.addInstantiatedVirtualLink(sap);
			}
		});
		final Set<NsdPackage> nsds = nsInstanceService.findNestedNsdByNsInstance(nsdInfo);
		nsds.forEach(x -> {
			final int c = nsInstanceService.countLiveInstanceOfNsd(nsInstance, x.getId());
			if (c == 0) {
				// create an instance of x
				final NsdInstance inst = nsInstanceControllerService.createNsd(x.getId().toString(), "nested_of_" + nsInstance.getId(), "");
				final NsInstantiatedNs sap = new NsInstantiatedNs();
				sap.setNsdPackage(x.getId());
				sap.setNsInstanceId(inst.getId().toString());
				sap.setChangeType(NsdChangeType.ADD);
				changes.addInstantiatedNs(sap);
			}
		});
		final Set<VnfPackage> vnfs = nsInstanceService.findVnfPackageByNsInstance(nsdInfo);
		vnfs.forEach(x -> {
			final int c = nsInstanceService.countLiveInstanceOfVnf(nsInstance, x.getId());
			if (c == 0) {
				final NsInstantiatedVnf sap = new NsInstantiatedVnf();
				sap.setChangeType(NsdChangeType.ADD);
				final NsdPackageVnfPackage nsPackageVnfPackage = find(x, nsdInfo.getVnfPkgIds());
				sap.setNsdPackageVnfPackage(nsPackageVnfPackage);
				final VnfInstance vnfmVnfInstance = vnfm.createVnfInstance(x, "VNF instance hold by: " + nsInstance.getId(), x.getId().toString());
				final VnfInstance vnfInstance = NsInstanceFactory.createNsInstancesNsInstanceVnfInstance(vnfmVnfInstance, x);
				vnfInstance.setNsInstance(nsInstance);
				// vnfInstance.setVimConnectionInfo(vimConnectionInfo);
				// vnfInstance.setMetadata(metadata);
				// vnfInstance.setVnfConfigurableProperties(vnfConfigurableProperties);
				sap.setVnfInstance(vnfInstance.getId());
				// XXX Not sure about the profileId is.
				changes.addInstantiatedVnf(sap);
			}
		});
	}

	public ListenableGraph<NsUnitOfWork, ConnectivityEdge<NsUnitOfWork>> plan(final NsLcmOpOccs lcmOpOccs, final NsdInstance nsInstance) {
		final ListenableGraph<NsUnitOfWork, ConnectivityEdge<NsUnitOfWork>> g = GraphTools.createGraph();
		final MultiValueMap<String, NsUnitOfWork> vertex = buildVertex(g, lcmOpOccs, nsInstance);
		final NsdPackage nsdPackage = nsdPackageJpa.findById(nsInstance.getNsdInfo().getId()).orElseThrow(() -> new NotFoundException("" + nsInstance.getNsdInfo().getId()));
		final Set<NsSap> saps = nsdPackageService.getSapByNsdPackage(nsdPackage);
		saps.forEach(x -> GraphTools.addEdge(g, vertex.get(x.getInternalVirtualLink()), vertex.get(x.getToscaName())));
		final Set<NsdPackageVnfPackage> nsdvnf = nsdPackageService.findVnfPackageByNsPackage(nsdPackage);
		nsdvnf.forEach(x -> {
			// An VNF may have a dependency on a VL thru ExtCP
			final VnfPackage vnfp = vnfPackageService.findById(x.getVnfPackage());
			vnfp.getVnfExtCp().forEach(y -> GraphTools.addEdge(g, vertex.get(y.getExternalVirtualLink()), vertex.get(x.getToscaName())));
		});
		final Set<NsdPackageNsdPackage> nsdnsd = nsdPackageService.findNestedNsdByNsdPackage(nsdPackage);
		nsdnsd.forEach(x -> {
			// A NSD may have a dependency on SAP
			final Set<NsSap> nsdSaps = nsdPackageService.getSapByNsdPackageId(x.getChild().getId());
			nsdSaps.forEach(y -> GraphTools.addEdge(g, vertex.get(x.getToscaName()), vertex.get(y.getToscaName())));
		});
		// Add start
		final NsInstantiatedBase vnfInstantiedStart = new NsInstantiatedBase();
		vnfInstantiedStart.setChangeType(NsdChangeType.ADD);
		// vnfInstantiedStart.setVnfLcmOpOccs(vnfLcmOpOccs);
		vnfInstantiedStart.setChangeResult(InstantiationStatusType.NOT_STARTED);
		final NsUnitOfWork root = new NsStartUow(vnfInstantiedStart);
		g.addVertex(root);
		g.vertexSet().stream()
				.filter(key -> g.incomingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != root) {
						LOG.debug("Connecting root to {}", key.getName());
						g.addEdge(root, key);
					}
				});
		// And end Node
		final NsInstantiatedBase vnfInstantiedEnd = new NsInstantiatedBase();
		vnfInstantiedEnd.setChangeType(NsdChangeType.ADD);
		// vnfInstantiedEnd.setVnfLcmOpOccs(vnfLcmOpOccs);
		vnfInstantiedEnd.setChangeResult(InstantiationStatusType.NOT_STARTED);
		final NsUnitOfWork end = new NsEndUow(vnfInstantiedEnd);
		g.addVertex(end);
		g.vertexSet().stream()
				.filter(key -> g.outgoingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != end) {
						g.addEdge(key, end);
					}
				});
		return g;
	}

	public void terminateNsPlan(final NsLcmOpOccs lcmOpOccs, final NsdPackage nsdInfo) {
		final List<NsInstantiatedNs> instantiatedCompute = nsInstanceService.getLiveNsInstanceOf(lcmOpOccs.getNsInstance());
		instantiatedCompute.forEach(x -> {
			final NsInstantiatedNs affectedCompute = copyInstantiedResource(x, new NsInstantiatedNs(), lcmOpOccs);
			affectedCompute.setNsInstanceId(x.getNsInstanceId());
			affectedCompute.setNsdPackage(nsdInfo.getId());
			lcmOpOccs.getResourceChanges().addInstantiatedNs(affectedCompute);
		});
		final List<NsInstantiatedSap> instantiatedSap = nsInstanceService.getLiveSapInstanceOf(lcmOpOccs.getNsInstance());
		instantiatedSap.forEach(x -> {
			final NsInstantiatedSap affectedCompute = copyInstantiedResource(x, new NsInstantiatedSap(), lcmOpOccs);
			affectedCompute.setSapInstanceId(x.getSapInstanceId());
			lcmOpOccs.getResourceChanges().addInstantiatedSap(affectedCompute);
		});
		final List<NsInstantiatedVl> instantiatedVl = nsInstanceService.getLiveVlInstanceOf(lcmOpOccs.getNsInstance());
		instantiatedVl.forEach(x -> {
			final NsInstantiatedVl affectedCompute = copyInstantiedResource(x, new NsInstantiatedVl(), lcmOpOccs);
			affectedCompute.setVlProfileId(x.getVlProfileId());
			lcmOpOccs.getResourceChanges().addInstantiatedVirtualLink(affectedCompute);
		});
		final List<NsInstantiatedVnf> instantiatedVnf = nsInstanceService.getLiveVnfInstanceOf(lcmOpOccs.getNsInstance());
		instantiatedVnf.forEach(x -> {
			final NsInstantiatedVnf affectedCompute = copyInstantiedResource(x, new NsInstantiatedVnf(), lcmOpOccs);
			affectedCompute.setVnfInstance(x.getVnfInstance());
			affectedCompute.setNsdPackageVnfPackage(x.getNsdPackageVnfPackage());
			lcmOpOccs.getResourceChanges().addInstantiatedVnf(affectedCompute);
		});
	}

	private MultiValueMap<String, NsUnitOfWork> buildVertex(final ListenableGraph<NsUnitOfWork, ConnectivityEdge<NsUnitOfWork>> g, final NsLcmOpOccs lcmOpOccs, final NsdInstance nsdInstance) {
		final MultiValueMap<String, NsUnitOfWork> vertex = new LinkedMultiValueMap<>();
		final NsLcmOpOccsResourceChanges resources = lcmOpOccs.getResourceChanges();
		resources.getAffectedNss().forEach(x -> {
			x.getNsdPackage();
			LOG.info("Adding NS vertex of {}", x.getNsInstanceId());
			final InstantiateNsRequest request = new InstantiateNsRequest();
			request.setNsFlavourId(nsdInstance.getFlavourId());
			request.setNsInstantiationLevelId(nsdInstance.getNsInstantiationLevelId());
			final NsUnitOfWork uow = new NsUow(x, request, null, nsInstanceControllerService, nsLcmOpOccsService, x.getNsdPackage().toString());
			vertex.add(x.getNsdPackage().toString(), uow);
			g.addVertex(uow);
		});
		resources.getAffectedPnfs().forEach(x -> {
			LOG.info("Adding PNF vertex of {}", x.getId());
			final NsUnitOfWork uow = new PnfUow(x, "");
			g.addVertex(uow);
			vertex.add(x.getPnfName(), uow);
		});
		resources.getAffectedSaps().stream().forEach(x -> {
			LOG.info("Adding SAP vertex of {}", x.getId());
			final NsUnitOfWork uow = new SapUow(x, "");
			g.addVertex(uow);
			vertex.add(x.getSapName(), uow);
		});
		resources.getAffectedVls().stream().forEach(x -> {
			LOG.info("Adding NSVL vertex of {}", x.getId());
			final NsUnitOfWork uow = new NsVlUow(x, "");
			g.addVertex(uow);
			vertex.add(x.getId().toString(), uow);
		});
		resources.getAffectedVnffgs().forEach(x -> {
			LOG.info("Adding VNFFG vertex of {}", x.getId());
			final NsUnitOfWork uow = new VnffgUow(x, "");
			g.addVertex(uow);
			vertex.add(x.getVnffgdId(), uow);
		});
		resources.getAffectedVnfs().forEach(x -> {
			LOG.info("Adding VNF vertex of {}", x.getNsdPackageVnfPackage().getToscaName());
			final InstantiateVnfRequest request = new InstantiateVnfRequest();
			request.setFlavourId(nsdInstance.getFlavourId());
			request.setInstantiationLevelId(nsdInstance.getNsInstantiationLevelId());
			final NsUnitOfWork uow = new VnfUow(x, request, x.getNsdPackageVnfPackage().getToscaName());
			g.addVertex(uow);
			vertex.add(x.getNsdPackageVnfPackage().getToscaName(), uow);
		});
		return vertex;
	}

	private static <T extends NsInstantiatedBase> T copyInstantiedResource(final NsInstantiatedBase source, final T inst, final NsLcmOpOccs lcmOpOccs) {
		inst.setChangeType(NsdChangeType.REMOVE);
		inst.setChangeResult(InstantiationStatusType.STARTED);
		// inst.setVduId(source.getResourceId());
		// inst.setRemovedInstantiated(source.getId());
		inst.setResourceId(source.getResourceId());
		inst.setInstantiationLevel(source.getInstantiationLevel());
		inst.setResourceId(source.getResourceId());
		inst.setNsLcmOpOccs(lcmOpOccs);
		return inst;
	}

	private static NsdPackageVnfPackage find(final VnfPackage vnfPackage, final Set<NsdPackageVnfPackage> vnfPkgIds) {
		return vnfPkgIds.stream()
				.filter(x -> x.getVnfPackage().getId().compareTo(vnfPackage.getId()) == 0)
				.findFirst().orElseThrow(() -> new NotFoundException("VNF Package not found: " + vnfPackage.getId()));
	}

}
