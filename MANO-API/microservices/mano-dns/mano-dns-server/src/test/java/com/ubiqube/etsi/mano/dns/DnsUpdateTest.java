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
package com.ubiqube.etsi.mano.dns;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Message;
import org.xbill.DNS.Name;
import org.xbill.DNS.Resolver;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.TSIG;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;
import org.xbill.DNS.Update;

public class DnsUpdateTest {

	private static Resolver createResolver() throws UnknownHostException, TextParseException {
		final SimpleResolver resolver = new SimpleResolver("127.0.0.1");
		resolver.setPort(2049);
		resolver.setTSIGKey(new TSIG(Name.fromString("hmac-sha512."), "generic-tsig", "/+LaODCjgOoaVKriWqTZCI7s9k/v83zvKWnf048LWP92GGGNspVzarOhjlXn/B2Yf2glgxVORqzfgs1J4RS8IA=="));
		resolver.setTCP(true);
		return resolver;
	}

	@Test
	void testName() throws Exception {
		final Resolver resolver = createResolver();

		final Lookup lookup = new Lookup("ns1.mano.ubiqube.com", Type.A);
		lookup.setResolver(resolver);
		lookup.run();
		if (lookup.getResult() == Lookup.SUCCESSFUL) {
			System.out.println(lookup.getAnswers()[0].rdataToString());
		}

		final Name zone = Name.fromConstantString("mano.ubiqube.com.");
		final Name host = Name.fromString("test.mano.ubiqube.com.");
		final Update update = new Update(zone);
		update.add(host, Type.A, 86400, "1.2.3.4");
		final Message response = resolver.send(update);
		System.out.println("" + response);
	}

	@Test
	public void testSubDomain() throws IOException {
		final Name zone = Name.fromConstantString("mano.ubiqube.com.");
		final Name host = Name.fromString("test.79058b44-6343-4cd7-b439-5a3605326a46.mano.ubiqube.com.");
		final Update update = new Update(zone);
		update.add(host, Type.A, 86400, "1.1.1.1");
		final Resolver resolver = createResolver();
		final Message response = resolver.send(update);
		System.out.println("" + response);
	}
}
