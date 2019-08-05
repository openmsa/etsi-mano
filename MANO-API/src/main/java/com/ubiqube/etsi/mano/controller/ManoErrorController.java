package com.ubiqube.etsi.mano.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.ubiqube.etsi.mano.model.vnf.sol005.ProblemDetails;

//@Controller
public class ManoErrorController implements ErrorController {
	private final ErrorAttributes errorAttributes;

	public ManoErrorController(ErrorAttributes errorAttributes) {
		super();
		this.errorAttributes = errorAttributes;
	}

	@RequestMapping(value = "error")
	@ResponseBody
	public ProblemDetails error(WebRequest webRequest, HttpServletResponse response) {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		final Map<String, Object> errors = getErrorAttributes(webRequest);
		return new ProblemDetails(response.getStatus(), (String) errors.get("message"));
	}

	@Override
	public String getErrorPath() {
		return "error";
	}

	private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
		final Map<String, Object> errorMap = new HashMap<>();
		errorMap.putAll(errorAttributes.getErrorAttributes(webRequest, false));
		return errorMap;
	}
}
