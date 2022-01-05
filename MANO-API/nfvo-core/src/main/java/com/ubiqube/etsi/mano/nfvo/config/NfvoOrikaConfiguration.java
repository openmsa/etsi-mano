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
package com.ubiqube.etsi.mano.nfvo.config;

import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.VimResource;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfcResourceInfoEntity;
import com.ubiqube.etsi.mano.dao.mano.dto.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfGrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.dto.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.dto.nsi.NsInstanceDto;
import com.ubiqube.etsi.mano.dao.mano.dto.nsi.NsInstantiate;
import com.ubiqube.etsi.mano.dao.mano.v2.ComputeTask;
import com.ubiqube.etsi.mano.dao.mano.v2.DnsZoneTask;
import com.ubiqube.etsi.mano.dao.mano.v2.NetworkTask;
import com.ubiqube.etsi.mano.dao.mano.v2.StorageTask;
import com.ubiqube.etsi.mano.dao.mano.v2.VnfBlueprint;
import com.ubiqube.etsi.mano.mapper.OffsetDateTimeToDateConverter;
import com.ubiqube.etsi.mano.mapper.OrikaFilterMapper;
import com.ubiqube.etsi.mano.mapper.UuidConverter;
import com.ubiqube.etsi.mano.model.VnfInstantiate;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class NfvoOrikaConfiguration implements OrikaMapperFactoryConfigurer {

	@SuppressWarnings("null")
	@Override
	public void configure(final MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(VnfPackage.class, VnfInstance.class)
				.exclude("audit")
				.exclude("id")
				.exclude("nsInstance")
				// .field("id", "vnfPkg.id")
				// .field("vnfCompute", "instantiatedVnfInfo.vnfcResourceInfo")
				// .field("vnfVl", "instantiatedVnfInfo.virtualLinkResourceInfo")
				// .field("vnfStorage", "instantiatedVnfInfo.virtualStorageResourceInfo")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfCompute.class, VnfInstantiatedCompute.class)
				.field("id", "vduId")
				// Don't save this one.field("id", "computeResource.vduId")
				// No this is a VIM Image ID .field("softwareImage.id", "imageId")
				.field("storages", "storageResourceIds")
				.exclude("instantiationLevel")
				.byDefault()
				.register();

		orikaMapperFactory.classMap(VnfPackage.class, VnfLcmOpOccs.class)
				.exclude("audit")
				.exclude("id")
				.field("vnfCompute", "resourceChanges.affectedVnfcs")
				.field("vnfVl", "resourceChanges.affectedVirtualLinks")
				.field("vnfStorage", "resourceChanges.affectedVirtualStorages")
				.byDefault()
				.register();

		orikaMapperFactory.classMap(GrantInformation.class, GrantInformationExt.class)
				.exclude("id")
				.field("id", "resourceDefinitionId")
				.byDefault()
				.register();

		orikaMapperFactory.classMap(GrantResponse.class, VnfGrantsRequest.class)
				.field("vnfInstanceId", "vnfInstance.id")
				.field("vnfLcmOpOccId", "vnfLcmOpOccs.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(GrantInformation.class, ComputeTask.class)
				.field("vduId", "vnfCompute.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(GrantInformation.class, NetworkTask.class)
				.field("vduId", "vnfVl.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(GrantInformation.class, DnsZoneTask.class)
				.byDefault()
				.register();
		orikaMapperFactory.classMap(GrantInformation.class, StorageTask.class)
				.field("vduId", "vnfStorage.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfGrantsRequest.class, VnfBlueprint.class)
				.exclude("vnfInstance")
				.field("vnfInstance.id", "vnfInstance.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfInstantiate.class, VnfBlueprint.class)
				.field("vimConnectionInfo", "vimConnections")
				.field("instantiationLevelId", "parameters.instantiationLevelId")
				.field("flavourId", "parameters.flavourId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(NsInstantiate.class, NsdInstance.class)
				.field("nsFlavourId", "instanceFlavourId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(NsInstanceDto.class, NsdInstance.class)
				.field("flavourId", "instanceFlavourId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfcResourceInfoEntity.class, ComputeTask.class)
				.field("vduId", "vnfCompute.toscaName")
				.field("storageResourceIds", "vnfCompute.storages")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VimResource.class, ComputeTask.class)
				.field("resourceId", "vimResourceId")
				.byDefault()
				.register();
		final ConverterFactory converterFactory = orikaMapperFactory.getConverterFactory();
		converterFactory.registerConverter(new UuidConverter());
		converterFactory.registerConverter(new OffsetDateTimeToDateConverter());
		converterFactory.registerConverter("filterConverter", new OrikaFilterMapper());
	}

}
