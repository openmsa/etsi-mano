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
package com.ubiqube.etsi.mano.nfvo.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ubiqube.etsi.mano.dao.mano.TemporaryDownload;
import com.ubiqube.etsi.mano.dao.mano.TemporaryDownload.ObjectType;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.nfvo.service.TemporaryDownloadService;
import com.ubiqube.etsi.mano.service.SystemService;
import com.ubiqube.etsi.mano.service.vim.VimManager;

@Controller
//@ApiIgnore
public class HomeController {
	private final VimConnectionInformationJpa vciJpa;
	private final TemporaryDownloadService temporaryDownloadService;
	private final VimManager vimManager;
	private final SystemService systemService;

	public HomeController(final VimConnectionInformationJpa vciJpa, final TemporaryDownloadService temporaryDownloadService,
			final VimManager vimManager, final SystemService systemService) {
		this.vciJpa = vciJpa;
		this.temporaryDownloadService = temporaryDownloadService;
		this.vimManager = vimManager;
		this.systemService = systemService;
	}

	@SuppressWarnings("static-method")
	@GetMapping(value = "/")
	public String index() {
		return "redirect:swagger-ui.html";
	}

	@SuppressWarnings("static-method")
	@GetMapping(value = "/test/{id}")
	public ResponseEntity<VnfPackage> test(@PathVariable("id") final VnfPackage vnfPackage) {
		return ResponseEntity.ok(vnfPackage);
	}

	@PostMapping(value = "/registerVim")
	@Deprecated(forRemoval = true, since = "24/01/2022")
	public ResponseEntity<VimConnectionInformation> registerVim(@RequestBody final VimConnectionInformation body) {
		if (null == body.getVimId()) {
			throw new GenericException("'vimId' cannot be [null].");
		}
		systemService.registerVim(body);
		final VimConnectionInformation vci = vciJpa.save(body);
		vimManager.rebuildCache();
		return ResponseEntity.ok(vci);
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
}
