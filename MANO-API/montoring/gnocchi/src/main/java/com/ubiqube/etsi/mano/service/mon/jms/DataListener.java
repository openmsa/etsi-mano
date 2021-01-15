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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import com.ubiqube.etsi.mano.service.event.jms.GnocchiPollAction;

public class DataListener {

	private static final Logger LOG = LoggerFactory.getLogger(DataListener.class);

	@JmsListener(destination = "mano.monitoring.gnocchi.data", concurrency = "10", containerFactory = "gnocchiDataFactory")
	public void onGnocchiData(final GnocchiPollAction action) {
		LOG.debug("Receive: {}", action);
	}
}
