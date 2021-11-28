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
package com.ubiqube.etsi.mano.service.vim;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Extension;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.AbsoluteLimit;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.QuotaSet;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.dns.v2.Recordset;
import org.openstack4j.model.dns.v2.Zone;
import org.openstack4j.model.dns.v2.ZoneType;
import org.openstack4j.model.identity.v3.Endpoint;
import org.openstack4j.model.identity.v3.Service;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.Router;
import org.openstack4j.model.network.RouterInterface;
import org.openstack4j.model.network.SecurityGroupRule;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.model.telemetry.gnocchi.MetricCreate;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.L2Data;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.SecurityGroup;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.service.VimService;
import com.ubiqube.etsi.mano.vim.dto.SwImage;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Tag("Remote")
//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.Silent.class)
public class OpenStackTest {

	private static final Logger LOG = LoggerFactory.getLogger(OpenStackTest.class);

	@Mock
	private VimService vimJpa;

	private final MapperFacade mapper;

	private final VimConnectionInformation vimConnectionInformation;

	private final UUID id;

	public OpenStackTest() throws JsonParseException, JsonMappingException, FileNotFoundException, IOException {
		final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapper = mapperFactory.getMapperFacade();
		final ObjectMapper desMapper = new ObjectMapper();
		vimConnectionInformation = desMapper.readValue(new FileInputStream("src/test/resources/vim-connection/openstack.json"), VimConnectionInformation.class);
		id = UUID.randomUUID();
		vimConnectionInformation.setId(id);
		vimConnectionInformation.setVimId(id.toString());

	}

	static void testName() throws Exception {
		final Identifier domainIdentifier = Identifier.byName("Default");
		final OSClientV3 os = OSFactory.builderV3()
				.endpoint("http://10.31.1.240:5000/v3")
				.credentials("admin", "84612d9a2e404ac9", domainIdentifier)
				.scopeToProject(Identifier.byId("df1f081bf2d345099e6bb53f6b9407ff"))
				.authenticate();

		final List<? extends Service> ep = os.identity().serviceEndpoints().list();
		final Optional<? extends Service> l = ep.stream().filter(x -> x.getType().equals("placement")).findFirst();
		System.out.println("l=" + l.get());
		final Service service = l.get();
		final Endpoint endpoint = os.identity().serviceEndpoints().getEndpoint(service.getId());
		System.out.println("EDP=" + endpoint);
		// 1.17

		/*
		 * final RegionService regions = os.identity().regions(); final List<? extends
		 * Region> zl = regions.list(); zl.forEach(x -> System.out.println("" +
		 * x.getId()));
		 */
		/*
		 * final List<? extends Domain> domains = os.identity().domains().list();
		 * domains.forEach(x -> System.out.println("" + x.getName()));
		 *
		 * final List<? extends Flavor> flavors = os.compute().flavors().list();
		 * flavors.forEach(x -> System.out.println("" + x.getName()));
		 *
		 * final List<? extends Image> images = os.compute().images().list();
		 * images.forEach(x -> System.out.println("" + x.getName()));
		 */
		/*
		 * final List<? extends Server> servers = os.compute().servers().list();
		 * servers.forEach(x -> System.out.println("" + x.getName()));
		 */
		/*
		 * final Set<ServiceType> le = os.getSupportedServices(); System.out.println(""
		 * + le);
		 */
		// createServer(os);

	}

	public static void createServer(final OSClientV3 os) {
		final ServerCreate sc = Builders.server()
				.name("cirros-ovi")
				.flavor("1")
				.image("e5429d68-3f1a-43e6-b46b-f83700d771da")
				.addPersonality("/etc/motd", "Welcome to the new VM! Restricted access only")
				.networks(ImmutableList.of("3958926a-b986-41f4-a5d9-95a3474d3d7b"))
				.build();

//Boot the Server
		final Server server = os.compute().servers().boot(sc);
		assertNotNull(server);
	}

