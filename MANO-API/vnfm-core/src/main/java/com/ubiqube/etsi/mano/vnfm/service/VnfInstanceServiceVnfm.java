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
package com.ubiqube.etsi.mano.vnfm.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ExtCpInfo;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.ExtVirtualLinkInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.InstantiationState;
import com.ubiqube.etsi.mano.dao.mano.VimResource;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;
import com.ubiqube.etsi.mano.dao.mano.VirtualStorageResourceInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfLiveInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfMonitoringParameter;
import com.ubiqube.etsi.mano.dao.mano.VnfcResourceInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.VnfcResourceInfoVnfcCpInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.v2.BlueprintParameters;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.ExternalCpTask;
import com.ubiqube.etsi.mano.dao.mano.v2.MonitoringTask;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.StorageTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfPortTask;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.jpa.VnfInstanceJpa;
import com.ubiqube.etsi.mano.service.VnfInstanceGatewayService;
import com.ubiqube.etsi.mano.vnfm.jpa.VnfLiveInstanceJpa;
import com.ubiqube.etsi.mano.vnfm.service.graph.DefaultVduNamingStrategy;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class VnfInstanceServiceVnfm implements VnfInstanceGatewayService {

	private final VnfInstanceJpa vnfInstanceJpa;

	private final VnfLiveInstanceJpa vnfLiveInstanceJpa;

	private final MapperFacade mapper;

	private final DefaultVduNamingStrategy namingStrategy;

	public VnfInstanceServiceVnfm(final VnfInstanceJpa vnfInstanceJpa, final VnfLiveInstanceJpa vnfLiveInstanceJpa, final MapperFacade mapper, final DefaultVduNamingStrategy namingStrategy) {
		this.vnfInstanceJpa = vnfInstanceJpa;
		this.vnfLiveInstanceJpa = vnfLiveInstanceJpa;
		this.mapper = mapper;
		this.namingStrategy = namingStrategy;
	}

	@Override
	public VnfInstance findById(final UUID id) {
		final VnfInstance inst = vnfInstanceJpa.findById(id).orElseThrow(() -> new NotFoundException("Could not find VNF instance: " + id));
		final BlueprintParameters vnfInfo = inst.getInstantiatedVnfInfo();
		final List<VnfLiveInstance> vli = vnfLiveInstanceJpa.findByVnfInstance(inst);
		extractCompute(vnfInfo, vli);
		extractExtCp(vnfInfo, vli);
		extractStorage(vnfInfo, vli);
		extractVl(vnfInfo, vli);
		extractExtVl(vnfInfo, vli);
		extractMonitoring(vnfInfo, vli);
		extractExtVirtualLinkInfo(vnfInfo, vli);
		inst.setInstantiationState(isLive(id));
		return inst;
	}

	private void extractExtVirtualLinkInfo(final BlueprintParameters vnfInfo, final List<VnfLiveInstance> vliAll) {
		final List<VnfLiveInstance> vli = vliAll.stream().filter(x -> x.getTask() instanceof VnfPortTask).toList();
		final Set<ExtVirtualLinkInfoEntity> obj = vli.stream().map(x -> {
			final VnfPortTask vpt = (VnfPortTask) x.getTask();
			final ExtVirtualLinkInfoEntity elie = new ExtVirtualLinkInfoEntity();
			elie.setId(x.getId());
			final VimResource handle = new VimResource();
			handle.setResourceId(x.getResourceId());
			handle.setResourceProviderId(vpt.getResourceProviderId());
			handle.setVimConnectionId(x.getVimConnectionId());
			handle.setVimLevelResourceType(null);
			elie.setResourceHandle(handle);
			return elie;
		}).collect(Collectors.toSet());
		vnfInfo.setExtVirtualLinkInfo(obj);
	}

	private void extractMonitoring(final BlueprintParameters vnfInfo, final List<VnfLiveInstance> vliAll) {
		final List<VnfLiveInstance> vli = vliAll.stream().filter(x -> x.getTask() instanceof MonitoringTask).toList();
		final Set<VnfMonitoringParameter> obj = vli.stream().map(x -> {
			final VnfMonitoringParameter mon = new VnfMonitoringParameter();
			final MonitoringTask mt = (MonitoringTask) x.getTask();
			mon.setId(x.getId());
			mon.setName(mt.getMonitoringParams().getName());
			mon.setPerformanceMetric(mt.getMonitoringParams().getPerformanceMetric());
			return mon;
		}).collect(Collectors.toSet());
		vnfInfo.setVnfMonitoringParameter(obj);
	}

	private void extractExtVl(final BlueprintParameters vnfInfo, final List<VnfLiveInstance> vliAll) {
		final List<VnfLiveInstance> vli = vliAll.stream().filter(x -> x.getTask() instanceof ExternalCpTask).toList();
		final Set<ExtManagedVirtualLinkDataEntity> obj = vli.stream().map(x -> {
			final ExtManagedVirtualLinkDataEntity ret = new ExtManagedVirtualLinkDataEntity();
			final ExternalCpTask ect = (ExternalCpTask) x.getTask();
			ret.setId(x.getId());
			ret.setResourceId(x.getResourceId());
			ret.setResourceProviderId(ect.getResourceProviderId());
			return ret;
		}).collect(Collectors.toSet());
		vnfInfo.setExtManagedVirtualLinks(obj);
	}

	private void extractVl(final BlueprintParameters vnfInfo, final List<VnfLiveInstance> vliAll) {
		final List<VnfLiveInstance> vli = vliAll.stream().filter(x -> x.getTask() instanceof NetworkTask).toList();
		final Set<VirtualLinkInfo> obj = vli.stream().map(x -> {
			final NetworkTask nt = (NetworkTask) x.getTask();
			final VirtualLinkInfo ret = new VirtualLinkInfo();
			ret.setId(x.getId());
			ret.setVnfVirtualLinkDescId(nt.getToscaName());
			final VimResource vimResource = new VimResource();
			vimResource.setResourceId(x.getResourceId());
			vimResource.setResourceProviderId(nt.getResourceProviderId());
			vimResource.setVimConnectionId(x.getVimConnectionId());
			vimResource.setVimLevelResourceType(null);
			ret.setNetworkResource(vimResource);
			ret.setVnfLinkPorts(null);
			return ret;
		}).collect(Collectors.toSet());
		vnfInfo.setVirtualLinkResourceInfo(obj);
	}

	private void extractStorage(final BlueprintParameters vnfInfo, final List<VnfLiveInstance> vli) {
		final List<VnfLiveInstance> storageVli = vli.stream().filter(x -> x.getTask() instanceof StorageTask).toList();
		final Set<VirtualStorageResourceInfo> storages = storageVli.stream().map(x -> {
			final VirtualStorageResourceInfo ret = new VirtualStorageResourceInfo();
			ret.setReservationId(null);
			final VimResource vimResource = new VimResource();
			vimResource.setResourceId(x.getResourceId());
			vimResource.setVimConnectionId(x.getVimConnectionId());
			vimResource.setResourceProviderId(x.getTask().getResourceProviderId());
			ret.setId(x.getId());
			ret.setStorageResource(vimResource);
			ret.setVirtualStorageDescId(x.getTask().getToscaName());
			ret.setVnfdId(null);
			ret.setZoneId(null);
			return ret;
		}).collect(Collectors.toSet());
		vnfInfo.setVirtualStorageResourceInfo(storages);
	}

	private void extractExtCp(final BlueprintParameters vnfInfo, final List<VnfLiveInstance> vli) {
		final List<VnfLiveInstance> portVli = vli.stream().filter(x -> x.getTask() instanceof VnfPortTask).toList();
		final Set<ExtCpInfo> extCp = portVli.stream().map(x -> {
			final VnfPortTask vpt = (VnfPortTask) x.getTask();
			final VnfLinkPort vlp = vpt.getVnfLinkPort();
			final ExtCpInfo ret = new ExtCpInfo();
			ret.setId(x.getId());
			ret.setAssociatedVnfcCpId(null); // This is one
			ret.setAssociatedVnfVirtualLinkId(getPort(portVli, vlp.getToscaName()));
			ret.setCpConfigId(null);
			ret.setCpdId(vlp.getVirtualLink());
			ret.setCpProtocolInfo(null);
			ret.setExtLinkPortId(null);
			return ret;
		}).collect(Collectors.toSet());
		vnfInfo.setExtCpInfo(extCp);
	}

	private String getPort(final List<VnfLiveInstance> portVli, final String toscaName) {
		return portVli.stream()
				.filter(x -> toscaName.equals(((VnfPortTask) x.getTask()).getVnfLinkPort().getToscaName()))
				.findFirst()
				.map(x -> x.getId().toString())
				.orElse(null);
	}

	private void extractCompute(final BlueprintParameters vnfInfo, final List<VnfLiveInstance> vli) {
		final List<VnfLiveInstance> computeVli = vli.stream().filter(x -> x.getTask() instanceof ComputeTask).toList();
		final Set<VnfcResourceInfoEntity> vnfcResourceInfo = computeVli.stream().map(x -> {
			final ComputeTask ct = (ComputeTask) x.getTask();
			final VnfcResourceInfoEntity ret = mapper.map(x, VnfcResourceInfoEntity.class);
			ret.setComputeResource(mapper.map(x.getTask(), VimResource.class));
			ret.setId(x.getId().toString());
			ret.setStorageResourceIds(ct.getVnfCompute().getStorages());
			ret.setVduId(ct.getToscaName());
			ret.setZoneId(ct.getZoneId());
			ret.getComputeResource().setResourceId(x.getResourceId());
			final Set<VnfcResourceInfoVnfcCpInfoEntity> cpInfo = extractCpInfo(ct, vli);
			ret.setVnfcCpInfo(cpInfo);
			return ret;
		}).collect(Collectors.toSet());
		vnfInfo.setVnfcResourceInfo(vnfcResourceInfo);
	}

	private Set<VnfcResourceInfoVnfcCpInfoEntity> extractCpInfo(final ComputeTask ct, final List<VnfLiveInstance> vli) {
		return ct.getVnfCompute().getPorts().stream()
				.map(x -> {
					final String livePortName = namingStrategy.nameConnectionPort(x, ct);
					final VnfLiveInstance vp = findPort(vli, livePortName);
					final VnfcResourceInfoVnfcCpInfoEntity ret = new VnfcResourceInfoVnfcCpInfoEntity();
					final VnfPortTask vpt = (VnfPortTask) vp.getTask();
					ret.setCpdId(vpt.getVnfLinkPort().getToscaName());
					ret.setCpProtocolInfo(null);
					ret.setId(vp.getId().toString());
					ret.setParentCpId(null);
					if (vpt.getVnfLinkPort().getVirtualLink().startsWith("virtual_link")) {
						ret.setVnfExtCpId(vp.getId().toString());
					} else {
						ret.setVnfLinkPortId(vp.getId().toString());
					}
					return ret;
				})
				.collect(Collectors.toSet());
	}

	private VnfLiveInstance findPort(final List<VnfLiveInstance> vli, final String livePortName) {
		return vli.stream().filter(x -> x.getTask().getToscaName().equals(livePortName)).findFirst().orElseThrow();
	}

	private InstantiationState isLive(final UUID id) {
		return vnfLiveInstanceJpa.findByVnfInstanceId(id).isEmpty() ? InstantiationState.NOT_INSTANTIATED : InstantiationState.INSTANTIATED;
	}

}
