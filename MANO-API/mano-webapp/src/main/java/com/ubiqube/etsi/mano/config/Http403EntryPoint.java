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
package com.ubiqube.etsi.mano.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.model.ProblemDetails;

/**
 * Landing JSON page in case of login error.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class Http403EntryPoint implements AuthenticationEntryPoint {
	/** Logger instance. */
	private static final Logger LOG = LoggerFactory.getLogger(Http403EntryPoint.class);
	/** Injected JSON object mapper. */
	private final ObjectMapper mapper;

	/**
	 * Constructor
	 *
	 * @param _mapper JSON Object mapper.
	 */
	public Http403EntryPoint(final ObjectMapper _mapper) {
		super();
		mapper = _mapper;
	}

	@Override
	public void commence(final HttpServletRequest _request, final HttpServletResponse _response, final AuthenticationException _authException) throws IOException, ServletException {
		LOG.error("Auth failed");
		_response.setContentType(MediaType.APPLICATION_JSON.toString());
		_response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		_response.addHeader("WWW-Authenticate", "Basic realm=\"ETSI-MANO API Realm\"");
		try (PrintWriter out = _response.getWriter()) {
			final ProblemDetails problemDetails = new ProblemDetails(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized.");
			out.print(mapper.writeValueAsString(problemDetails));
		}
	}

}
