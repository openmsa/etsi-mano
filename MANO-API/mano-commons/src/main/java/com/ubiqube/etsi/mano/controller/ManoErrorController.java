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

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.model.ProblemDetails;

@RestController
public class ManoErrorController extends AbstractErrorController {

	private static final Logger LOG = LoggerFactory.getLogger(ManoErrorController.class);

	public ManoErrorController(final ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping("/error")
	public ResponseEntity<ProblemDetails> getError(final HttpServletRequest request, final HttpServletResponse response) {
		final Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		final Map<String, Object> ea = getErrorAttributes(request, ErrorAttributeOptions.of(Include.EXCEPTION, Include.MESSAGE, Include.BINDING_ERRORS));
		LOG.trace("Error controller: {} => {}", status, ea);
		if (status != null) {
			final ProblemDetails problemDetail = new ProblemDetails(status, (String) ea.get("message"));
			return ResponseEntity.status(status)
					.contentType(MediaType.APPLICATION_PROBLEM_JSON)
					.body(problemDetail);
		}
		final ProblemDetails problemDetail = new ProblemDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Some thing bad happend.");
		return ResponseEntity
				.status(500)
				.contentType(MediaType.APPLICATION_PROBLEM_JSON)
				.body(problemDetail);
	}
}
