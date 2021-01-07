package com.ubiqube.etsi.mec.meo.v211.controller.pkg;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppD;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgInfo;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgInfoModifications;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.CreateAppPkg;

@Controller
public class AppPackages211MepmApiController implements AppPackages211MepmApi {

    @Override
    public ResponseEntity<Void> appPackageDELETE(final String appPkgId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<AppPkgInfo> appPackageGET(final String appPkgId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<AppPkgInfoModifications> appPackagePATCH(@Valid final AppPkgInfoModifications body, final String appPkgId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<List<AppPkgInfo>> appPackagesGET(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<List<AppPkgInfo>> appPackagesPOST(@Valid final CreateAppPkg body) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> appPkgGET(final String appPkgId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<AppD> appPkgIdGET(final String appPkgId, @Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<Void> appPkgPUT(final String appPkgId, @Valid final Object body) {
        // TODO Auto-generated method stub
        return null;
    }

}