	static void testConn() throws Exception {
		final Identifier domainIdentifier = Identifier.byId("default");
		final Identifier projectIdentifier = Identifier.byId("df1f081bf2d345099e6bb53f6b9407ff");
		final OSClientV3 os = OSFactory.builderV3()
				.endpoint("http://10.31.1.240:5000/v3")
				.credentials("admin", "84612d9a2e404ac9", domainIdentifier)
				.scopeToProject(projectIdentifier)
				.authenticate();
		System.out.println("GOOD");
		os.compute().hostAggregates().list().forEach(System.out::println);
		System.out.println("================= SERVICES");
		os.compute().services().list().forEach(System.out::println);
		System.out.println("================= ZONE");
		os.compute().zones().list().forEach(System.out::println);
		System.out.println("================= SERVER GROUPS");
		os.compute().serverGroups().list().forEach(System.out::println);
		System.out.println("================= Agregates");
		os.compute().hostAggregates().list().forEach(System.out::println);
		assertNotNull(os);
	}

	static void testConn2() throws Exception {
		final Identifier domainIdentifier = Identifier.byId("default");
		final Identifier projectIdentifier = Identifier.byId("ede0540276a94b75ae3de044cd7cc235");
		final OSClientV3 os = OSFactory.builderV3()
				.endpoint("http://10.31.1.15:5000/v3")
				.credentials("admin", "5fd399078a8844de", domainIdentifier)
				.scopeToProject(projectIdentifier)
				.authenticate();
		System.out.println("GOOD");
		System.out.println("================= HA");
		os.compute().hostAggregates().list().forEach(System.out::println);
		System.out.println("================= SERVICES");
		os.compute().services().list().forEach(System.out::println);
		System.out.println("================= ZONE");
		os.compute().zones().list(true).forEach(System.out::println);
		System.out.println("================= SERVER GROUPS");
		os.compute().serverGroups().list().forEach(System.out::println);
		assertNotNull(os);
	}

	void testNetwork() throws Exception {
		final VnfStorage vnfStorage = new VnfStorage();
		vnfStorage.setSize(1 * 1000 * 1000 * 1000);
		final SoftwareImage softwareImage = new SoftwareImage();
		softwareImage.setContainerFormat("ContainerFmtStr");
		softwareImage.setDiskFormat("QCOW2");
		softwareImage.setImagePath("/home/olivier/cirros.qcow2");
		softwareImage.setName("cirros");
		softwareImage.setMinDisk(1000000000L);
		softwareImage.setSize(1000000000L);
		softwareImage.setVimId(id.toString());
		vnfStorage.setSoftwareImage(softwareImage);
		vnfStorage.setToscaName("JUnit-test-volume");
		when(vimJpa.findById(id)).thenReturn(Optional.of(vimConnectionInformation));
		final OpenStackVim vim = new OpenStackVim(mapper);
		final String lid = vim.storage(vimConnectionInformation).createStorage(vnfStorage, "junit-test");
		assertNotNull(lid);
	}

	public void testNetworkFunctions() throws Exception {
		final VlProtocolData vl = new VlProtocolData();
		final L2Data l2ProtocolData = new L2Data();
		l2ProtocolData.setMtu(1000);
		l2ProtocolData.setName("vl-junit");
		l2ProtocolData.setNetworkType("VXLAN");
		vl.setL2ProtocolData(l2ProtocolData);
		final L3Data l3ProtocolData = new L3Data();
		l3ProtocolData.setCidr("192.168.90.0/24");
		l3ProtocolData.setGatewayIp("192.168.90.127");
		l3ProtocolData.setL3Name("l3name");
		l3ProtocolData.setIpVersion("V4");
		vl.setL3ProtocolData(l3ProtocolData);
		LOG.info("==> {}", id);

		final OpenStackVim vim = new OpenStackVim(mapper);
		when(vimJpa.findById(id)).thenReturn(Optional.ofNullable(vimConnectionInformation));
		final String net = vim.network(vimConnectionInformation).createNetwork(vl, "Junit-vl", "mano.junit.net.", null);
		final IpPool ipPool = new IpPool();
		ipPool.setStartIpAddress("192.168.90.1");
		ipPool.setEndIpAddress("192.168.90.126");
		final String lid = vim.network(vimConnectionInformation).createSubnet(l3ProtocolData, ipPool, net);
		assertNotNull(lid);
	}

