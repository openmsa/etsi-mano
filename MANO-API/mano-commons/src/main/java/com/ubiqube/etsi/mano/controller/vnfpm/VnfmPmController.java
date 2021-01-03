package com.ubiqube.etsi.mano.controller.vnfpm;

import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.pm.PerformanceReport;
import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;

public interface VnfmPmController {

	void delete(UUID fromString);

	PmJob findById(UUID fromString);

	PerformanceReport findReport(UUID fromString, UUID fromString2);

	PmJob save(PmJob res);

}
