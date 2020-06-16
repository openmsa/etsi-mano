package com.ubiqube.etsi.mano.jpa;

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedVirtualLink;

public interface VnfInstantiedVirtualLinkJpa extends VnfInstantiedBaseJpa<VnfInstantiatedVirtualLink> {

	List<VnfInstantiatedVirtualLink> findAllByVnfLcmOpOccs_VnfInstance(VnfInstance vnfInstance);
}
