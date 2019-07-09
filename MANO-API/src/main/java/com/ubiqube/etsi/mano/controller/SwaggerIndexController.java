package com.ubiqube.etsi.mano.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerIndexController {

	@GetMapping("/openApi")
	public String index() {
		return "index";
	}
}
