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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.List;

import org.jgroups.Address;
import org.jgroups.JChannel;
import org.jgroups.Receiver;
import org.jgroups.View;

public class JgroupsTest {
	private static final int MAX_ROUNDS = 1_000;
	private static final int SLEEP_TIME_IN_MILLIS = 1000;

	// @Test
	void testName() throws Exception {
		assertTrue(true);
	}

	void testName2() throws Exception {
		final InputStream is = getClass().getResourceAsStream("/udp.xml");
		final JChannel channel = new JChannel(is);
		final Receiver r = new JGroupReceiver(channel);
		channel.setReceiver(r);
		channel.setDiscardOwnMessages(true);
		channel.connect("mano-cluster");
		for (int round = 0; round < MAX_ROUNDS; round++) {
			checkLeaderStatus(channel);
			sleep();
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(SLEEP_TIME_IN_MILLIS);
		} catch (final InterruptedException e) {
			// Ignored
		}
	}

	private static void checkLeaderStatus(final JChannel channel) {
		final View view = channel.getView();
		final List<Address> address = view.getMembers();
		System.out.println("============================");
		address.forEach(System.out::println);

	}
}
