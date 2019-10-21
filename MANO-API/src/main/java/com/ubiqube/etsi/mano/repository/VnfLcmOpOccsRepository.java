package com.ubiqube.etsi.mano.repository;

import java.util.List;

import com.ubiqube.etsi.mano.model.nslcm.sol003.VnfLcmOpOcc;

public interface VnfLcmOpOccsRepository extends CrudRepository<VnfLcmOpOcc>, BinaryRepository {

	void save(List<VnfLcmOpOcc> vnfLcmOpOccsIds);

}
