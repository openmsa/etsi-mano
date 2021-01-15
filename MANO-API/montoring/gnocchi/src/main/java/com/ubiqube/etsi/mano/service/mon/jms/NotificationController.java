/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.mon.jms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.mon.GnocchiTelemetryMetrics;
import com.ubiqube.etsi.mano.dao.mano.pm.PmJob;
import com.ubiqube.etsi.mano.service.event.jms.GnocchiPollAction;
import com.ubiqube.etsi.mano.service.vim.GnocchiSubTelemetry;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimManager;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class NotificationController {
	private final VimManager vimManager;
	private final GnocchiSubTelemetry gnocchiSubTelemetry;
	private final JmsTemplate jmsTemplate;

	public NotificationController(final VimManager _vimManager, final GnocchiSubTelemetry _gnocchiSubTelemetry, final JmsTemplate _jmsTemplate) {
		vimManager = _vimManager;
		gnocchiSubTelemetry = _gnocchiSubTelemetry;
		jmsTemplate = _jmsTemplate;
	}

	@JmsListener(destination = "mano.monitoring.gnocchi.data-polling", concurrency = "10")
	public void onGnocchiDataPolling(final PmJob job) {
		final Vim vim = vimManager.getVimById(job.getVimConnectionInformation().getId());
		// Get Gnocchi instances and sub metrics.
		final List<GnocchiTelemetryMetrics> lst = job.getObjectInstanceIds().stream()
				.flatMap(x -> gnocchiSubTelemetry.getMetricsForVnfc(job.getVimConnectionInformation(), x).stream())
				.collect(Collectors.toList());
		// Flatten metrics.
		final ArrayList<GnocchiPollAction> flat = flatMetrics(job);
		// Filter metrics.
		merge(flat, lst);
		// Now we have a batch of metrics. Send to data poller.
		flat.forEach(x -> {
			jmsTemplate.convertAndSend("mano.monitoring.gnocchi.data", x);
		});
	}

	private void merge(final ArrayList<GnocchiPollAction> flat, final List<GnocchiTelemetryMetrics> lst) {
		//
	}

	private ArrayList<GnocchiPollAction> flatMetrics(final PmJob job) {
		final ArrayList<GnocchiPollAction> ret = new ArrayList<>();
		job.getObjectInstanceIds().forEach(x -> {
			job.getCriteria().getPerformanceMetric().forEach(y -> ret.add(new GnocchiPollAction(job.getVimConnectionInformation(), x, y)));
		});
		return ret;
	}

}
