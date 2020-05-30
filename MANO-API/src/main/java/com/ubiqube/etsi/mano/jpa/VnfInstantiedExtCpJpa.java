package com.ubiqube.etsi.mano.jpa;

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp;

public interface VnfInstantiedExtCpJpa extends VnfInstantiedBaseJpa<com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedExtCp> {

	List<VnfInstantiatedExtCp> findAllByVnfLcmOpOccs_VnfInstance(VnfInstance vnfInstance);
}
