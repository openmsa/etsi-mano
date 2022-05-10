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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.model.NotificationEvent;
import com.ubiqube.etsi.mano.service.GrantService;
import com.ubiqube.etsi.mano.service.event.EventManager;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

	private final EventManager eventManager;
	private final GrantService grantService;

	public AdminController(final EventManager eventManager, final GrantService grantJpa) {
		super();
		this.eventManager = eventManager;
		this.grantService = grantJpa;
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
