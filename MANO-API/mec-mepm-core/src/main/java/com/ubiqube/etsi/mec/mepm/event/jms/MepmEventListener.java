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
package com.ubiqube.etsi.mec.mepm.event.jms;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.ubiqube.etsi.mano.service.event.jms.ActionMessage;
import com.ubiqube.etsi.mec.mepm.event.MepmActionController;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class MepmEventListener {

	private static final Logger LOG = LoggerFactory.getLogger(MepmEventListener.class);

	private final MepmActionController mepmActionController;

	public MepmEventListener(final MepmActionController _mepmActionController) {
		mepmActionController = _mepmActionController;
	}

	@JmsListener(destination = "system.actions.mepm", concurrency = "5")
	@Transactional(TxType.NEVER)
	@org.springframework.transaction.annotation.Transactional(propagation = Propagation.NEVER)
	public void onEvent(final ActionMessage ev) {
		LOG.info("JMS MEPM ActionController Receiving Action: {}", ev);
		mepmActionController.dispatch(ev.getActionType(), ev.getObjectId(), ev.getParameters());
		LOG.info("JMS MEPM ActionController Done for event: {}", ev);
	}
}