	public void uploadSoftwareImageTest() {
		final OpenStackVim vim = new OpenStackVim(mapper);
		when(vimJpa.findById(id)).thenReturn(Optional.ofNullable(vimConnectionInformation));
		final SoftwareImage img = new SoftwareImage();
		img.setContainerFormat("BARE");
		img.setDiskFormat("QCOW2");
		img.setMinDisk(1000000000L);
		img.setMinRam(1000000L);
		img.setName("Junit-uploaded-image");
		img.setSize(1L);
		img.setImagePath("/home/olivier/Downloads/cirros-0.5.1-x86_64-disk.img");
		final SwImage lid = vim.storage(vimConnectionInformation).uploadSoftwareImage(null, img);
		assertNotNull(lid);
	}

	void testFlavorGet() throws Exception {
		final OpenStackVim vim = new OpenStackVim(mapper);
		when(vimJpa.findById(id)).thenReturn(Optional.ofNullable(vimConnectionInformation));

		// Get m1.small
		final int numVcpu = 1;
		final long virtualMemorySize = 2 * 100000000L;
		final long disk = 20 * 1000000000L;
		final String lid = vim.getOrCreateFlavor(vimConnectionInformation, "Junit-flavor", numVcpu, virtualMemorySize, disk, new HashMap<>());
		assertNotNull(lid);
	}

	void testCompute() throws Exception {
		final OpenStackVim vim = new OpenStackVim(mapper);
		when(vimJpa.findById(id)).thenReturn(Optional.ofNullable(vimConnectionInformation));

		final VnfCompute vnfc = new VnfCompute();
		final SoftwareImage softwareImage = new SoftwareImage();
		softwareImage.setName("cirros");
		vnfc.setSoftwareImage(softwareImage);
		vnfc.setName("vdu01");
		final List<String> networks = new ArrayList<>();
		final List<String> storages = new ArrayList<>();
		final String lid = vim.createCompute(vimConnectionInformation, "junit-name", "12745412-08b4-489c-95b0-eb2fd4a98b36", "e5429d68-3f1a-43e6-b46b-f83700d771da", networks, storages, null, List.of(), List.of());
		assertNotNull(lid);
	}

	private static OSClientV3 getTrainConnection() {
		final Identifier domainIdentifier = Identifier.byId("default");
		final Identifier projectIdentifier = Identifier.byId("ede0540276a94b75ae3de044cd7cc235");
		return OSFactory.builderV3()
				.endpoint("http://10.31.1.15:5000/v3")
				.credentials("admin", "5fd399078a8844de", domainIdentifier)
				.scopeToProject(projectIdentifier)
				.authenticate();
	}

	private static OSClientV3 getQueensConnection() {
		final Identifier domainIdentifier = Identifier.byName("Default");
		return OSFactory.builderV3()
				.endpoint("http://10.31.1.240:5000/v3")
				.credentials("admin", "13f83cb78a4f4213", domainIdentifier)
				.scopeToProject(Identifier.byId("d45cdd393cda4255915f855527f9c98e"))
				.authenticate();
	}

	private static OSClientV3 getVictoriaConnection() {
		final Identifier domainIdentifier = Identifier.byName("Default");
		return OSFactory.builderV3()
				.endpoint("http://os-victoria:5000/v3")
				.credentials("admin", "cf83a3d4263b4be0", domainIdentifier)
				.scopeToProject(Identifier.byId("29672f9f0e444a4bb0f8691195939d37"))
				.authenticate();
	}

