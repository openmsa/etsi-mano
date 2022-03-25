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
package com.ubiqube.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ZabbixProbe {

	@Test
	void testName() throws Exception {
		final int port = 10050;
		final String ip = "10.31.1.182";
		final Socket socket = new Socket(ip, port);

		final byte[] pkt = buildPayload("agent.version");
		// final ObjectOutputStream oos = new
		// ObjectOutputStream(socket.getOutputStream());
		socket.getOutputStream().write(pkt);
		socket.getOutputStream().flush();
		// oos.write(buildPayload("agent.version"));
		// oos.flush();
		// 0000 00 00 ff fe 00 00 00 00 00 00 00 00 00 00 08 00 ................
		// 0010 45 00 00 47 a8 d3 40 00 40 06 a7 22 0a 1f 01 1d E..G..@.@.."....
		// 0020 c0 a8 1e d7 27 42 d9 b2 ca b1 56 a5 18 75 f3 10 ....'B....V..u..
		// 0030 80 18 00 e3 7b 41 00 00 01 01 08 0a 4b 99 0a a2 ....{A......K...
		// 0040 61 4f e1 42 5a 42 58 44 01 06 00 00 00 00 00 00 aO.BZBXD........
		// 0050 00 34 2e 30 2e 32 39 .4.0.29
		// final byte[] header = new byte[13];
		// final int res = socket.getInputStream().read(header);
		// System.out.println(bytesToHex(header));

		// final byte[] lengthBuff = new byte[4];
		// System.arraycopy(header, 5, lengthBuff, 0, 4);
		// System.out.println(bytesToHex(lengthBuff));
		// final ByteBuffer wrap =
		// ByteBuffer.wrap(lengthBuff).order(ByteOrder.LITTLE_ENDIAN);
		// final int il = wrap.getInt();
		final int il = getLenght(socket.getInputStream());
		System.err.println(" > " + il);
		// final byte[] payload = new byte[il];
		// socket.getInputStream().read(payload);
		final List<String> payload = getPayload(socket.getInputStream(), il);
		System.out.println("res=" + payload);
		// final String message = (String) ois.readObject();
		// System.out.println(message);
		socket.close();
		assertTrue(true);
	}

	private static List<String> getPayload(final InputStream is, final int size) throws IOException {
		final List<String> ret = new ArrayList<>();
		final byte[] payload = new byte[size];
		is.read(payload);
		int start = 0;
		for (int i = 0; i < size; i++) {
			if (payload[i] == 0) {
				final byte[] target = new byte[i - start];
				System.arraycopy(payload, start, target, 0, i - start);
				ret.add(new String(target));
				start = i;
			}
		}
		final byte[] target = new byte[size - start];
		System.arraycopy(payload, start, target, 0, size - start);
		ret.add(new String(target));
		return ret;
	}

	private static int getLenght(final InputStream is) throws IOException {
		final byte[] fullHeader = new byte[13];
		final byte[] lengthBuff = new byte[4];
		is.read(fullHeader);
		System.arraycopy(fullHeader, 5, lengthBuff, 0, 4);
		System.out.println(bytesToHex(lengthBuff));
		final ByteBuffer wrap = ByteBuffer.wrap(lengthBuff).order(ByteOrder.LITTLE_ENDIAN);
		return wrap.getInt();
	}

	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	public static String bytesToHex(final byte[] bytes) {
		final char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			final int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}

	private static byte[] buildPayload(final String metric) {
		final byte[] data = metric.getBytes();
		final byte[] header = {
				'Z', 'B', 'X', 'D', '\1',
				(byte) (data.length & 0xFF),
				(byte) (data.length >> 8 & 0xFF),
				(byte) (data.length >> 16 & 0xFF),
				(byte) (data.length >> 24 & 0xFF),
				'\0', '\0', '\0', '\0' };

		final byte[] packet = new byte[header.length + data.length];
		System.arraycopy(header, 0, packet, 0, header.length);
		System.arraycopy(data, 0, packet, header.length, data.length);
		return packet;
	}
}
