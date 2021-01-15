package com.ubiqube.etsi.mano.controller.nspm;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.pm.Threshold;

public interface NfvoThresholdController {

	List<Threshold> query(String filter);

	Threshold save(Threshold threshold);

	void delete(UUID id);

	Threshold getById(UUID fromString);

}
