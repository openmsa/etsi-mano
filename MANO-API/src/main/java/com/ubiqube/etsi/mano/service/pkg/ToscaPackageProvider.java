package com.ubiqube.etsi.mano.service.pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jdt.annotation.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.dao.mano.AdditionalArtifact;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfLinkPort;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.dao.mano.VnfVl;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.event.ProviderData;
import com.ubiqube.parser.tosca.ToscaContext;
import com.ubiqube.parser.tosca.ToscaParser;
import com.ubiqube.parser.tosca.api.ArtefactInformations;
import com.ubiqube.parser.tosca.api.ToscaApi;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import tosca.nodes.nfv.VNF;
import tosca.nodes.nfv.VduCp;
import tosca.nodes.nfv.VnfVirtualLink;
import tosca.nodes.nfv.vdu.Compute;
import tosca.nodes.nfv.vdu.VirtualBlockStorage;
import tosca.nodes.nfv.vdu.VirtualObjectStorage;

public class ToscaPackageProvider implements PackageProvider {

	private static final Logger LOG = LoggerFactory.getLogger(ToscaPackageProvider.class);

	private final ToscaApi toscaApi;
	private final ToscaContext root;
	private final MapperFacade mapper;

	private final ToscaParser toscaParser;

	public ToscaPackageProvider(final byte[] data) {
		final File tempFile = fetchData(data);
		toscaParser = new ToscaParser(tempFile.getAbsolutePath());
		root = toscaParser.getContext();
		toscaApi = new ToscaApi();
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(ProviderData.class, VNF.class)
				.field("vnfProvider", "provider")
				.field("vnfProductName", "productName")
				.field("vnfSoftwareVersion", "softwareVersion")
				.byDefault()
				.register();
		mapperFactory.classMap(ArtefactInformations.class, AdditionalArtifact.class)
				.field("path", "artifactPath")
				.field("checksum", "checksum.hash")
				.field("algorithm", "checksum.algorithm")
				.byDefault()
				.register();
		mapperFactory.classMap(VirtualBlockStorage.class, VnfStorage.class)
				.field("swImageData", "softwareImage")
				.field("virtualBlockStorageData.sizeOfStorage", "size")
				.field("internalName", "toscaName")
				.field("", "myField:{|setType('BLOCK')}")
				.byDefault()
				.register();
		mapperFactory.classMap(VirtualObjectStorage.class, VnfStorage.class)
				.field("virtualObjectStorageData.maxSizeOfStorage", "size")
				.field("internalName", "toscaName")
				.field("", "myField:{|setType('OBJECT')}")
				.byDefault()
				.register();
		mapperFactory.classMap(Compute.class, VnfCompute.class)
				.field("swImageData", "softwareImage")
				.field("internalName", "toscaName")
				.byDefault()
				.register();
		mapperFactory.classMap(VduCp.class, VnfLinkPort.class)
				.field("virtualBindingReq", "virtualBinding")
				.field("virtualLinkReq", "virtualLink")
				.field("internalName", "toscaName")
				.byDefault()
				.register();
		mapperFactory.classMap(VnfVirtualLink.class, VnfVl.class)
				.field("internalName", "toscaName")
				.field("vlProfile", "vlProfileEntity")
				.byDefault()
				.register();
		final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		converterFactory.registerConverter(new SizeConverter());
		converterFactory.registerConverter(new TimeConverter());
		mapper = mapperFactory.getMapperFacade();
	}

	private static File fetchData(final byte[] data) {
		File tempFile;
		try {
			tempFile = File.createTempFile("tosca", ".zip");
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		try (final OutputStream os = new FileOutputStream(tempFile)) {
			os.write(data);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
		return tempFile;
	}

	@Override
	public Set<SoftwareImage> getSoftwareImages() {
		final List<@NonNull Compute> list = toscaApi.getObjects(root, Compute.class);
		LOG.debug("Found {} Compute node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x.getSwImageData(), SoftwareImage.class))
				.collect(Collectors.toSet());
	}

	@Override
	public ProviderData getProviderPadata() {
		final List<@NonNull VNF> vnfs = toscaApi.getObjects(root, VNF.class);
		if (vnfs.isEmpty()) {
			LOG.warn("No VNF node found in the package.");
			return new ProviderData();
		}
		return mapper.map(vnfs.get(0), ProviderData.class);
	}

	@Override
	public Set<AdditionalArtifact> getAdditionalArtefacts() {
		final List<ArtefactInformations> files = toscaParser.getFiles();
		return files.stream().map(x -> mapper.map(x, AdditionalArtifact.class)).collect(Collectors.toSet());
	}

	@Override
	public Set<VnfCompute> getVnfComputeNodes() {
		final List<@NonNull Compute> list = toscaApi.getObjects(root, Compute.class);
		LOG.debug("Found {} Compute node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, VnfCompute.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<VnfStorage> getVnfStorages() {
		final List<@NonNull VirtualBlockStorage> list = toscaApi.getObjects(root, VirtualBlockStorage.class);
		LOG.debug("Found {} Block Storage node in TOSCA model", list.size());
		final Set<@NonNull VnfStorage> res = list.stream()
				.map(x -> mapper.map(x, VnfStorage.class))
				.collect(Collectors.toSet());
		final List<@NonNull VirtualObjectStorage> vos = toscaApi.getObjects(root, VirtualObjectStorage.class);
		LOG.debug("Found {} Object Storage node in TOSCA model", vos.size());
		final Set<@NonNull VnfStorage> resVos = vos.stream()
				.map(x -> mapper.map(x, VnfStorage.class))
				.collect(Collectors.toSet());
		res.addAll(resVos);
		return res;
	}

	@Override
	public Set<VnfVl> getVnfVirtualLinks() {
		final List<@NonNull VnfVirtualLink> list = toscaApi.getObjects(root, VnfVirtualLink.class);
		LOG.debug("Found {} Compute node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, VnfVl.class))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<VnfLinkPort> getVnfVduCp() {
		final List<@NonNull VduCp> list = toscaApi.getObjects(root, VduCp.class);
		LOG.debug("Found {} Compute node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x, VnfLinkPort.class))
				.collect(Collectors.toSet());
	}

}
