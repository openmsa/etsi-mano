package com.ubiqube.etsi.mano.controller.nspm;

import java.util.List;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.pm.PerformanceReport;
import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;

public interface NfvoPmController {

	List<PmJob> query(String filter);

	void deleteById(UUID fromString);

	PmJob getById(UUID fromString);

	PerformanceReport getReportById(String pmJobId, String reportId);

	PmJob save(PmJob req);

}
