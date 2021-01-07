package com.ubiqube.etsi.mec.meo.v211.controller.lcm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceLcmOpOcc;

@Controller
public class AppLcmOpOccsApiController implements AppLcmOpOccsApi {

    @Override
    public ResponseEntity<List<AppInstanceLcmOpOcc>> appLcmOpOccsGET(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<AppInstanceLcmOpOcc> appLcmOpOccsbyIdGET(final String appLcmOpOccId) {
        // TODO Auto-generated method stub
        return null;
    }

}
