/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
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
