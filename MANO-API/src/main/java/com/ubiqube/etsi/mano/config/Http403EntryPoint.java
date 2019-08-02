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
import com.ubiqube.etsi.mano.model.vnf.sol005.ProblemDetails;

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
	public Http403EntryPoint(ObjectMapper _mapper) {
		super();
		mapper = _mapper;
	}

	@Override
	public void commence(HttpServletRequest _request, HttpServletResponse _response, AuthenticationException _authException) throws IOException, ServletException {
		LOG.error("Auth failed");
		_response.setContentType(MediaType.APPLICATION_JSON.toString());
		_response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		_response.addHeader("WWW-Authenticate", "Basic realm=\"ETSI-MANO API Realm\"");
		final PrintWriter out = _response.getWriter();
		final ProblemDetails problemDetails = new ProblemDetails(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized.");
		out.print(mapper.writeValueAsString(problemDetails));
	}

}
