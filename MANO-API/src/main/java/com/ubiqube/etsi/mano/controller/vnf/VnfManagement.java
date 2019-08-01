package com.ubiqube.etsi.mano.controller.vnf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.grammar.AstBuilder;
import com.ubiqube.etsi.mano.grammar.JsonFilter;
import com.ubiqube.etsi.mano.json.CustomSerializer;
import com.ubiqube.etsi.mano.json.ViewHolder;
import com.ubiqube.etsi.mano.model.vnf.sol005.InlineResponse2001;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationVnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationsMessage;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequest;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequestAuthentication;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdGetResponse;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.repository.SubscriptionRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.service.Notifications;
import com.ubiqube.etsi.mano.utils.RangeHeader;
import com.ubiqube.etsi.mano.utils.ZipFileHandler;

@Service
public class VnfManagement {
	private static final String APPLICATION_ZIP = "application/zip";
	private static final Logger LOG = LoggerFactory.getLogger(VnfManagement.class);
	private static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	private static final String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";
	private static final String REPOSITORY_SUBSCRIPTION_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/subscriptions";

	private final VnfPackageRepository vnfPackageRepository;
	private final RepositoryService repositoryService;
	private final SubscriptionRepository subscriptionRepository;
	private final Notifications notifications;
	private final JsonFilter jsonFilter;

	public VnfManagement(VnfPackageRepository _vnfPackageRepository, RepositoryService _repositoryService, SubscriptionRepository _subscriptionRepository, Notifications _notifications, JsonFilter _jsonFilter) {
		super();
		LOG.debug("Booting VNF SOL003 SOL005 Management.");
		vnfPackageRepository = _vnfPackageRepository;
		repositoryService = _repositoryService;
		subscriptionRepository = _subscriptionRepository;
		notifications = _notifications;
		jsonFilter = _jsonFilter;
	}

