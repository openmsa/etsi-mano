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
package com.ubiqube.mano.discovery.jgroups;

import java.util.List;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Receiver;
import org.jgroups.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class JGroupReceiver implements Receiver {

	private static final Logger LOG = LoggerFactory.getLogger(JGroupReceiver.class);

	private View lastView;
	private final JChannel channel;
	private final ObjectMapper mapper;

	public JGroupReceiver(final JChannel channel) {
		this.channel = channel;
		this.mapper = new ObjectMapper();
	}

	@Override
	public void viewAccepted(final View newView) {
		// Save view if this is the first
		if (lastView == null) {
			System.out.println("Received initial view:");
			newView.forEach(x -> LOG.debug("{}", x));
		} else {
			// Compare to last view
			LOG.debug("Received new view.");

			final List<Address> newMembers = View.newMembers(lastView, newView);
			LOG.debug("New members: ");
			newMembers.forEach(this::handleNewMember);

			final List<Address> exMembers = View.leftMembers(lastView, newView);
			LOG.debug("Exited members:");
			exMembers.forEach(x -> LOG.debug("{}", x));
		}
		lastView = newView;
	}

	//
	private void handleNewMember(final Address addr) {
		try {
			LOG.info("Sending hello to {}", addr);
			final EventMessage msg = EventMessage.builder().action("announce").payload("payload").build();
			channel.send(addr, mapper.writeValueAsString(msg));
		} catch (final Exception e) {
			LOG.error("", e);
		}
	}
}
