package com.ubiqube.etsi.mano.controller.vnfpm;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.pm.Threshold;

public interface VnfmThresholdController {

	Threshold save(Threshold res);

	void delete(UUID fromString);

	Threshold findById(UUID fromString);

	List<Threshold> query(String filter);
}
