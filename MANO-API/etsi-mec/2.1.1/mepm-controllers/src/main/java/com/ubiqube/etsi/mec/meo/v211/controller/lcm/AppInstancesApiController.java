package com.ubiqube.etsi.mec.meo.v211.controller.lcm;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mec.meo.v211.model.lcm.AppInstanceInfo;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.CreateAppInstanceRequest;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.InstantiateAppRequest;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.OperateAppRequest;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.TerminateAppRequest;

@Controller
public class AppInstancesApiController implements AppInstancesApi {

    @Override
    public ResponseEntity<List<AppInstanceInfo>> appInstanceGET(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> appInstanceIdDELETE(final String appInstanceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<AppInstanceInfo> appInstanceIdGET(final String appInstanceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<AppInstanceInfo> appInstancePOST(@Valid final CreateAppInstanceRequest body) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> appLcmInstanciatePOST(@Valid final InstantiateAppRequest body, final String appInstanceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> appLcmOperatePOST(@Valid final OperateAppRequest body, final String appInstanceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> appLcmTerminatePOST(@Valid final TerminateAppRequest body, final String appInstanceId) {
        // TODO Auto-generated method stub
        return null;
    }

}
