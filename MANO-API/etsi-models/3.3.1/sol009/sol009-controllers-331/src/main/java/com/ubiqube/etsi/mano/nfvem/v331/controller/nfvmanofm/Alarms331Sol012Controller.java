package com.ubiqube.etsi.mano.nfvem.v331.controller.nfvmanofm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanofm.Alarm;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanofm.AlarmModifications;

@RestController
public class Alarms331Sol012Controller implements Alarms331Sol012Api {

	@Override
	public ResponseEntity<Alarm> alarmsAlarmIdGet(final String alarmId, final String version, final String accept, final String authorization) {
		// TODO Auto-generated method stub
		return Alarms331Sol012Api.super.alarmsAlarmIdGet(alarmId, version, accept, authorization);
	}

	@Override
	public ResponseEntity<AlarmModifications> alarmsAlarmIdPatch(final String version, final String accept, final String contentType, final String alarmId, @Valid final AlarmModifications body, final String authorization) {
		// TODO Auto-generated method stub
		return Alarms331Sol012Api.super.alarmsAlarmIdPatch(version, accept, contentType, alarmId, body, authorization);
	}

	@Override
	public ResponseEntity<List<Alarm>> alarmsGet(final String version, final String accept, final String authorization, @Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return Alarms331Sol012Api.super.alarmsGet(version, accept, authorization, filter, nextpageOpaqueMarker);
	}

}
