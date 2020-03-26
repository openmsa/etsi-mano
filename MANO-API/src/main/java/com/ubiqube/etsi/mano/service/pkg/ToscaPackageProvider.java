package com.ubiqube.etsi.mano.service.pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.annotation.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageArtifactInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageSoftwareImageInfo;
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
import tosca.nodes.nfv.vdu.Compute;

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
		mapperFactory.classMap(ArtefactInformations.class, VnfPackageArtifactInfo.class)
				.field("path", "artifactPath")
				.field("checksum", "checksum.hash")
				.field("algorithm", "checksum.algorithm")
				.byDefault()
				.register();
		final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
		converterFactory.registerConverter(new SizeConverter());
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
	public List<VnfPackageSoftwareImageInfo> getSoftwareImages() {
		final List<@NonNull Compute> list = toscaApi.getObjects(root, Compute.class);
		LOG.debug("Found {} Compute node in TOSCA model", list.size());
		return list.stream()
				.map(x -> mapper.map(x.getSwImageData(), VnfPackageSoftwareImageInfo.class))
				.collect(Collectors.toList());
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
	public List<VnfPackageArtifactInfo> getAdditionalArtefacts() {
		final List<ArtefactInformations> files = toscaParser.getFiles();
		return files.stream().map(x -> mapper.map(x, VnfPackageArtifactInfo.class)).collect(Collectors.toList());
	}

}
