package com.ubiqube.etsi.mano.repository;

import java.util.List;

import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;
import com.ubiqube.etsi.mano.model.nslcm.sol005.NsLcmOpOccsNsLcmOpOcc;

public interface LcmOpOccsRepository extends CrudRepository<NsLcmOpOccsNsLcmOpOcc> {

	void save(List<VnfLcmOpOcc> vnfLcmOpOccsIds);

}