	public VnfPkgInfo vnfPackagesVnfPkgIdGet(String vnfPkgId) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);
		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);
		return vnfPkgInfo;
	}

	public List<SubscriptionsPkgmSubscription> subscriptionsGet(String filter) {
		final AstBuilder astBuilder = new AstBuilder(filter);
		List<String> listFilesInFolder;
		try {
			listFilesInFolder = repositoryService.doSearch(REPOSITORY_SUBSCRIPTION_BASE_PATH, "");
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
		final List<SubscriptionsPkgmSubscription> response = new ArrayList<>();
		for (final String entry : listFilesInFolder) {
			final String path = entry.substring((REPOSITORY_SUBSCRIPTION_BASE_PATH + '/').length());
			final File file = new File(path);
			LOG.info("Retreiving: {}", file.getParent());
			final SubscriptionObject subscriptionObject = subscriptionRepository.get(file.getParent());
			if (jsonFilter.apply(subscriptionObject.getSubscriptionsPkgmSubscription(), astBuilder)) {
				final InlineResponse2001 pack = new InlineResponse2001();
				final SubscriptionsPkgmSubscription subscriptionsPkgmSubscription = subscriptionObject.getSubscriptionsPkgmSubscription();
				pack.setPkgmSubscription(subscriptionsPkgmSubscription);
				response.add(subscriptionsPkgmSubscription);
			}
		}
		return response;
	}

	public List<InlineResponse2001> subscriptionsPost(SubscriptionsPkgmSubscriptionRequest subscriptionsPostQuery, String href, String id) {
		// Response
		final ArrayList<InlineResponse2001> response = new ArrayList<>();
		final String callback = subscriptionsPostQuery.getCallbackUri();
		final SubscriptionsPkgmSubscriptionFilter filter = subscriptionsPostQuery.getFilter();
		final SubscriptionsPkgmSubscription subscription = new SubscriptionsPkgmSubscription(callback, id, href, filter);

		final InlineResponse2001 pack = new InlineResponse2001();
		pack.setPkgmSubscription(subscription);
		final SubscriptionObject subscriptionObject = new SubscriptionObject(subscriptionsPostQuery.getAuthentication(), subscription);
		subscriptionRepository.save(subscriptionObject);

		response.add(pack);
		return response;
	}

	public void vnfPackageChangeNotificationPost(NotificationsMessage notificationsMessage, String id, String hrefVnfPackage, String hrefSubscription) {
		final String vnfPkgId = notificationsMessage.getVnfPkgId();
		final String vnfdId = notificationsMessage.getVnfdId();
		final String subscriptionId = notificationsMessage.getSubscriptionId();

		final SubscriptionObject subscriptionsRepository = subscriptionRepository.get(subscriptionId);
		final SubscriptionsPkgmSubscriptionRequestAuthentication auth = subscriptionsRepository.getSubscriptionsPkgmSubscriptionRequestAuthentication();
		final String callbackUri = subscriptionsRepository.getSubscriptionsPkgmSubscription().getCallbackUri();

		final VnfPackageChangeNotification vnfPackageChangeNotification = new VnfPackageChangeNotification(id, vnfPkgId, vnfdId, subscriptionId, hrefVnfPackage, hrefSubscription);
		notifications.doNotification(vnfPackageChangeNotification, callbackUri, auth);
	}

	public void vnfPackageOnboardingNotificationPost(NotificationsMessage notificationsMessage, String id, String hrefSubscription, String hrefPackage) {
		final String subscriptionId = notificationsMessage.getSubscriptionId();
		final SubscriptionObject subscriptionsRepository = subscriptionRepository.get(subscriptionId);
		final SubscriptionsPkgmSubscription req = subscriptionsRepository.getSubscriptionsPkgmSubscription();
		final String cbUrl = req.getCallbackUri();
		final String vnfPkgId = notificationsMessage.getVnfPkgId();
		final String vnfdId = notificationsMessage.getVnfdId();
		final SubscriptionsPkgmSubscriptionRequestAuthentication auth = subscriptionsRepository.getSubscriptionsPkgmSubscriptionRequestAuthentication();

		final NotificationVnfPackageOnboardingNotification notificationVnfPackageOnboardingNotification = new NotificationVnfPackageOnboardingNotification(id, "", subscriptionId, vnfPkgId, vnfdId, hrefSubscription, hrefPackage);

		notifications.doNotification(notificationVnfPackageOnboardingNotification, cbUrl, auth);
	}

	public List<VnfPkgInfo> vnfPackagesGet(Map<String, String> queryParameters) throws ServiceException {
		final String filter = queryParameters.get("filter");
		final AstBuilder astBuilder = new AstBuilder(filter);
		final List<String> vnfPkgsIdsList = getVnfPkgIdsFromRepository();

		final List<VnfPkgInfo> vnfPkginfos = new ArrayList<>();
		for (final String vnfPckId : vnfPkgsIdsList) {
			final VnfPkgInfo vnfPackage = vnfPackageRepository.get(vnfPckId);
			if (jsonFilter.apply(vnfPackage, astBuilder)) {
				vnfPkginfos.add(vnfPackage);
			}
		}
		final String exclude = queryParameters.get("all_fields");
		final String fields = queryParameters.get("fields");
		final String excludeFields = queryParameters.get("exclude_fields");
		final String excludeDefault = queryParameters.get("exclude_default");
		final ObjectMapper mapperForQuery = getMapperForView(exclude, fields, excludeDefault, excludeFields);
		return vnfPkginfos;
	}

	private ObjectMapper getMapperForView(String exclude, String fields, String excludeDefault, String excludeFields) {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.setSerializationInclusion(Include.NON_NULL);

		final List<ViewHolder> excludeList = buildViewList(exclude);
		final List<ViewHolder> wantedList = buildViewList(fields);
		mapper.registerModule(new SimpleModule() {
			private static final long serialVersionUID = 1L;

			@Override
			public void setupModule(SetupContext context) {
				super.setupModule(context);
				context.addBeanSerializerModifier(new CustomSerializer(excludeList, wantedList));
			}
		});
		return mapper;
	}

	private static List<ViewHolder> buildViewList(@Nullable String fields) {
		final List<ViewHolder> ret = new ArrayList<>();
		if (null == fields) {
			return ret;
		}
		final String[] fieldArray = fields.split(",");
		for (final String string : fieldArray) {
			ret.add(new ViewHolder(string));
		}
		return ret;
	}

	/**
	 * We should not reply a Response here.
	 *
	 * @param vnfPkgId
	 * @param artifactPath
	 * @param _accept
	 * @param rangeHeader
	 * @return
	 * @throws ServiceException
	 */
	public ResponseEntity<Resource> vnfPackagesVnfPkgIdArtifactsArtifactPathGet(String vnfPkgId, String artifactPath, RangeHeader rangeHeader) throws ServiceException {
		getVnfPkgIndividualInfoOrCheckOnboardingStatus(vnfPkgId, true);

		final List<String> listvnfPckgFiles = repositoryService.doSearch(new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).append("/").append(artifactPath.trim()).toString(), "");

		if (!listvnfPckgFiles.isEmpty()) {
			if (listvnfPckgFiles.size() > 1) {
				return getZipArchive(rangeHeader, listvnfPckgFiles);
			}
			final RepositoryElement repositoryElement = repositoryService.getElement(listvnfPckgFiles.get(0));
			final byte[] content = repositoryService.getRepositoryElementContent(repositoryElement);
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.body(resource);
		}
		throw new NotFoundException(new StringBuilder("VNF package artifact not found for vnfPack with id: ")
				.append(vnfPkgId).append(" artifactPath: ").append(artifactPath).toString());
	}

	public ResponseEntity<Resource> vnfPackagesVnfPkgIdVnfdGet(String vnfPkgId, String accept) throws ServiceException {
		final List<String> listvnfPckgFiles = new LinkedList<>();

		getVnfPkgIndividualInfoOrCheckOnboardingStatus(vnfPkgId, true);

		// - Implement VNFD multi-files support
		final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).append("/").append("vnfd.json").toString();
		listvnfPckgFiles.add(uri);

		final boolean isVnfd = repositoryService.exists(uri);

		if (isVnfd) {
			if (MediaType.TEXT_PLAIN_VALUE.equals(accept)) {
				final RepositoryElement repositoryElement = repositoryService.getElement(uri);
				final byte[] content = repositoryService.getRepositoryElementContent(repositoryElement);
				final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(content));
				return ResponseEntity.ok(resource);
			} else if (APPLICATION_ZIP.equals(accept) && MediaType.TEXT_PLAIN_VALUE.equals(accept)) {
				return getZipArchive(null, listvnfPckgFiles);
			} else {
				final RepositoryElement repositoryElement = repositoryService.getElement(uri);
				final String content = new String(repositoryService.getRepositoryElementContent(repositoryElement), StandardCharsets.UTF_8);
				final String yaml = conJsonToYaml(content);
				final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(yaml.getBytes()));
				return ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_OCTET_STREAM)
						.body(resource);
			}
		}
		throw new NotFoundException("VNFD not found for vnfPkg with id: " + vnfPkgId);
	}

	/**
	 * Method allows to archive VNF Package contents and artifacts.
	 *
	 * @param range
	 * @param listvnfPckgFiles
	 * @return
	 *
	 */
	private ResponseEntity<Resource> getZipArchive(RangeHeader rangeHeader, List<String> listvnfPckgFiles) {

		final ZipFileHandler zip = new ZipFileHandler(repositoryService, listvnfPckgFiles);
		ByteArrayOutputStream bos;

		try {
			if (rangeHeader == null) {
				bos = zip.getZipFile();
				final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));
				final MediaType contentType = MediaType.APPLICATION_OCTET_STREAM;
				return ResponseEntity.ok().contentType(contentType).body(resource);

			}
			bos = zip.getByteRangeZipFile((int) rangeHeader.getFrom(), rangeHeader.getTo());
			final String contentRange = new StringBuilder().append("bytes").append(rangeHeader.getFrom()).append("-")
					.append(rangeHeader.getTo()).append("/").append(zip.zipFileByteArrayLength()).toString();
			final InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bos.toByteArray()));
			final MediaType contentType = MediaType.APPLICATION_OCTET_STREAM;
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
					.header("Content-Range", contentRange)
					.contentType(contentType)
					.body(resource);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	/**
	 * Get the list of VNF Packages Information corresponding IDs.
	 *
	 * @return <b>vnfPackageIdList</b> VNF Packages details IDs list.
	 * @throws ServiceException
	 */
	private List<String> getVnfPkgIdsFromRepository() throws ServiceException {

		// List vnfd package from repository
		final List<String> listFilesInFolder = repositoryService.doSearch(REPOSITORY_NVFO_DATAFILE_BASE_PATH, "");
		final List<String> vnfPackageIdList = new ArrayList<>();

		// Split files path and store VNF Pckg Id
		for (final String filePath : listFilesInFolder) {
			final String[] splitArray = filePath.split("/", -1);
			final String retrievedVnfPckId = splitArray[3];
			vnfPackageIdList.add(retrievedVnfPckId);
		}

		final Set<String> set = new HashSet<>(vnfPackageIdList);
		vnfPackageIdList.clear();
		vnfPackageIdList.addAll(set);

		return vnfPackageIdList;
	}

	/**
	 * Map YAML file to JsonObject.
	 *
	 * @param yaml VNF Package Metadata from repository
	 * @return the JsonObject as a String
	 */
	private static String convertYamlToJson(final String yaml) {
		try {
			final ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
			final Object obj = yamlReader.readValue(yaml, Object.class);
			final ObjectMapper jsonWriter = new ObjectMapper();
			return jsonWriter.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private String conJsonToYaml(String json) {
		try {
			final ObjectMapper jsonReader = new ObjectMapper();
			final Object obj = jsonReader.readValue(json, Object.class);
			final ObjectMapper yamlWriter = new ObjectMapper(new YAMLFactory());
			return yamlWriter.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private VnfPkgInfo getVnfPkgIndividualInfoOrCheckOnboardingStatus(String vnfPkgId, boolean isCheckOnbordingStatus) {
		final VnfPkgInfo vnfPkgInfo = vnfPackageRepository.get(vnfPkgId);

		if (isCheckOnbordingStatus) {
			final String onboardingState = vnfPkgInfo.getOnboardingState();
			if (OnboardingStateEnum.PROCESSING.toString().equalsIgnoreCase(onboardingState)
					|| OnboardingStateEnum.UPLOADING.toString().equalsIgnoreCase(onboardingState.toUpperCase())) {
				throw new ConflictException("Conflict with the current VNF Package onbording state: " + onboardingState.toUpperCase());
			}
		} else {
			return vnfPkgInfo;
		}
		return vnfPkgInfo;
	}

	public void subscriptionsSubscriptionIdDelete(String _subscriptionId) {
		subscriptionRepository.delete(_subscriptionId);
	}

	public SubscriptionsPkgmSubscription subscriptionsSubscriptionIdGet(String _subscriptionId) {
		return subscriptionRepository.get(_subscriptionId).getSubscriptionsPkgmSubscription();
	}

	public ResponseEntity<Resource> vnfPackagesVnfPkgIdPackageContentGet(String _vnfPkgId, String _range) {
		getVnfPkgIndividualInfoOrCheckOnboardingStatus(_vnfPkgId, true);

		// List vnfd package from repository
		List<String> listvnfPckgFiles;
		try {
			listvnfPckgFiles = repositoryService.doSearch(new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(_vnfPkgId).toString(), "");
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}

		if (!listvnfPckgFiles.isEmpty()) {
			return getZipArchive(RangeHeader.fromValue(_range), listvnfPckgFiles);
		}
		throw new NotFoundException("VNF package content not found for vnfPkgId: " + _vnfPkgId);
	}

}
