package com.ubiqube.etsi.mano.service;

import com.ubiqube.etsi.mano.nfvo.v261.model.lcmgrant.GrantRequest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface VnfmFactory {

	<T> void makeGrantRequestLink(final GrantRequest manoGrant);
}
