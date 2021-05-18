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
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class SnmpTest {

	@Test
	void snmpv2() throws Exception {
		final PDU pdu = new PDU();
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.1"))); // sysDescr
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.1"))); // ifNumber
		pdu.setType(PDU.GETNEXT);

		final Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
		snmp.listen();

		final Address address = GenericAddress.parse("udp:10.31.1.248/161");
		final CommunityTarget<Address> target = new CommunityTarget<>();
		target.setCommunity(new OctetString("ubiqube"));
		target.setAddress(address);
		target.setVersion(SnmpConstants.version1);

		final ResponseEvent<Address> response = snmp.send(pdu, target);
		if (response.getResponse() == null) {
			// request timed out
			System.out.println("Time out.");
		} else {
			System.out.println("Received response from: " + response.getPeerAddress());
			// dump response PDU
			System.out.println(response.getResponse().toString());
		}
	}

	@Test
	void snmpV3() throws Exception {
		final ScopedPDU pdu = new ScopedPDU();
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.1"))); // ifNumber
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.2.1.10"))); // ifInOctets
		pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.2.2.1.16"))); // ifOutOctets
		pdu.setType(PDU.GET);

		SecurityProtocols.getInstance().addDefaultProtocols();
		SecurityProtocols.getInstance().addAuthenticationProtocol(new AuthMD5());
		SecurityProtocols.getInstance().addPrivacyProtocol(new PrivDES());
		final Address address = GenericAddress.parse("udp:10.31.1.247/161");
		final UserTarget<Address> target = new UserTarget<>();
		target.setAddress(address);
		target.setRetries(5);
		target.setTimeout(1000);
		target.setVersion(SnmpConstants.version3);
		target.setSecurityLevel(SecurityLevel.AUTH_PRIV);
		target.setSecurityName(new OctetString(new OctetString("ovi")));

		final USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
		usm.setEngineDiscoveryEnabled(true);
		final MPv3 mPv3 = new MPv3(usm);
		final UsmUser user = new UsmUser(new OctetString("ovi"), AuthMD5.ID, new OctetString("ubiqube123"), PrivDES.ID, new OctetString("ubiqube123"));
		usm.addUser(user);

		final Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
		// SecurityModels.getInstance().addSecurityModel(usm);
		final SecurityModels securityModels = new SecurityModels();
		securityModels.addSecurityModel(usm);
		mPv3.setSecurityModels(securityModels);
		((MPv3) snmp.getMessageProcessingModel(MPv3.ID)).setSecurityModels(securityModels);
		snmp.getMessageDispatcher().addMessageProcessingModel(mPv3);
		snmp.listen();
		snmp.getUSM().addUser(new OctetString("ovi"),
				new UsmUser(new OctetString("ovi"), AuthMD5.ID,
						new OctetString("ubiqube123"), PrivDES.ID,
						new OctetString("ubiqube123")));
		final ResponseEvent<Address> response = snmp.send(pdu, target);
		if (response.getResponse() == null) {
			// request timed out
			System.out.println("Time out.");
		} else {
			System.out.println("Received response from: " + response.getPeerAddress());
			// dump response PDU
			System.out.println(response.getResponse().toString());
		}
	}
}
