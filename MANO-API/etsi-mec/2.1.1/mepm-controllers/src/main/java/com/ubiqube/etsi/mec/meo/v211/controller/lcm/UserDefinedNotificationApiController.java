package com.ubiqube.etsi.mec.meo.v211.controller.lcm;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mec.meo.v211.model.lcm.Body1;

@Controller
public class UserDefinedNotificationApiController implements UserDefinedNotificationApi {

    @Override
    public ResponseEntity<Void> appInstNotificationPOST(@Valid final Body1 body) {
        // TODO Auto-generated method stub
        return null;
    }

}
