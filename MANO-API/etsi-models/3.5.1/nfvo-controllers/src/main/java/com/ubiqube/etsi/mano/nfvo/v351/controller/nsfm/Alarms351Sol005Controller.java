package com.ubiqube.etsi.mano.nfvo.v351.controller.nsfm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.nfvo.v351.model.nsfm.Alarm;
import com.ubiqube.etsi.mano.nfvo.v351.model.nsfm.AlarmModifications;

@RestController
public class Alarms351Sol005Controller implements Alarms351Sol005Api {

	@Override
	public ResponseEntity<Alarm> alarmsAlarmIdGet(final String alarmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<AlarmModifications> alarmsAlarmIdPatch(final String alarmId, @Valid final AlarmModifications body) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<List<Alarm>> alarmsGet(@Valid final String filter, @Valid final String nextpageOpaqueMarker) {
		// TODO Auto-generated method stub
		return null;
	}

}
