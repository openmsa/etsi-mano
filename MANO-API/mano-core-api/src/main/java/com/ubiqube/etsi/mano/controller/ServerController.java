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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.dao.mano.config.ServerDto;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.service.ServerService;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RestController
@RolesAllowed({ "ROLE_ADMIN" })
@RequestMapping("/admin/server")
public class ServerController {
	private final ServerService serverService;
	private final MapperFacade mapper;

	public ServerController(final ServerService serverService, final MapperFacade mapper) {
		super();
		this.serverService = serverService;
		this.mapper = mapper;
	}

	@PostMapping
	public ResponseEntity<Void> createServer(@Valid @RequestBody final ServerDto serversDto) {
		final Servers servers = mapper.map(serversDto, Servers.class);
		final Servers newServer = serverService.createServer(servers);
		final URI location = linkTo(methodOn(ServerController.class).findById(newServer.getId())).withSelfRel().toUri();
		return ResponseEntity.accepted().location(location).build();
	}

	@GetMapping
	public List<EntityModel<Servers>> findAll(final Pageable pageable) {
		final Page<Servers> page = serverService.findAll(pageable);
		return page.getContent().stream().map(x -> EntityModel.of(x, makeLinks(x))).toList();
	}

	@GetMapping("/{id}")
	public EntityModel<Servers> findById(@PathVariable("id") final UUID id) {
		final Servers ret = serverService.findById(id);
		return EntityModel.of(ret, makeLinks(ret));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable final UUID id) {
		serverService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	private static List<Link> makeLinks(final Servers server) {
		final List<Link> ret = new ArrayList<>();
		ret.add(linkTo(methodOn(ServerController.class).findById(server.getId())).withRel("account"));
		return ret;
	}

}