	void testTrain() throws Exception {
		final OSClientV3 os = getVictoriaConnection();
		final List<? extends Service> ep = os.identity().serviceEndpoints().list();
		final Optional<? extends Service> l = ep.stream().filter(x -> x.getType().equals("placement")).findFirst();
		System.out.println("l=" + l.get());
		os.networking().agent().list().forEach(x -> {
			System.out.println("" + x.getBinary());
		});
		assertNotNull(os);
	}

	void testQueens() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final List<? extends Service> ep = os.identity().serviceEndpoints().list();
		final Optional<? extends Service> l = ep.stream().filter(x -> x.getType().equals("placement")).findFirst();
		System.out.println("l=" + l.get());
		os.networking().agent().list().forEach(x -> {
			System.out.println("" + x.getBinary());
		});
		assertNotNull(os);
	}

	static void testDeleteNetwork() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final List<? extends Port> ret = os.networking().port().list();
		ret.forEach(System.out::println);
		System.out.println("" + ret);
		assertNotNull(os);
	}

	static void testDeleteRouter() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final String device = "9f07f44f-3f06-48c8-ad75-5c7afcc46d2e";
		final Router router = os.networking().router().get(device);
		final List<? extends Port> routerList = os.networking().port().list();
		routerList.stream()
				.filter(x -> x.getDeviceId().equals(device))
				.forEach(System.out::println);
		final List<RouterInterface> ret = routerList.stream()
				.filter(x -> x.getDeviceId().equals(device))
				.filter(x -> !x.getDeviceOwner().equals("network:router_gateway"))
				.map(Port::getId)
				.map(x -> os.networking().router().detachInterface(device, null, x))
				.collect(Collectors.toList());
		ret.forEach(x -> System.out.println("" + x));
		System.out.println("" + router);
		final ActionResponse ret2 = os.networking().router().delete(device);
		System.out.println("" + ret2);
		assertNotNull(os);
	}

	static void testGetPublic() throws Exception {
		final OSClientV3 os = getQueensConnection();
		os.networking().network().list().forEach(x -> {
			System.out.println(x.getName() + " -- " + x.isRouterExternal() + " ==> " + x.getId());
		});
		assertNotNull(os);
	}

	static void testAgentList() throws Exception {
		final OSClientV3 os = getQueensConnection();
		os.networking().agent().list().forEach(x -> {
			System.out.println("" + x.getTopic() + " " + x.getAgentType() + " " + x.getBinary());
		});
		assertNotNull(os);
	}

	static void testDns01() throws Exception {
		final OSClientV3 os = getTrainConnection();
		os.dns().recordsets().list().forEach(x -> {
			System.out.println("" + x);
		});
		assertNotNull(os);
	}

	public static void testflavor() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final int disk = 20;
		final int ram = 4096;
		final int numVcpu = 2;
		final Flavor build = Builders.flavor()
				.disk(disk)
				.ram(ram)
				.vcpus(numVcpu)
				.isPublic(true)
				.name("junittest")
				.build();
		final Flavor res = os.compute()
				.flavors()
				.create(build);
		System.out.println("" + res);
		assertNotNull(os);
	}

	public static void testCreateZone() throws Exception {
		final OSClientV3 os = getTrainConnection();
		final Zone zone = Builders.zone().name("aed0bd5a-acf8-463f-9797-9af1af1bab23.mano.com.")
				.ttl(36)
				.type(ZoneType.PRIMARY)
				.email("mano@ubqube.com")
				.build();
		final Zone res = os.dns().zones().create(zone);
		System.out.println("" + res);
		assertNotNull(os);
	}

	static void testAddHost() throws Exception {
		final OSClientV3 os = getTrainConnection();
		final List<String> records = new ArrayList<>();
		records.add("192.168.10.6");
		records.add("192.168.10.5");
		final Recordset recordSet = Builders
				.recordset()
				.name("test.aed0bd5a-acf8-463f-9797-9af1af1bab23.mano.com.")
				.type("A")
				.records(records)
				.build();
		final Recordset res = os.dns().recordsets().create("32b90f02-765a-46c8-80a1-79a1a513d59f", recordSet);
		System.out.println("" + res);
		assertNotNull(os);
	}

	static void testgetIp() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final List<? extends Subnet> l = os.networking().subnet().list();
		l.forEach(System.out::println);
		final List<? extends Port> l2 = os.networking().port().list();
		l2.forEach(System.out::println);
		assertNotNull(os);
	}

	public static void testQuotas() {
		final OSClientV3 os = getQueensConnection();
		os.compute().quotaSets().listTenantUsages().stream()
				.forEach(x -> {
					System.out.println("" + x.getTenantId());
					final QuotaSet quota = os.compute().quotaSets().get(x.getTenantId());
					final AbsoluteLimit usage = os.compute().quotaSets().limits().getAbsolute();
					System.out.println("" + quota);
					System.out.println("" + usage);
				});
		System.out.println("==========================");
		os.networking().quotas().get().stream()
				.forEach(x -> {
					x.getNetwork();
					System.out.println("netw " + x);
				});
		os.identity().policies().list().stream()
				.forEach(x -> {
					System.out.println("" + x);
				});
		assertNotNull(os);
	}

	@Test
	void testNetworkExtension() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final List<? extends Extension> exts = os.networking().network().listExtensions();
		exts.forEach(System.out::println);
		assertTrue(!exts.isEmpty());
	}

	@Test
	void testTelemetry() throws Exception {
		final OSClientV3 os = getQueensConnection();
		os.telemetry().alarms().list();
		os.telemetry().meters().list();
		os.telemetry().resources().list();
		os.telemetry().samples().list();
		assertNotNull(os);
	}

	@Test
	void testGnochhiCreate() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final MetricCreate metrics = Builders.gnocchi().metric().archivePolicyName("high").name("test-ovi").resourceId(null).build();
		os.telemetry().gnocchi().metrics().create(metrics);
		assertNotNull(os);
	}

	@Test
	void testGnocchiDelete() throws Exception {
		final OSClientV3 os = getQueensConnection();
		os.telemetry().gnocchi().metrics().delete("7856e791-c918-4f72-814c-b3f18127190b");
		assertNotNull(os);
	}

	@Test
	void testServerGroup() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final org.openstack4j.model.compute.ServerGroup res = os.compute().serverGroups().create(UUID.randomUUID().toString(), "affinity");
		assertNotNull(os);
	}

	@Test
	void testSecurityGroup() throws Exception {
		final SecurityGroup sg = new SecurityGroup();
		sg.setDirection("ingress");
		sg.setEtherType("ipv4");
		sg.setPortRangeMin(22);
		sg.setPortRangeMax(24);
		sg.setProtocol("tcp");
		sg.setToscaName("security");
		final OSClientV3 os = getQueensConnection();
		final SecurityGroupRule group = Builders.securityGroupRule()
				.direction(sg.getDirection())
				.ethertype(sg.getEtherType())
				.portRangeMin(sg.getPortRangeMin())
				.portRangeMax(sg.getPortRangeMax())
				.protocol(sg.getProtocol())
				.securityGroupId(sg.getToscaName())
				.build();
		// final SecurityGroupRule res = os.networking().securityrule().create(group);
		final org.openstack4j.model.network.SecurityGroup securityGroup = Builders.securityGroup()
				.name("security")
				.build();
		final org.openstack4j.model.network.SecurityGroup res = os.networking().securitygroup().create(securityGroup);
		System.out.println("" + res.getId());
		assertNotNull(os);
	}
}
