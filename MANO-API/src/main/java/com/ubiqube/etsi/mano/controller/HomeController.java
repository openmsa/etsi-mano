package com.ubiqube.etsi.mano.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ubiqube.etsi.mano.dao.mano.VnfPackage;

import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {
	@GetMapping(value = "/")
	public String index() {
		return "redirect:swagger-ui.html";
	}

	@GetMapping(value = "/test/{id}")
	public ResponseEntity<VnfPackage> test(@PathVariable("id") final VnfPackage vnfPackage) {
		return ResponseEntity.ok(vnfPackage);
	}
}
