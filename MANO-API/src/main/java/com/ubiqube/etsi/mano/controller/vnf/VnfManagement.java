package com.ubiqube.etsi.mano.controller.vnf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.controller.vnf.sol003.VnfPkgSol003;
import com.ubiqube.etsi.mano.exception.BadRequestException;
import com.ubiqube.etsi.mano.exception.ConflictException;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.model.vnf.sol005.InlineResponse2001;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationVnfPackageOnboardingNotification;
import com.ubiqube.etsi.mano.model.vnf.sol005.NotificationsMessage;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionObject;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscription;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionFilter;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPkgmSubscriptionRequestAuthentication;
import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionsPostQuery;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackageChangeNotification;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPackagesVnfPkgIdGetResponse;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo;
import com.ubiqube.etsi.mano.model.vnf.sol005.VnfPkgInfo.OnboardingStateEnum;
import com.ubiqube.etsi.mano.repository.AbstractGenericRepository;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;
import com.ubiqube.etsi.mano.utils.Notifications;
import com.ubiqube.etsi.mano.utils.RangeHeader;
import com.ubiqube.etsi.mano.utils.ZipFileHandler;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class VnfManagement {
	private static final String APPLICATION_ZIP = "application/zip";
	private static final Logger LOG = LoggerFactory.getLogger(VnfPkgSol003.class);
	private static final String NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO";
	private static final String REPOSITORY_NVFO_DATAFILE_BASE_PATH = "Datafiles/NFVO/vnf_packages";
	private static final String REPOSITORY_SUBSCRIPTION_BASE_PATH = NVFO_DATAFILE_BASE_PATH + "/subscriptions";

	private final VnfPackageRepository vnfPackageRepository;
	private final RepositoryService repositoryService;
	private final ObjectMapper mapper;
	private final AbstractGenericRepository<SubscriptionObject> subscriptionRepository;

	public VnfManagement(VnfPackageRepository _vnfPackageRepository, RepositoryService _repositoryService, ObjectMapper _mapper, AbstractGenericRepository<SubscriptionObject> _subscriptionRepository) {
		super();
		LOG.debug("Booting VNF SOL003 SOL005 Management.");
		vnfPackageRepository = _vnfPackageRepository;
		repositoryService = _repositoryService;
		mapper = _mapper;
		subscriptionRepository = _subscriptionRepository;
	}

	public VnfPkgInfo vnfPackagesVnfPkgIdGet(@PathParam("vnfPkgId") String vnfPkgId) {
		final VnfPkgInfo vnfPkgInfo = getVnfPkgIndividualInfoOrCheckOnboardingStatus(vnfPkgId, false);
		final VnfPackagesVnfPkgIdGetResponse vnfPackagesVnfPkgIdGetResponse = new VnfPackagesVnfPkgIdGetResponse();
		vnfPackagesVnfPkgIdGetResponse.setVnfPkgInfo(vnfPkgInfo);
		return vnfPkgInfo;
	}

	public List<SubscriptionsPkgmSubscription> subscriptionsGet(String filter) {
		List<String> listFilesInFolder;
		try {
			listFilesInFolder = repositoryService.doSearch(REPOSITORY_SUBSCRIPTION_BASE_PATH, "");
		} catch (final ServiceException e) {
			throw new GenericException(e);
		}
		final List<SubscriptionsPkgmSubscription> response = new ArrayList<>();
		for (final String entry : listFilesInFolder) {
			final RepositoryElement repositoryElement = repositoryService.getElement(entry);
			final String content = new String(repositoryService.getRepositoryElementContent(repositoryElement));
			try {
				final SubscriptionObject subscriptionObject = mapper.readValue(content, SubscriptionObject.class);

				final InlineResponse2001 pack = new InlineResponse2001();
				final SubscriptionsPkgmSubscription subscriptionsPkgmSubscription = subscriptionObject.getSubscriptionsPkgmSubscription();
				pack.setPkgmSubscription(subscriptionsPkgmSubscription);
				response.add(subscriptionsPkgmSubscription);
			} catch (final Exception e) {
				throw new GenericException(e);
			}
		}
		return response;
	}

	public List<InlineResponse2001> subscriptionsPost(SubscriptionsPostQuery subscriptionsPostQuery, String href, String id) {
		// Response
		final ArrayList<InlineResponse2001> response = new ArrayList<>();
		final String callback = subscriptionsPostQuery.getPkgmSubscriptionRequest().getCallbackUri();
		final SubscriptionsPkgmSubscriptionFilter filter = subscriptionsPostQuery.getPkgmSubscriptionRequest().getFilter();
		final SubscriptionsPkgmSubscription subscription = new SubscriptionsPkgmSubscription(callback, id, href, filter);

		final InlineResponse2001 pack = new InlineResponse2001();
		pack.setPkgmSubscription(subscription);
		final SubscriptionObject subscriptionObject = new SubscriptionObject(subscriptionsPostQuery.getPkgmSubscriptionRequest().getAuthentication(), subscription);
		subscriptionRepository.save(subscriptionObject);

		response.add(pack);

		return response;
	}

	public void vnfPackageChangeNotificationPost(NotificationsMessage notificationsMessage, String id, String hrefVnfPackage, String hrefSubscription) {
		final Notifications notifications = new Notifications();
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
		final Notifications notifications = new Notifications();

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

	public JSONArray vnfPackagesGet(MultivaluedMap<String, String> queryParameters) throws ServiceException {

		final List<String> vnfPkgsIdsList = getVnfPkgIdsFromRepository();

		// - Refactor this method to map vnfpkgInfo from YAML to VnfPkgInfo object
		// and return a list of VnfPackagesVnfPkgIdGetResponse.

		JSONArray vnfPkginfos = new JSONArray();
		for (final String vnfPckId : vnfPkgsIdsList) {
			final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/")
					.append(vnfPckId).append("/").append("Metadata.yaml").toString();
			final RepositoryElement repositoryElement = repositoryService.getElement(uri);
			final String content = new String(repositoryService.getRepositoryElementContent(repositoryElement));
			final JSONObject contentJson = JSONObject.fromObject(convertYamlToJson(content));
			vnfPkginfos = applyAttributebasedFilteringAndSelectors(queryParameters, vnfPkginfos, contentJson);
		}
		return vnfPkginfos;
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
	public Response vnfPackagesVnfPkgIdArtifactsArtifactPathGet(String vnfPkgId, String artifactPath, RangeHeader rangeHeader) throws ServiceException {
		getVnfPkgIndividualInfoOrCheckOnboardingStatus(vnfPkgId, true);

		final List<String> listvnfPckgFiles = repositoryService.doSearch(new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).append("/").append(artifactPath.trim()).toString(), "");

		if (!listvnfPckgFiles.isEmpty()) {
			if (listvnfPckgFiles.size() > 1) {
				return getZipArchive(rangeHeader, listvnfPckgFiles);
			}
			final RepositoryElement repositoryElement = repositoryService.getElement(listvnfPckgFiles.get(0));
			final String content = new String(repositoryService.getRepositoryElementContent(repositoryElement));
			return Response.ok().type(APPLICATION_ZIP).entity(new ByteArrayInputStream(content.getBytes())).build();
		}
		throw new NotFoundException(new StringBuilder("VNF package artifact not found for vnfPack with id: ")
				.append(vnfPkgId).append(" artifactPath: ").append(artifactPath).toString());
	}

	public Response vnfPackagesVnfPkgIdVnfdGet(String vnfPkgId, String accept) throws ServiceException {
		final List<String> listvnfPckgFiles = new LinkedList<>();

		getVnfPkgIndividualInfoOrCheckOnboardingStatus(vnfPkgId, true);

		// - Implement VNFD multi-files support
		final String uri = new StringBuilder().append(REPOSITORY_NVFO_DATAFILE_BASE_PATH).append("/").append(vnfPkgId).append("/").append("vnfd.json").toString();
		listvnfPckgFiles.add(uri);

		final boolean isVnfd = repositoryService.exists(uri);

		if (isVnfd) {
			if (MediaType.TEXT_PLAIN.equals(accept)) {
				final RepositoryElement repositoryElement = repositoryService.getElement(uri);
				final String content = new String(repositoryService.getRepositoryElementContent(repositoryElement));
				return Response.ok(content, MediaType.APPLICATION_JSON).build();
			} else if (APPLICATION_ZIP.equals(accept)
					|| (APPLICATION_ZIP.equals(accept) && MediaType.TEXT_PLAIN.equals(accept))) {
				return getZipArchive(null, listvnfPckgFiles);
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
	private Response getZipArchive(RangeHeader rangeHeader, List<String> listvnfPckgFiles) {

		final ZipFileHandler zip = new ZipFileHandler(repositoryService, listvnfPckgFiles);
		ByteArrayOutputStream bos;

		try {
			if (rangeHeader == null) {
				bos = zip.getZipFile();
				final Response.ResponseBuilder response = Response.status(Response.Status.OK).type(APPLICATION_ZIP).entity(new ByteArrayInputStream(bos.toByteArray()));
				return response.build();

			}
			bos = zip.getByteRangeZipFile((int) rangeHeader.getFrom(), (int) rangeHeader.getTo());
			final String contentRange = new StringBuilder().append("bytes").append(rangeHeader.getFrom()).append("-")
					.append(rangeHeader.getTo()).append("/").append(zip.zipFileByteArrayLength()).toString();
			final Response.ResponseBuilder response = Response.status(Response.Status.PARTIAL_CONTENT).type(APPLICATION_ZIP).entity(new ByteArrayInputStream(bos.toByteArray())).header("Content-Range", contentRange);
			return response.build();
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private JSONArray applyAttributebasedFilteringAndSelectors(MultivaluedMap<String, String> queryParams, JSONArray vnfPkgInfos, JSONObject contentJson) {
		// Get the dynamic paramaters attribute / value(s)
		String attributesParams = "";
		List<String> attributesValuesParams = new LinkedList<>();

		// Get the filter parameter from the query params object.
		for (final Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
			attributesParams = entry.getKey();
			attributesValuesParams = entry.getValue();
		}

		// List of the fields exclude by default from the VNF Package Info
		final ArrayList<String> listOfDefaultExcludeFields = new ArrayList<>();
		listOfDefaultExcludeFields.add("softwareImages");
		listOfDefaultExcludeFields.add("additionalArtifacts");

		// Filtering operation
		if ("all_fields".equals(attributesParams)) {
			vnfPkgInfos.add(contentJson);
		} else if ("fields".equals(attributesParams)) {
			keepFieldsInVnfPckgInfo(contentJson, attributesValuesParams, listOfDefaultExcludeFields);
			vnfPkgInfos.add(contentJson);
		} else if ("exclude_fields".equals(attributesParams) || "exclude_default".equals(attributesParams)) {
			removeFields(contentJson, listOfDefaultExcludeFields);
			vnfPkgInfos.add(contentJson);
		} else {
			final boolean isFilterMatched = isFilterMatched(attributesParams, attributesValuesParams, contentJson);
			if (isFilterMatched) {
				removeFields(contentJson, listOfDefaultExcludeFields);
				vnfPkgInfos.add(contentJson);
			}
		}
		return vnfPkgInfos;
	}

	private void removeField(JSONObject contentJson, String field) {
		if (contentJson.containsKey(field)) {
			contentJson.remove(field);
		}

	}

	private void removeFields(JSONObject contentJson, ArrayList<String> fields) {
		for (final String field : fields) {
			removeField(contentJson, field);
		}
	}

	/**
	 * Private method allows to slip a String object.
	 *
	 * @param regex
	 * @param object
	 * @return List of the split values
	 */
	private ArrayList<String> splitStringObj(String regex, String object) {
		final ArrayList<String> list = new ArrayList<>();
		list.addAll(Arrays.asList(object.split(regex)));

		return list;
	}

	/**
	 * Private Method allows to search in VNFPackInfos the matched values based-on
	 * filter input.
	 *
	 * @param attributesParams
	 * @param attributesValuesParams
	 * @param contentJson
	 * @return TRUE if matched and FALSE in opposite.
	 */
	private boolean isFilterMatched(String attributesParams, List<String> attributesValuesParams, JSONObject contentJson) {
		String filterOperator = "";

		if (!("".equals(attributesParams) && attributesValuesParams.isEmpty())) {

			final ArrayList<String> listOfAttribute = splitStringObj("\\.", attributesParams);

			final String attributeValues = attributesValuesParams.get(0);
			final ArrayList<String> listOfExpectedValues = splitStringObj(",", attributeValues);

			// Retrieve the filter operator from filter attribute.
			filterOperator = getOperatorFromList(listOfAttribute);

			// Extract input attribute matched value(s).
			final ArrayList<String> attrMatchedValues = extractAttrMatchedValues(listOfAttribute, contentJson);
			// Apply the filter and return matched VNFPackage info(s)
			return compareValuesBasedOnFilterOperator(listOfExpectedValues, attrMatchedValues, filterOperator);
		}
		return true;
	}

	/**
	 * private method allows to filter the inputs attributes matched values based-on
	 * filtering operator.
	 *
	 * @param listOfExpectedValues
	 * @param attrMatchedValues
	 * @param filterOperator
	 * @return boolean True if the operation result is true else false.
	 */
	private boolean compareValuesBasedOnFilterOperator(List<String> listOfExpectedValues, ArrayList<String> attrMatchedValues, String filterOperator) {
		for (final String value : attrMatchedValues) {
			for (final String expectedValue : listOfExpectedValues) {
				// Attribute equal to one of the expect values in the list.
				if ("".equals(filterOperator) || "eq".equals(filterOperator)) {
					if (expectedValue.equals(value)) {
						return true;
					}
				}
				// Attribute not equal to any of the values in the list
				if ("neq".equals(filterOperator)) {
					if (!expectedValue.equals(value)) {
						return true;
					}
				}
				// Attribute greater than <value>

				// - Implementation for the other operators.
			}
		}
		return false;
	}

	private String getOperatorFromList(ArrayList<String> listOfAttrName) {
		for (final String attribute : listOfAttrName) {
			if ("eq".equals(attribute) || "neq".equals(attribute) || "gt".equals(attribute)
					|| "lt".equals(attribute) || "gte".equals(attribute) || "lte".equals(attribute) || "cont".equals(attribute) || "ncont".equals(attribute)) {
				return attribute;
			}
		}
		return "";
	}

	/**
	 * Private method allows to extract from VFND Infos (Json object) the attribute
	 * value(s).
	 *
	 * @param listOfAttrName  Attribute
	 * @param vnfPackInfoJson VNFD infos
	 * @return List of the value(s)
	 */
	private ArrayList<String> extractAttrMatchedValues(ArrayList<String> listOfAttrName, JSONObject vnfPackInfoJson) throws BadRequestException {
		final ArrayList<String> matchedValues = new ArrayList<>();
		JSONObject vnfPackInfo = vnfPackInfoJson;

		int index = 0;
		if (isthereAnOperatorFilterInList(listOfAttrName)) {
			index = 1;
		}

		// - Extract object from Map Entry loop

		for (final String key : listOfAttrName) {
			final Object object = vnfPackInfo.get(key);
			if (object == null) {
				throw new com.ubiqube.etsi.mano.exception.BadRequestException("Wrong filter name: " + key);
			}

			if (((object instanceof JSONArray) || (object instanceof JSONObject)) && (index < (listOfAttrName.size() - index))) {
				if (object instanceof JSONArray) {
					for (int i = 0; i < ((JSONArray) object).size(); i++) {
						final String value = (String) ((JSONArray) object).get(i);
						if (value instanceof String) {
							matchedValues.add(value);
						}
					}
				} else {
					vnfPackInfo = (JSONObject) object;
				}
			} else if (((object instanceof String) || (object instanceof Integer) || (object instanceof Boolean) || (object instanceof Character))
					&& (index < (listOfAttrName.size() - index))) {
				matchedValues.add(String.valueOf(object));
			}
		}
		return matchedValues;
	}

	/**
	 * Private method allows to check if a filtering operator in the list of
	 * attributes name.
	 *
	 * @param listOfAttrName
	 * @return If there is an filtering operator in the list return TRUE alse FALSE.
	 */
	private boolean isthereAnOperatorFilterInList(ArrayList<String> listOfAttrName) {
		// Check the last attribute name if it an filtering operator or not.
		final String lastAttribute = listOfAttrName.get(listOfAttrName.size() - 1);

		if (!"".equals(getOperatorFromList(listOfAttrName))) {
			return true;
		}
		return false;
	}

	private void keepFieldsInVnfPckgInfo(JSONObject contentJson, List<String> attributesValuesParams, ArrayList<String> listOfDefaultExcludeFields) {
		final String attributeValues = attributesValuesParams.get(0);
		final ArrayList<String> fieldsFromInput = splitStringObj(",", attributeValues);
		final ArrayList<String> fieldsToRemove = listOfDefaultExcludeFields;

		// Compare the both lists contents and remove them from the "excluded fields by
		// default" the matched values.
		fieldsToRemove.removeAll(fieldsFromInput);

		// Remove the exclude fields not matched to the inputs fields list.
		for (final String field : fieldsToRemove) {
			removeField(contentJson, field);
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
		final List<String> vnfPackageIdList = new ArrayList<String>();

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

	public Response vnfPackagesVnfPkgIdPackageContentGet(String _vnfPkgId, String _range) {
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
