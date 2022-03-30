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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.InetAddress;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;
import org.opendaylight.aaa.cert.api.ICertificateManager;
import org.opendaylight.ovsdb.lib.OvsdbClient;
import org.opendaylight.ovsdb.lib.impl.NettyBootstrapFactory;
import org.opendaylight.ovsdb.lib.impl.OvsdbConnectionService;

import com.google.common.util.concurrent.ListenableFuture;

class LibvirtTest {
	private Connect connection() {
		Connect conn = null;
		try {
			conn = new Connect("qemu+ssh://root@10.31.1.240/system", true);
		} catch (final LibvirtException e) {
			System.out.println("exception caught:" + e);
			System.out.println(e.getError());
		}
		return conn;
	}

	void testName() throws Exception {
		final Connect conn = connection();
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
		assertTrue(true);
	}

	void test2() throws Exception {
		final Connect conn = connection();
		final String[] doms = conn.listDefinedDomains();
		for (final String string : doms) {
			System.out.println(" - " + string);
		}
		System.out.println("Host: " + conn.getHostName());
		System.out.println("Caps: " + conn.getCapabilities());
		System.out.println("Sys info: " + conn.getSysinfo());
		System.out.println("Type: " + conn.getType());
		System.out.println("Version: " + conn.getVersion());
		final ICertificateManager certManagerSrv = null;
		final NettyBootstrapFactory bootstrapFactory = new NettyBootstrapFactory();
		final OvsdbConnectionService ovs = new OvsdbConnectionService(bootstrapFactory, certManagerSrv);
		final OvsdbClient client = ovs.connect(InetAddress.getByName("os-victoria"), 6642);
		final ListenableFuture<List<String>> db = client.getDatabases();
		final List<String> list = db.get();
		System.out.println("" + list);
		assertTrue(true);
	}

	@Test
	void testTest() throws Exception {
		assertTrue(true);
	}
}
