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
package com.ubiqube.etsi.mano.service.mon;

import org.junit.jupiter.api.Test;
import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;

public class LibvirtTest {

	@Test
	void testName() throws Exception {
		Connect conn = null;
		try {
			conn = new Connect("qemu+ssh://root@10.31.1.240/system", true);
		} catch (final LibvirtException e) {
			System.out.println("exception caught:" + e);
			System.out.println(e.getError());
		}
		final String[] doms = conn.listDefinedDomains();
		for (final String string : doms) {
			System.out.println(" - " + string);
		}
		final Domain testDomain = conn.domainLookupByName("instance-0000048c");
		System.out.println("Domain:" + testDomain.getName() + " id " +
				testDomain.getID() + " running " +
				testDomain.getOSType());
		System.out.println("" + testDomain.getName());
		System.out.println("" + testDomain.getUUIDString()); // OS uuid
		final DomainInfo domInfo = testDomain.getInfo();
		System.out.println("cpu: " + domInfo.cpuTime + ", mem: " + domInfo.memory);
		// - instance-00000246
		// - instance-00000244

		//
		final String[] iface = conn.listNetworks();
		for (final String string : iface) {
			System.out.println(" - " + string);
		}
	}
}
