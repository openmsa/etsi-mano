package com.ubiqube.etsi.mano.vnfm.v261;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.common.v261.model.vnf.Checksum;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageSoftwareImageInfo;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageSoftwareImageInfo.ContainerFormatEnum;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPackageSoftwareImageInfo.DiskFormatEnum;
import com.ubiqube.etsi.mano.common.v261.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;
import com.ubiqube.etsi.mano.dao.mano.VnfLcmOpOccs;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.AffectedVirtualLink;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.AffectedVnfc;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfLcmOpOcc;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

@Component
public class OrikaMapperVnfm261 implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(final MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(com.ubiqube.etsi.mano.common.v261.model.nslcm.VnfInstance.class, VnfInstance.class)
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
		orikaMapperFactory.classMap(AffectedVirtualLink.class, VnfInstantiatedVirtualLink.class)
				.exclude("audit")
				.field("virtualLinkDescId", "manoResourceId")
				.field("networkResource.resourceId", "resourceId")
				.field("networkResource.resourceProviderId", "resourceProviderId")
				.field("networkResource.vimLevelResourceType", "vimLevelResourceType")
				.field("networkResource.vimConnectionId", "vimConnectionInformation.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(AffectedVnfc.class, VnfInstantiatedCompute.class)
				.exclude("audit")
				.field("computeResource.resourceId", "resourceId")
				.field("computeResource.resourceProviderId", "resourceProviderId")
				.field("computeResource.vimLevelResourceType", "vimLevelResourceType")
				.field("computeResource.vimConnectionId", "vimConnectionInformation.id")
				.byDefault()
				.register();
		orikaMapperFactory.classMap(VnfLcmOpOcc.class, VnfLcmOpOccs.class)
				.field("vnfInstanceId", "vnfInstance.id")
				.byDefault()
				.register();
	}
}
