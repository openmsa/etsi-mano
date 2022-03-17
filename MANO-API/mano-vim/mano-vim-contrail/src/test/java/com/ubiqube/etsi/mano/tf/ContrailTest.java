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
package com.ubiqube.etsi.mano.tf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;

import net.juniper.contrail.api.ApiConnector;
import net.juniper.contrail.api.ApiConnectorFactory;
import net.juniper.contrail.api.ApiObjectBase;
import net.juniper.contrail.api.Status;
import net.juniper.contrail.api.types.IpamSubnetType;
import net.juniper.contrail.api.types.IpamSubnets;
import net.juniper.contrail.api.types.NetworkIpam;
import net.juniper.contrail.api.types.PortTuple;
import net.juniper.contrail.api.types.ServiceInstance;
import net.juniper.contrail.api.types.ServiceTemplate;
import net.juniper.contrail.api.types.ServiceTemplateInterfaceType;
import net.juniper.contrail.api.types.ServiceTemplateType;
import net.juniper.contrail.api.types.SubnetType;
import net.juniper.contrail.api.types.VirtualMachineInterface;
import net.juniper.contrail.api.types.VirtualNetwork;
import net.juniper.contrail.api.types.VirtualNetworkType;
import net.juniper.contrail.api.types.VnSubnetsType;

class ContrailTest {

	private static final Logger LOG = LoggerFactory.getLogger(ContrailTest.class);
	private final SystemConnections vimConnectionInformation;

	public ContrailTest() {
		vimConnectionInformation = new SystemConnections();
		vimConnectionInformation.setInterfaceInfo(new HashMap<>());
		vimConnectionInformation.getInterfaceInfo().put("endpoint", "http://192.168.1.36:8082");
		vimConnectionInformation.setAccessInfo(new HashMap<>());
		vimConnectionInformation.getAccessInfo().put("username", "ncroot");
		vimConnectionInformation.getAccessInfo().put("password", "password");
	}

	private final static ApiConnector getConnection() {
		return ApiConnectorFactory.build("192.168.1.36", 8082).credentials("", "");
	}

	private static Status executeCreate(final ApiObjectBase obj) throws IOException {
		final ApiConnector conn = getConnection();
		final Status st = conn.create(obj);
		LOG.info("{} ", st.getClass());
		st.ifFailure(new LogErrorHandler());
		return st;
	}

	@Test
	void testConnection() throws IOException {
		final ApiConnector conn = getConnection();
		final List<? extends ApiObjectBase> res = conn.list(PortTuple.class, List.of());
		res.forEach(x -> {
			final PortTuple xx = (PortTuple) x;
			LOG.info("{} {}", xx.getName(), xx.getUuid());
		});
		LOG.info("{}", res);
	}

	@Test
	void createServiceTemplateTest() throws IOException {
		final ApiConnector conn = getConnection();
		final ServiceTemplate obj = new ServiceTemplate();
		obj.setDisplayName("ovi test");
		obj.setName("ovi-name");
		final ServiceTemplateType props = new ServiceTemplateType();
		props.setServiceVirtualizationType("virtual-machine"); // Any of [‘virtual-machine’, ‘network-namespace’, ‘vrouter-instance’,
																// ‘physical-device’]
		props.addInterfaceType(new ServiceTemplateInterfaceType("left"));
		props.addInterfaceType(new ServiceTemplateInterfaceType("right"));
		props.setServiceMode("in-network"); // Any of [‘transparent’, ‘in-network’, ‘in-network-nat’]
		props.setServiceType("firewall"); // Any of [‘firewall’, ‘analyzer’, ‘source-nat’, ‘loadbalancer’]
		props.setVersion(2);
		obj.setProperties(props);
		final Status st = conn.create(obj);
		LOG.info("{} ", st.getClass());
		st.ifFailure(new LogErrorHandler());
	}

	@Test
	void createServiceInstanceTest() throws IOException {
		final ApiConnector conn = getConnection();
		final ServiceInstance root = new ServiceInstance();
		final ServiceTemplate st = new ServiceTemplate();
		root.setServiceTemplate(st);
	}

