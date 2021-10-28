package com.ubiqube.etsi.mano.vnfm.v351.controller.vnffm;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.dao.mano.alarm.AckState;
import com.ubiqube.etsi.mano.vnfm.fc.vnffm.AlarmFrontController;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.Alarm;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.AlarmLinks;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.AlarmModifications;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnffm.Link;

@RestController
public class Alarms351Sol003Controller implements Alarms351Sol003Api {

	private final AlarmFrontController alarmFrontController;

	public Alarms351Sol003Controller(final AlarmFrontController alarmFrontController) {
		super();
		this.alarmFrontController = alarmFrontController;
	}

	@Override
	public ResponseEntity<Alarm> alarmsAlarmIdGet(final String alarmId) {
		return alarmFrontController.findById(alarmId, Alarm.class, Alarms351Sol003Controller::makeLinks);
	}

	@Override
	public ResponseEntity<AlarmModifications> alarmsAlarmIdPatch(final String alarmId, final AlarmModifications alarmModifications, final String ifMatch) {
		return alarmFrontController.patch(alarmId, AckState.valueOf(alarmModifications.getAckState().toString()), ifMatch, AlarmModifications.class);
	}

	@Override
	public ResponseEntity<String> alarmsGet(final MultiValueMap<String, String> requestParams, @Valid final String nextpageOpaqueMarker) {
		return alarmFrontController.search(requestParams, Alarm.class, Alarms351Sol003Controller::makeLinks);
	}

	private static void makeLinks(final Alarm alarm) {
		final AlarmLinks links = new AlarmLinks();
		Link link = new Link();
		link.setHref(linkTo(methodOn(Alarms351Sol003Api.class).alarmsAlarmIdGet(alarm.getId())).withSelfRel().getHref());
		links.setSelf(link);

		link = new Link();
		link.setHref(linkTo(methodOn(Alarms351Sol003Api.class).alarmsAlarmIdGet(alarm.getId())).withSelfRel().getHref());
		links.setObjectInstance(link);

		alarm.setLinks(links);
	}

}
