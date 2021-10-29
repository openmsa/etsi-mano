package com.ubiqube.etsi.mano.vnfm.v351.controller.vnf;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.Link;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.VnfPkgInfo;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.VnfPkgInfoLinks;

public class LinksSol003 {

	private LinksSol003() {
		// Nothing.
	}

	public static void makeLinks(final VnfPkgInfo vnfPackage) {
		final String vnfPkgId = vnfPackage.getId();
		final VnfPkgInfoLinks links = new VnfPkgInfoLinks();

		final Link self = new Link();
		self.setHref(linkTo(methodOn(VnfPackages351Sol003Api.class).vnfPackagesVnfPkgIdGet(vnfPkgId, null)).withSelfRel().getHref());
		links.self(self);

		final Link vnfd = new Link();
		vnfd.setHref(linkTo(methodOn(VnfPackages351Sol003Api.class).vnfPackagesVnfPkgIdVnfdGet(vnfPkgId, null)).withSelfRel().getHref());
		links.setVnfd(vnfd);

		final Link packageContent = new Link();
		packageContent.setHref(linkTo(methodOn(VnfPackages351Sol003Api.class).vnfPackagesVnfPkgIdPackageContentGet(vnfPkgId, "")).withSelfRel().getHref());
		links.setPackageContent(packageContent);
		vnfPackage.setLinks(links);
	}

}