	@Test
	void createPortTupleTest() throws IOException {
		final ApiConnector conn = getConnection();
		final PortTuple root = new PortTuple();
		root.setDisplayName("ovi-tuple");
		root.setName("ovi-tuple-name");
		final ServiceInstance parent = null;
		root.setParent(parent);
	}

	@Test
	void createIpamTest() throws IOException {
		final ApiConnector conn = getConnection();
		final NetworkIpam root = new NetworkIpam();
		root.setDisplayName("ovi-ipam");
		root.setName("ovi-ipam-name");
		// root.setIpamSubnetMethod("user-defined-subnet"); // Any of
		// [‘user-defined-subnet’, ‘flat-subnet’, ‘auto-subnet’]
		final List<IpamSubnetType> subnets = new ArrayList<>();
		final SubnetType subnet = new SubnetType("5.6.7.8", 24);
		final IpamSubnetType ipt = new IpamSubnetType(subnet);
		subnets.add(ipt);
		final IpamSubnets ipam_subnets = new IpamSubnets(subnets);
		// root.setIpamSubnets(ipam_subnets);
		executeCreate(root);
		LOG.info("Done crating ipam.");

		// conn.update(root);
	}

	@Test
	void deleteIpam() throws IOException {
		final ApiConnector conn = getConnection();
		final NetworkIpam root = new NetworkIpam();
		root.setUuid("af605e1f-f1a4-4d90-a39c-924a72f3d3c5");
		conn.delete(root);
	}

	@Test
	void createVirtualNetworkTest() throws IOException {
		final ApiConnector conn = getConnection();
		final VirtualNetwork root = new VirtualNetwork();
		root.setDisplayName("ovi display name");
		root.setName("ovi-vl");
		// final NetworkIpam ipam = (NetworkIpam) conn.findById(NetworkIpam.class,
		// "8e2e057c-204f-499c-9782-0367e0bc0aa1");
		final NetworkIpam ipam = new NetworkIpam();
		ipam.setName("ovi-ipam-name");
		final List<IpamSubnetType> subnets = new ArrayList<>();
		final SubnetType subnet = new SubnetType("5.6.7.8", 24);
		final IpamSubnetType ipt = new IpamSubnetType(subnet);
		subnets.add(ipt);
		final IpamSubnets ipam_subnets = new IpamSubnets(subnets);
		final VirtualNetworkType vnp = new VirtualNetworkType();
		final VnSubnetsType data = new VnSubnetsType();
		data.addIpamSubnets(ipt);
		root.setNetworkIpam(ipam, data);
		executeCreate(root);
	}

	@Test
	void createVirtualMachineInterfaceTest() throws IOException {
		final ApiConnector conn = getConnection();
		final VirtualMachineInterface root = new VirtualMachineInterface();
		root.setName("vmi-ovi");
		root.setDisplayName("VirtMacInt-OVI");
		final PortTuple obj = null;
		root.setPortTuple(obj);
		executeCreate(root);
	}

	@Test
	void createServiceInstance() {
		final ContrailApi api = new ContrailApi();
		// final String tmpl = api.createServiceTemplate(vimConnectionInformation,
		// "ovi-template");
		final String left = "6e5fc975-9265-43f7-8923-89f2cce03563";
		final String right = "7ae1f037-b6f5-431a-bb7b-48d8644b9033";
		// final String si = api.createServiceInstance(vimConnectionInformation,
		// "ovi-si", tmpl, left, right);
		// final String pt = api.createPortTuple(vimConnectionInformation, "ovi-pt",
		// si);
		final String vmi = "4880c971-0359-43f4-a2a1-d63ad72dbab4";
		final String ptStr = "c45f7463-67b9-4436-a1f3-c80d211605d2";
		api.updatePort(vimConnectionInformation, vmi, ptStr, "left");
	}

	@Test
	void getTuplePort() {
		final ContrailFacade cf = new ContrailFacade();
		final VirtualMachineInterface obj = cf.findById(vimConnectionInformation, VirtualMachineInterface.class, "4880c971-0359-43f4-a2a1-d63ad72dbab4");
		LOG.debug("{}", obj);
	}
}
