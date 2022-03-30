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
package com.ubiqube.etsi.mano.controller;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.exception.NotFoundException;
import com.ubiqube.etsi.mano.exception.PreConditionException;
import com.ubiqube.etsi.mano.model.NotificationEvent;
import com.ubiqube.etsi.mano.service.GrantService;
import com.ubiqube.etsi.mano.service.Patcher;
import com.ubiqube.etsi.mano.service.VimService;
import com.ubiqube.etsi.mano.service.event.EventManager;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

import ma.glasnost.orika.MapperFacade;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

	private final VimService vimService;
	private final MapperFacade mapper;
	private final VimManager vimManager;
	private final EventManager eventManager;
	private final Patcher patcher;
	private final GrantService grantService;

	public AdminController(final VimService vciJpa, final MapperFacade mapper, final VimManager vimManager, final EventManager eventManager,
			final Patcher patcher, final GrantService grantJpa) {
		super();
		this.vimService = vciJpa;
		this.mapper = mapper;
		this.vimManager = vimManager;
		this.eventManager = eventManager;
		this.patcher = patcher;
		this.grantService = grantJpa;
	}

	@PostMapping(value = "/vim/register")
	public ResponseEntity<VimConnectionInformation> registerVim(@RequestBody final VimConnectionInformation body) {
		final VimConnectionInformation vci = vimManager.register(body);
		return ResponseEntity.ok(mapper.map(vci, VimConnectionInformation.class));
	}

	@DeleteMapping(value = "/vim/{id}")
	public ResponseEntity<Void> deleteVim(@PathVariable("id") final String id) {
		vimManager.deleteVim(UUID.fromString(id));
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/vim/{id}/refresh")
	public ResponseEntity<VimConnectionInformation> updateVim(@PathVariable("id") final UUID id) {
		final VimConnectionInformation vci = vimManager.refresh(id);
		return ResponseEntity.ok(mapper.map(vci, VimConnectionInformation.class));
	}

	@PatchMapping(value = "/vim/{id}")
	public ResponseEntity<VimConnectionInformation> patchVim(@PathVariable("id") final UUID id, @Nullable @RequestBody final String body,
			@RequestHeader(name = HttpHeaders.IF_MATCH, required = false) final String ifMatch) {
		final VimConnectionInformation vim = vimManager.findVimById(id);
		if (ifMatch != null && !ifMatch.equals(vim.getVersion() + "")) {
			throw new PreConditionException(ifMatch + " does not match " + vim.getVersion());
		}
		patcher.patch(body, vim);
		final VimConnectionInformation newVim = vimManager.save(vim);
		return ResponseEntity.ok(newVim);
	}

	@GetMapping(value = "/vim/{id}/connect")
	public ResponseEntity<Map<String, String>> connectVim(@PathVariable("id") final UUID id) {
		final Vim vim = vimManager.getVimById(id);
		final VimConnectionInformation vimconn = vimService.findById(id).orElseThrow(() -> new NotFoundException("Could not find vim id " + id));
		final Map<String, String> networks = vim.network(vimconn).getPublicNetworks();
		return ResponseEntity.ok(networks);
	}

	@GetMapping(value = "/vim")
	public ResponseEntity<Iterable<VimConnectionInformation>> listVim() {
		final Iterable<VimConnectionInformation> vci = vimService.findAll();
		return ResponseEntity.ok(vci);
	}

	@GetMapping(value = "/event/{event}/{id}")
	public ResponseEntity<Void> sendEvent(@PathVariable("event") final NotificationEvent event, @PathVariable("id") final UUID id) {
		eventManager.sendNotification(event, id, Map.of());
		return ResponseEntity.accepted().build();
	}

	@DeleteMapping(value = "/grant/all")
	public ResponseEntity<Void> deleteAllGrant() {
		grantService.findAll().forEach(x -> {
			try {
				grantService.delete(x);
			} catch (final RuntimeException e) {
				LOG.trace("", e);
				LOG.info("Unable to delete: {}", x.getId());
			}
		});
		return ResponseEntity.accepted().build();
	}
}
