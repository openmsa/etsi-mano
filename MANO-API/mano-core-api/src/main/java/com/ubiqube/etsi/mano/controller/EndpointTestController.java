package com.ubiqube.etsi.mano.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.service.event.jms.EventMessage;

@RestController
@RequestMapping("/test/")
public class EndpointTestController {

	private static final Logger LOG = LoggerFactory.getLogger(EndpointTestController.class);

	private final JmsTemplate jmsTemplate;

	public EndpointTestController(final JmsTemplate jmsTemplate) {
		super();
		this.jmsTemplate = jmsTemplate;
	}

	@GetMapping
	public ResponseEntity<Void> get() {
		LOG.info("Check subscribtion received.");
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Void> post() {
		LOG.info("POST subscribtion received.");
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/notification", consumes = { "application/json" })
	public ResponseEntity<Void> notification(@RequestBody final EventMessage ev) {
		jmsTemplate.convertAndSend("system.notifications", ev);
		LOG.info("Notification sent.");
		return ResponseEntity.noContent().build();
	}
}
