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

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.mon.dao.TelemetryMetricsResult;
import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;
import com.ubiqube.etsi.mano.service.mon.vim.GnocchiSubTelemetry;
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
	private final JmsTemplate jmsTopicTemplate;

	public NotificationController(final VimManager _vimManager, final GnocchiSubTelemetry _gnocchiSubTelemetry, @Qualifier("jmsTopicTemplate") final JmsTemplate jmsTopicTemplate) {
		vimManager = _vimManager;
		gnocchiSubTelemetry = _gnocchiSubTelemetry;
		this.jmsTopicTemplate = jmsTopicTemplate;
	}

	@JmsListener(destination = "mano.monitoring.gnocchi.data-polling", concurrency = "10")
	public void onGnocchiDataPolling(final BatchPollingJob job) {
		// Get Gnocchi instances and sub metrics.
		final List<TelemetryMetricsResult> allHostMetrics = job.getHosts().stream()
				.flatMap(x -> gnocchiSubTelemetry.getMetricsForVnfc(vimManager.findVimById(job.getVimId()), x, job.getMetrics(), job.getId()).stream())
				.toList();
		// Now we have a batch of metrics. Send to data poller.
		allHostMetrics.forEach(x -> {
			jmsTopicTemplate.convertAndSend("mano.monitoring.gnocchi.data", x);
		});
	}
}
