package com.ubiqube.etsi.mano.config;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.dao.mano.AffectedCompute;
import com.ubiqube.etsi.mano.dao.mano.AffectedVl;
import com.ubiqube.etsi.mano.dao.mano.AffectedVs;
import com.ubiqube.etsi.mano.dao.mano.GrantInformation;
import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.GrantsRequest;
import com.ubiqube.etsi.mano.dao.mano.NsdInstance;
import com.ubiqube.etsi.mano.dao.mano.NsdPackage;
import com.ubiqube.etsi.mano.dao.mano.ResourceHandleEntity;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.VirtualLinkInfo;
import com.ubiqube.etsi.mano.dao.mano.VirtualStorageInfo;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.mapper.OffsetDateTimeToDateConverter;
import com.ubiqube.etsi.mano.mapper.OrikaFilterMapper;
import com.ubiqube.etsi.mano.mapper.UuidConverter;
import com.ubiqube.etsi.mano.model.ExtManagedVirtualLinkData;
import com.ubiqube.etsi.mano.model.ResourceHandle;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.GrantRequest;
import com.ubiqube.etsi.mano.model.lcmgrant.sol003.ResourceDefinition;
import com.ubiqube.etsi.mano.model.nsd.sol005.NsdInfo;
import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsInstance;
import com.ubiqube.etsi.mano.model.vnf.SubscriptionObject;
import com.ubiqube.etsi.mano.model.vnf.sol005.Checksum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo.ContainerFormatEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo.DiskFormatEnum;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.ConverterFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class OrikaConfiguration implements OrikaMapperFactoryConfigurer {

	@SuppressWarnings("null")
	@Override
	public void configure(final MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(NsdInfo.class, NsdPackage.class)
				.field("vnfPkgIds{}", "vnfPkgIds{id}")
				.field("pnfdInfoIds{}", "pnfdInfoIds{id}")
				.field("nestedNsdInfoIds{}", "nestedNsdInfoIds{id}")
				.field("userDefinedData{key}", "userDefinedData{name}")
				.field("userDefinedData{value}", "userDefinedData{value}")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(com.ubiqube.etsi.mano.model.nslcm.VnfInstance.class, VnfInstance.class)
				.field("vnfPkgId", "vnfPkg.id")
				.field("vnfConfigurableProperties{key}", "vnfConfigurableProperties{key}")
				.field("vnfConfigurableProperties{value}", "vnfConfigurableProperties{value}")

				.field("metadata{key}", "metadata{key}")
				.field("metadata{value}", "metadata{value}")

				.field("extensions{key}", "extensions{key}")
				.field("extensions{value}", "extensions{value}")
				.byDefault()
				.register();

		orikaMapperFactory.classMap(VnfPackage.class, VnfPkgInfo.class)
				.byDefault()
				.customize(new CustomMapper<VnfPackage, VnfPkgInfo>() {
					@Override
					public void mapAtoB(final VnfPackage a, final VnfPkgInfo b, final MappingContext context) {
						// Remap Images
						final Set<VnfCompute> vnfc = a.getVnfCompute();
						if (null != vnfc) {
							vnfc.forEach(x -> {
								final SoftwareImage img = x.getSoftwareImage();
								if (null != img) {
									b.addSoftwareImagesItem(mapSoftwareImage(img));
								}
							});
						}
						final Set<VnfStorage> storage = a.getVnfStorage();
						if (null != storage) {
							storage.forEach(x -> {
								final SoftwareImage img = x.getSoftwareImage();
								if (null != img) {
									b.addSoftwareImagesItem(mapSoftwareImage(img));
								}
							});
						}

					}

					private VnfPackageSoftwareImageInfo mapSoftwareImage(final SoftwareImage img) {
						final VnfPackageSoftwareImageInfo ret = new VnfPackageSoftwareImageInfo();
						ret.setChecksum(mapChecksum(img.getChecksum()));
						if (null != img.getContainerFormat()) {
							ret.setContainerFormat(ContainerFormatEnum.fromValue(img.getContainerFormat()));
						}
						// ret.setCreatedAt(img.get);
						if (null != img.getDiskFormat()) {
							ret.setDiskFormat(DiskFormatEnum.valueOf(img.getDiskFormat()));
						}
						if (null != img.getId()) {
							ret.setId(img.getId().toString());
						}
						ret.setImagePath(img.getImagePath());
						ret.setMinDisk(img.getMinDisk());
						ret.setMinRam(img.getMinRam());
						ret.setName(img.getName());
						ret.setProvider(img.getProvider());
						ret.setSize(img.getSize());
						// ret.setUserMetadata(img.get);
						ret.setVersion(img.getVersion());
						return ret;
					}

					private Checksum mapChecksum(final com.ubiqube.etsi.mano.dao.mano.common.Checksum checksum) {
						final Checksum ret = new Checksum();
						ret.setAlgorithm(checksum.getAlgorithm());
						ret.setHash(checksum.getHash());
						return ret;
					}
				})
				.register();

		orikaMapperFactory.classMap(VnfLcmOpOcc.class, VnfLcmOpOccs.class)
				.field("vnfInstanceId", "vnfInstance.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(NsInstance.class, NsdInstance.class)
				.field("nestedNsInstanceId{}", "nestedNsInstance{id}")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(SubscriptionObject.class, Subscription.class)
				.field("pkgmSubscription.callbackUri", "subscriptionQuery.callbackUri")
				.fieldMap("pkgmSubscription.filter", "subscriptionFilter").converter("filterConverter").add()
				.field("pkgmSubscription.id", "id")
				.field("subscriptionAuthentication.paramsBasic", "authentificationInformations.authParamBasic")
				.field("subscriptionAuthentication.paramsOauth2ClientCredentials", "authentificationInformations.authParamOath2")
				.field("subscriptionAuthentication.authType[0]", "authentificationInformations.authType")
				.byDefault()
				.register();

		orikaMapperFactory.classMap(ResourceHandle.class, ResourceHandleEntity.class)
				.field("vimConnectionId", "vimConnectionInformation.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(ExtManagedVirtualLinkData.class, ResourceHandleEntity.class)
				.field("vimId", "vimConnectionInformation.id")
				.field("extManagedVirtualLinkId", "vduId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfPackage.class, VnfInstance.class)
				.exclude("audit")
				.exclude("id")
				// .field("id", "vnfPkg.id")
				// .field("vnfCompute", "instantiatedVnfInfo.vnfcResourceInfo")
				// .field("vnfVl", "instantiatedVnfInfo.virtualLinkResourceInfo")
				// .field("vnfStorage", "instantiatedVnfInfo.virtualStorageResourceInfo")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfCompute.class, VnfInstantiedCompute.class)
				.field("id", "vduId")
				.field("id", "compResource.vduId")
				// Don't save this one.field("id", "computeResource.vduId")
				// No this is a VIM Image ID .field("softwareImage.id", "imageId")
				.field("storages", "storageResourceIds")
				.exclude("instantiationLevel")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfVl.class, VirtualLinkInfo.class)
				.field("id", "vnfVirtualLinkDescId")
				.field("id", "networkResource.vduId")
				// Don't map this one.field("id", "grantInformation.vduId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfStorage.class, VirtualStorageInfo.class)
				.field("id", "storageResource.vduId")
				.field("id", "virtualStorageDescId")
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
		orikaMapperFactory.classMap(VnfCompute.class, AffectedCompute.class)
				.field("id", "vduId")
				.field("id", "vnfInstantiedCompute.vduId")
				// No this is a VIM Image ID .field("softwareImage.id", "imageId")
				// XXX .field("storages", "storageResourceIds")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfVl.class, AffectedVl.class)
				.field("id", "virtualLinkDesc.id")
				.field("id", "networkResource.vduId")
				// XXX .field("id", "grantInformation.vduId")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfStorage.class, AffectedVs.class)
				.field("id", "storageResource.vduId")
				.field("id", "virtualStorageDesc.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(GrantInformation.class, GrantInformationExt.class)
				.exclude("id")
				.field("id", "resourceDefinitionId")
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
		orikaMapperFactory.classMap(ResourceDefinition.class, ResourceHandleEntity.class)
				.fieldBToA("id", "id")
				.field("vduId", "vduId")
				.field("resource.vimConnectionId", "vimConnectionInformation.id")
				.field("resource.resourceProviderId", "resourceProviderId")
				.field("resource.resourceId", "resourceId")
				.field("resource.vimLevelResourceType", "vimLevelResourceType")
				.register();
		orikaMapperFactory.classMap(GrantsRequest.class, GrantRequest.class)
				.field("vnfInstance.id", "vnfInstanceId")
				.field("vnfLcmOpOccs.id", "vnfLcmOpOccId")
				.byDefault()
				.register();
		final ConverterFactory converterFactory = orikaMapperFactory.getConverterFactory();
		converterFactory.registerConverter(new UuidConverter());
		converterFactory.registerConverter(new OffsetDateTimeToDateConverter());
		converterFactory.registerConverter("filterConverter", new OrikaFilterMapper());
	}

}
