/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.mano.geoms;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SshTest {

	@Test
	void testName() throws Exception {
		final java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		final JSch jsch = new JSch();
		final Session session = jsch.getSession("admin", "10.30.19.14", 22);
		session.setConfig(config);
		session.setPassword("$ubiq38!");
		session.connect();

		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand("get system admin");
		channel.setInputStream(null);
		((ChannelExec) channel).setErrStream(System.err);

		try (final InputStream in = channel.getInputStream()) {
			channel.connect();
			consume(in, channel);
			channel.disconnect();
		}

		channel = session.openChannel("exec");
		try (final InputStream in = channel.getInputStream()) {
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			((ChannelExec) channel).setCommand("ps ax");
			channel.connect();
			consume(in, channel);
		}

		channel.disconnect();
		session.disconnect();
		System.out.println("DONE");
	}

	private static void consume(final InputStream in, final Channel channel) throws IOException {
		final byte[] tmp = new byte[1024];
		while (true) {
			while (in.available() > 0) {
				final int i = in.read(tmp, 0, 1024);
				if (i < 0) {
					break;
				}
				System.out.print(new String(tmp, 0, i));
			}
			if (channel.isClosed()) {
				System.out.println("exit-status: " + channel.getExitStatus());
				break;
			}
			try {
				Thread.sleep(100);
			} catch (final Exception ee) {
			}
		}
	}
}
