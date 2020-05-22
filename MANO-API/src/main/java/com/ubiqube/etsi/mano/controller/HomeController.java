package com.ubiqube.etsi.mano.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ubiqube.etsi.mano.dao.mano.TemporaryDownload;
import com.ubiqube.etsi.mano.dao.mano.TemporaryDownload.ObjectType;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.jpa.GrantRequestJpa;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.jpa.VnfLcmOpOccsJpa;
import com.ubiqube.etsi.mano.model.VimConnectionInfo;
import com.ubiqube.etsi.mano.service.TemporaryDownloadService;

import ma.glasnost.orika.MapperFacade;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {
	private final VimConnectionInformationJpa vciJpa;
	private final MapperFacade mapper;
	private final TemporaryDownloadService temporaryDownloadService;
	private final GrantRequestJpa grandRequestJpa;
	private final VnfLcmOpOccsJpa vnfLcmOpOccsJpa;

	public HomeController(final VimConnectionInformationJpa _vciJpa, final MapperFacade _mapper, final TemporaryDownloadService _temporaryDownloadService,
			final GrantRequestJpa _grandRequestJpa, final VnfLcmOpOccsJpa _vnfLcmOpOccsJpa) {
		vciJpa = _vciJpa;
		mapper = _mapper;
		temporaryDownloadService = _temporaryDownloadService;
		grandRequestJpa = _grandRequestJpa;
		vnfLcmOpOccsJpa = _vnfLcmOpOccsJpa;
	}

	@GetMapping(value = "/")
	public String index() {
		return "redirect:swagger-ui.html";
	}

	@GetMapping(value = "/test/{id}")
	public ResponseEntity<VnfPackage> test(@PathVariable("id") final VnfPackage vnfPackage) {
		return ResponseEntity.ok(vnfPackage);
	}

	@PostMapping(value = "/registerVim")
	public ResponseEntity<VimConnectionInfo> registerVim(@RequestBody final VimConnectionInfo body) {
		VimConnectionInformation vci = mapper.map(body, VimConnectionInformation.class);
		vci = vciJpa.save(vci);
		return ResponseEntity.ok(mapper.map(vci, VimConnectionInfo.class));
	}

	@GetMapping(value = "/download/{id}")
	public ResponseEntity<byte[]> downloadAnonymous(@PathVariable("id") final String id) {
		final byte[] res = temporaryDownloadService.getDocument(id);
		return ResponseEntity.ok(res);
	}

	@PostMapping(value = "/expose/{objectType}/{id}")
	public ResponseEntity<TemporaryDownload> exposeAnonymous(@PathVariable("objectType") final ObjectType objectType, @PathVariable("id") final UUID id) {
		final TemporaryDownload td = temporaryDownloadService.exposeDocument(objectType, id);
		return ResponseEntity.ok(td);
	}

	@DeleteMapping(value = "/admin/vnf-lcm-op-occs/{id}")
	public ResponseEntity<Object> deleteLcmOpOccs(@PathVariable("id") final UUID id) {
		vnfLcmOpOccsJpa.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(value = "/admin/grants/{id}")
	public ResponseEntity<Object> deletegrants(@PathVariable("id") final UUID id) {
		grandRequestJpa.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
