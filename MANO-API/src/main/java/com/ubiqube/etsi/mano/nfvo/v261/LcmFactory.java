package com.ubiqube.etsi.mano.nfvo.v261;

import javax.annotation.Nonnull;

import com.ubiqube.etsi.mano.nfvo.v261.model.Link;
import com.ubiqube.etsi.mano.vnfm.v261.model.nslcm.VnfInstanceLinks;

public class LcmFactory {

	@Nonnull
	public static VnfInstanceLinks createVnfInstancesLink(final String hrefSelf, final String hrefChangeExtConn, final String hrefChangeFlavor, final String hrefHeal, final String hrefIndicators, final String hrefInstanciate, final String hrefOperate, final String hrefScale, final String hrefScaleToLevel, final String hrefTerminate) {
		final VnfInstanceLinks vnfInstanceLinks = new VnfInstanceLinks();
		final Link self = new Link();
		self.setHref(hrefSelf);
		vnfInstanceLinks.self(self);

		final Link changeExtConn = new Link();
		changeExtConn.setHref(hrefChangeExtConn);
		vnfInstanceLinks.setChangeExtConn(changeExtConn);

		final Link changeFlavour = new Link();
		changeFlavour.setHref(hrefChangeFlavor);
		vnfInstanceLinks.setChangeFlavour(changeFlavour);

		final Link heal = new Link();
		heal.setHref(hrefHeal);
		vnfInstanceLinks.setHeal(heal);

		final Link indicators = new Link();
		indicators.setHref(hrefIndicators);
		vnfInstanceLinks.setIndicators(indicators);

		final Link instantiate = new Link();
		instantiate.setHref(hrefInstanciate);
		vnfInstanceLinks.setInstantiate(instantiate);

		final Link operate = new Link();
		operate.setHref(hrefOperate);
		vnfInstanceLinks.setOperate(operate);

		final Link scale = new Link();
		scale.setHref(hrefScale);
		vnfInstanceLinks.setScale(scale);

		final Link terminate = new Link();
		terminate.setHref(hrefTerminate);
		vnfInstanceLinks.setTerminate(terminate);

		final Link scaleToLevel = new Link();
		scaleToLevel.setHref(hrefScaleToLevel);
		vnfInstanceLinks.setScaleToLevel(scaleToLevel);
		return vnfInstanceLinks;
	}

}
