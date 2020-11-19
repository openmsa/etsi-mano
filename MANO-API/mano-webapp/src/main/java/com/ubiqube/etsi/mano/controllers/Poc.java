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

package com.ubiqube.etsi.mano.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.service.vim.OpenStackVim;
import com.ubiqube.etsi.mano.service.vim.VimCapability;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
@RequestMapping("/poc")
public class Poc {
	private final OpenStackVim osv;
	private final VimConnectionInformationJpa vimConnectionInformationJpa;

	public Poc(final OpenStackVim _osv, final VimConnectionInformationJpa _vimConnectionInformationJpa) {
		osv = _osv;
		vimConnectionInformationJpa = _vimConnectionInformationJpa;
	}

	@GetMapping(value = "/test/{id}")
	public ResponseEntity<List<VimCapability>> test(@PathVariable(value = "id") final UUID id) {
		final Optional<VimConnectionInformation> vc = vimConnectionInformationJpa.findById(id);
		final List<VimCapability> res = osv.getCaps(vc.get());
		return ResponseEntity.ok(res);
	}

}
