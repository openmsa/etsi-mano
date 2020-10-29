package com.ubiqube.etsi.mano.config;

import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.mapper.OffsetDateTimeToDateConverter;
import com.ubiqube.etsi.mano.mapper.OrikaFilterMapper;
import com.ubiqube.etsi.mano.mapper.UuidConverter;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class OrikaConfiguration implements OrikaMapperFactoryConfigurer {

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

		orikaMapperFactory.classMap(GrantResponse.class, GrantsRequest.class)
				.field("vnfInstanceId", "vnfInstance.id")
				.field("vnfLcmOpOccId", "vnfLcmOpOccs.id")
				.byDefault()
				.register();
		/*
		 * orikaMapperFactory.classMap(com.ubiqube.etsi.mano.model.nslcm.VnfInstance.
		 * class, VnfInstance.class) .field("vimId", "vimConnectionInfo{vimId}")
		 * .byDefault() .register();
		 */
		final ConverterFactory converterFactory = orikaMapperFactory.getConverterFactory();
		converterFactory.registerConverter(new UuidConverter());
		converterFactory.registerConverter(new OffsetDateTimeToDateConverter());
		converterFactory.registerConverter("filterConverter", new OrikaFilterMapper());
	}

}
