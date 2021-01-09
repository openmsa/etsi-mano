package com.ubiqube.etsi.mec.meo.v211.controller.pkg;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgNotification;

@Controller
public class UserDefinedNotification211MepmApiController implements UserDefinedNotification211MepmApi {

	@Override
	public ResponseEntity<Void> appPkgNotificationPOST(@Valid final AppPkgNotification body) {
		// TODO Auto-generated method stub
		return null;
	}

}
