package com.ubiqube.etsi.mec.meo.v211.controller.pkg;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppD;

@Controller
public class OnboardedAppPackages211MepmApiController implements OnboardedAppPackages211MepmApi {

    @Override
    public ResponseEntity<AppD> appDGET(final String appDId, @Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> appDIdGET(final String appDId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> appDIdPUT(final String appDId, @Valid final Object body) {
        // TODO Auto-generated method stub
        return null;
    }

}
