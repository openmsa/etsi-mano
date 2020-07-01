package com.ubiqube.etsi.mano.nfvo.v261;

import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.common.v261.model.nslcm.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfExtCpInfo;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfVirtualLinkResourceInfo;
import com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfcResourceInfo;
import com.ubiqube.etsi.mano.dao.mano.ExtManagedVirtualLinkDataEntity;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedVnf;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.ConstraintResourceRef;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.ResourceDefinition;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.AffectedVnf;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.InstantiateNsRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nslcm.NsInstance;

import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class OrikaConfigurationNfvo261 implements OrikaMapperFactoryConfigurer {
	@Override
	public void configure(final MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(NsdInfo.class, NsdPackage.class)
				.field("vnfPkgIds{}", "vnfPkgIds{id}")
				.field("pnfdInfoIds{}", "pnfdInfoIds{id}")
				.field("nestedNsdInfoIds{}", "nestedNsdInfoIds{id}")
				.byDefault()
				.register();

		orikaMapperFactory.classMap(NsInstance.class, NsdInstance.class)
				.field("nestedNsInstanceId{}", "nestedNsInstance{id}")
				.field("nsdId", "nsdInfo.nsdId")
				.field("nsdInfoId", "nsdInfo.id")
				.byDefault()
				.register();

		orikaMapperFactory.classMap(ResourceDefinition.class, GrantInformationExt.class)
				.exclude("id")
				.field("id", "resourceDefinitionId")
				.field("type", "type")
				.field("vduId", "vduId")
				.register();
		orikaMapperFactory.classMap(ResourceDefinition.class, GrantInformation.class)
				.fieldBToA("id", "id")
				.field("resource.vimConnectionId", "vimConnectionId")
				.field("resource.resourceProviderId", "resourceProviderId")
				.field("type", "type")
				.field("vduId", "vduId")
				.byDefault()
				.register();

		orikaMapperFactory.classMap(GrantsRequest.class, GrantRequest.class)
				.field("vnfLcmOpOccs.vnfInstance.id", "vnfInstanceId")
				.field("vnfLcmOpOccs.id", "vnfLcmOpOccId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(InstantiateNsRequest.class, NsdInstance.class)
				.field("nsFlavourId", "flavourId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(ExtManagedVirtualLinkData.class, ExtManagedVirtualLinkDataEntity.class)
				.field("vmfVirtualLinkDescId", "vnfVirtualLinkDescId")
				.field("vimId", "vimConnectionId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(AffectedVnf.class, NsInstantiatedVnf.class)
				.field("vnfdId", "")
				.field("vnfInstanceId", "vnfInstance")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(GrantRequest.class, GrantResponse.class)
				.field("vimConstraints[0].resource", "vimConnections")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(ConstraintResourceRef.class, VimConnectionInformation.class)
				.field("vimConnectionId", "vimId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfcResourceInfo.class, VnfInstantiatedCompute.class)
				.field("computeResource.resourceId", "resourceId")
				.field("computeResource.resourceProviderId", "resourceProviderId")
				.field("computeResource.vimConnectionId", "vimConnectionInformation.vimId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfExtCpInfo.class, VnfInstantiatedExtCp.class)
				.field("cpdId", "toscaName")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfVirtualLinkResourceInfo.class, VnfInstantiatedVirtualLink.class)
				.field("networkResource.resourceId", "resourceId")
				.field("networkResource.resourceProviderId", "resourceProviderId")
				.field("networkResource.vimConnectionId", "vimConnectionInformation.vimId")
				.field("vnfVirtualLinkDescId", "toscaName")
				.byDefault()
				.register();
	}
}
