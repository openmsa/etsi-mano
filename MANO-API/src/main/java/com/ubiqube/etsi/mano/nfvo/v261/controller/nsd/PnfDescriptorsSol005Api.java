package com.ubiqube.etsi.mano.nfvo.v261.controller.nsd;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.dao.mano.PnfDescriptor;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.factory.PnfFactory;
import com.ubiqube.etsi.mano.json.MapperForView;
import com.ubiqube.etsi.mano.model.Link;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.CreatePnfdInfoRequest;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.PnfdInfo;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.PnfdInfoLinks;
import com.ubiqube.etsi.mano.nfvo.v261.model.nsd.sol005.PnfdInfoModifications;
import com.ubiqube.etsi.mano.repository.PnfdInfoRepository;

import ma.glasnost.orika.MapperFacade;

@Profile({ "!VNFM" })
@RestController
public class PnfDescriptorsSol005Api implements PnfDescriptorsSol005 {

	private static final Logger LOG = LoggerFactory.getLogger(PnfDescriptorsSol005Api.class);
	private final PnfdInfoRepository pnfdInfoRepository;
	private final MapperFacade mapper;

	public PnfDescriptorsSol005Api(final PnfdInfoRepository _pnfdInfoRepository, final MapperFacade _mapper) {
		pnfdInfoRepository = _pnfdInfoRepository;
		mapper = _mapper;
		LOG.info("Starting PNF Management SOL005 Controller.");
	}

	/**
	 * Query information about multiple PNF descriptor resources.
	 *
	 * \&quot;The GET method queries information about multiple PNF descriptor
	 * resources.\&quot;
	 *
	 */
	@Override
	public ResponseEntity<String> pnfDescriptorsGet(final String filter, final String allFields, final String fields, final String excludeFields, final String excludeDefault) {
		final List<PnfDescriptor> pnfs = pnfdInfoRepository.query(filter);
		final List<PnfdInfo> pnfds = pnfs.stream()
				.map(x -> mapper.map(x, PnfdInfo.class))
				.collect(Collectors.toList());
		pnfds.forEach(x -> x.setLinks(makeLinks(x)));
		final ObjectMapper mapperFv = MapperForView.getMapperForView(excludeFields, fields, null, null);
		try {
			return new ResponseEntity<>(mapperFv.writeValueAsString(pnfds), HttpStatus.OK);
		} catch (final JsonProcessingException e) {
			throw new GenericException(e);
		}
	}

	/**
	 * Delete an individual PNF descriptor resource.
	 *
	 * The DELETE method deletes an individual PNF descriptor resource. An
	 * individual PNF descriptor resource can only be deleted when there is no NS
	 * instance using it or there is NSD referencing it. To delete all PNFD versions
	 * identified by a particular value of the \&quot;pnfdInvariantId\&quot;
	 * attribute, the procedure is to first use the GET method with filter
	 * \&quot;pnfdInvariantId\&quot; towards the PNF descriptors resource to find
	 * all versions of the PNFD. Then, the client uses the DELETE method described
	 * in this clause to delete each PNFD version individually.
	 *
	 */
	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdDelete(final String pnfdInfoId) {
		pnfdInfoRepository.delete(UUID.fromString(pnfdInfoId));
		return ResponseEntity.noContent().build();
	}

	/**
	 * Read an individual PNFD resource.
	 *
	 * The GET method reads information about an individual PNF descriptor. This
	 * method shall follow the provisions specified in the Tables 5.4.6.3.2-1 and
	 * 5.4.6.3.2-2 of GS NFV-SOL 005 for URI query parameters, request and response
	 * data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<PnfdInfo> pnfDescriptorsPnfdInfoIdGet(final String pnfdInfoId) {
		final PnfDescriptor pnfdInfoDb = pnfdInfoRepository.get(UUID.fromString(pnfdInfoId));
		final PnfdInfo pnfdInfo = mapper.map(pnfdInfoDb, PnfdInfo.class);
		pnfdInfo.setLinks(makeLinks(pnfdInfo));
		return new ResponseEntity<>(pnfdInfo, HttpStatus.OK);
	}

	/**
	 * Modify the user defined data of an individual PNF descriptor resource.
	 *
	 * The PATCH method modifies the user defined data of an individual PNF
	 * descriptor resource.
	 *
	 */
	@Override
	public ResponseEntity<PnfdInfoModifications> pnfDescriptorsPnfdInfoIdPatch(final String pnfdInfoId, final String contentType, final PnfdInfoModifications body) {
		// : Implement...

		return null;
	}

	/**
	 * Fetch the content of a PNFD.
	 *
	 * The GET method fetches the content of the PNFD. This method shall follow the
	 * provisions specified in the Table 5.4.7.3.2-2 for URI query parameters,
	 * request and response data structures, and response codes.
	 *
	 */
	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdPnfdContentGet(final String pnfdInfoId, final String accept) {
		// : Implement...
		return ResponseEntity.noContent().build();
	}

	/**
	 * Upload the content of a PNFD.
	 *
	 * The PUT method is used to upload the content of a PNFD. This resource
	 * represents the content of the individual PNF descriptor, i.e. PNFD content.
	 * The client can use this resource to upload and download the content of the
	 * PNFD.
	 *
	 */
	@Override
	public ResponseEntity<Void> pnfDescriptorsPnfdInfoIdPnfdContentPut(final String pnfdInfoId, final String accept) {
		// PnfdOnBoardingNotification OSS/BSS
		return ResponseEntity.noContent().build();
	}

	/**
	 * Create a new PNF descriptor resource.
	 *
	 * The POST method is used to create a new PNF descriptor resource
	 *
	 */
	@Override
	public ResponseEntity<PnfdInfo> pnfDescriptorsPost(final String contentType, final CreatePnfdInfoRequest body) {
		PnfDescriptor pnfdDb = PnfFactory.createPnfDescriptorsPnfdInfo(body);
		pnfdDb = pnfdInfoRepository.save(pnfdDb);
		final PnfdInfo pnfd = mapper.map(pnfdDb, PnfdInfo.class);
		pnfd.setLinks(makeLinks(pnfd));
		return new ResponseEntity<>(pnfd, HttpStatus.OK);
	}

	private static PnfdInfoLinks makeLinks(final PnfdInfo x) {
		final PnfdInfoLinks links = new PnfdInfoLinks();
		final Link pnfdContent = new Link();
		pnfdContent.setHref(linkTo(methodOn(PnfDescriptorsSol005.class).pnfDescriptorsPnfdInfoIdPnfdContentGet(x.getId(), "")).withSelfRel().getHref());
		links.setPnfdContent(pnfdContent);
		final Link self = new Link();
		self.setHref(linkTo(methodOn(PnfDescriptorsSol005.class).pnfDescriptorsPnfdInfoIdGet(x.getId())).withSelfRel().getHref());
		links.setSelf(self);

		return null;
	}

}
