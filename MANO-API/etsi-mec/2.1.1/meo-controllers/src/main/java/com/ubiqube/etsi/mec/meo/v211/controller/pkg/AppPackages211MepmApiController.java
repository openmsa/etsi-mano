package com.ubiqube.etsi.mec.meo.v211.controller.pkg;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppPkg;
import com.ubiqube.etsi.mec.controller.pkg.AppPackageMeoController;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppD;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgInfo;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgInfoModifications;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.CreateAppPkg;

import ma.glasnost.orika.MapperFacade;

@RestController
@ExposesResourceFor(AppPkgInfo.class)
public class AppPackages211MepmApiController implements AppPackages211MepmApi {
	private final AppPackageMeoController appPackageMeoController;

	private final MapperFacade mapper;

	private final EntityLinks entityLinks;

	public AppPackages211MepmApiController(final AppPackageMeoController _appPackageMeoController, final MapperFacade _mapper, final EntityLinks _entityLinks) {
		appPackageMeoController = _appPackageMeoController;
		mapper = _mapper;
		entityLinks = _entityLinks;
	}

	@Override
	public ResponseEntity<Void> appPackageDELETE(final String appPkgId) {
		appPackageMeoController.deleteById(UUID.fromString(appPkgId));
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<AppPkgInfo> appPackageGET(final String appPkgId) {
		final AppPkg appPkg = appPackageMeoController.findById(UUID.fromString(appPkgId));
		final AppPkgInfo appPkgInfo = mapper.map(appPkg, AppPkgInfo.class);
		createSelfLink(appPkgInfo);
		return ResponseEntity.ok(appPkgInfo);
	}

	private void createSelfLink(final AppPkgInfo re) {
		final Link selfLink = entityLinks.linkToItemResource(AppPkgInfo.class, re.getId());
		re.add(selfLink);
	}

	@Override
	public ResponseEntity<AppPkgInfoModifications> appPackagePATCH(@Valid final AppPkgInfoModifications body, final String appPkgId) {
		appPackageMeoController.setOperationState(UUID.fromString(appPkgId), OperationalStateType.fromValue(body.getOperationState().toString()));
		return ResponseEntity.ok(mapper.map(body, AppPkgInfoModifications.class));
	}

	@Override
	public ResponseEntity appPackagesGET(@Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault, final Pageable pageable) {
		final List<AppPkg> list = appPackageMeoController.query(filter);
		final List<AppPkgInfo> ret = list.stream().map(x -> mapper.map(x, AppPkgInfo.class)).collect(Collectors.toList());
		ret.forEach(this::createSelfLink);
		final CollectionModel<EntityModel<AppPkgInfo>> cm = CollectionModel.wrap(ret);
		return ResponseEntity.ok(cm);
	}

	@Override
	public ResponseEntity<AppPkgInfo> appPackagesPOST(@Valid final CreateAppPkg body) {
		AppPkg appPkg = mapper.map(body, AppPkg.class);
		appPkg = appPackageMeoController.save(appPkg);
		final AppPkgInfo mapped = mapper.map(appPkg, AppPkgInfo.class);
		createSelfLink(mapped);
		return ResponseEntity.ok(mapped);
	}

	@Override
	public ResponseEntity<Resource> appPkgGetContent(final String appPkgId) {
		return appPackageMeoController.getPackage(UUID.fromString(appPkgId));
	}

	@Override
	public ResponseEntity<AppD> appPkgIdGetAppd(final String appPkgId, @Valid final String filter, @Valid final String allFields, @Valid final String fields, @Valid final String excludeFields, @Valid final String excludeDefault) {
		final AppPkg appPkg = appPackageMeoController.getAppd(UUID.fromString(appPkgId));
		return ResponseEntity.ok(mapper.map(appPkg, AppD.class));
	}

	@Override
	public ResponseEntity<Void> appPkgPutContent(final String appPkgId, @Valid final Object body) {
		// TODO Auto-generated method stub
		return ResponseEntity.noContent().build();
	}

}
