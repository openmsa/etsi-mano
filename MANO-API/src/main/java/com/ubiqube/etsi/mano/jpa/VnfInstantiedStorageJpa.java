package com.ubiqube.etsi.mano.jpa;

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.VnfInstance;
import com.ubiqube.etsi.mano.dao.mano.VnfInstantiatedStorage;

public interface VnfInstantiedStorageJpa extends VnfInstantiedBaseJpa<VnfInstantiatedStorage> {

	List<VnfInstantiatedStorage> findAllByVnfLcmOpOccs_VnfInstance(VnfInstance vnfInstance);

}
