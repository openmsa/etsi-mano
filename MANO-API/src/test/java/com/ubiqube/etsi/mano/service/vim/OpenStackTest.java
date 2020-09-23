package com.ubiqube.etsi.mano.service.vim;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jgrapht.ListenableGraph;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.jgrapht.nio.dot.DOTExporter;
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
import org.openstack4j.model.network.Subnet;
import org.openstack4j.openstack.OSFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;
import com.ubiqube.etsi.mano.dao.mano.IpPool;
import com.ubiqube.etsi.mano.dao.mano.L2Data;
import com.ubiqube.etsi.mano.dao.mano.L3Data;
import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VlProtocolData;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.graph.TestUnitOfWork;
import com.ubiqube.etsi.mano.jpa.VimConnectionInformationJpa;
import com.ubiqube.etsi.mano.service.graph.ConnectivityEdge;
import com.ubiqube.etsi.mano.service.graph.vnfm.EdgeListener;
import com.ubiqube.etsi.mano.service.graph.vnfm.UnitOfWork;
import com.ubiqube.etsi.mano.service.graph.vnfm.VirtualLinkUow;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Tag("Remote")
//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.Silent.class)
public class OpenStackTest {

	private static final Logger LOG = LoggerFactory.getLogger(OpenStackTest.class);

	@Mock
	private VimConnectionInformationJpa vimJpa;

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

	private static void createServer(final OSClientV3 os) {
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

	private void createStack() {
		// Builders.stack().
	}

	@Test
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

	@Test
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
		final OpenStackVim vim = new OpenStackVim(vimJpa, mapper);
		final String lid = vim.createStorage(vimConnectionInformation, vnfStorage, "junit-test");
		assertNotNull(lid);
	}

	@Test
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

		final OpenStackVim vim = new OpenStackVim(vimJpa, mapper);
		when(vimJpa.findById(id)).thenReturn(Optional.ofNullable(vimConnectionInformation));
		final String net = vim.createNetwork(vimConnectionInformation, vl, "Junit-vl", "mano.junit.net.", null);
		final IpPool ipPool = new IpPool();
		ipPool.setStartIpAddress("192.168.90.1");
		ipPool.setEndIpAddress("192.168.90.126");
		final String lid = vim.createSubnet(vimConnectionInformation, l3ProtocolData, ipPool, net);
		assertNotNull(lid);
	}

	@Test
	public void uploadSoftwareImageTest() {
		final OpenStackVim vim = new OpenStackVim(vimJpa, mapper);
		when(vimJpa.findById(id)).thenReturn(Optional.ofNullable(vimConnectionInformation));
		final SoftwareImage img = new SoftwareImage();
		img.setContainerFormat("BARE");
		img.setDiskFormat("QCOW2");
		img.setMinDisk(1000000000L);
		img.setMinRam(1000000L);
		img.setName("Junit-uploaded-image");
		img.setSize(1L);
		img.setImagePath("/home/olivier/Downloads/cirros-0.5.1-x86_64-disk.img");
		final SoftwareImage lid = vim.uploadSoftwareImage(vimConnectionInformation, img);
		assertNotNull(lid);
	}

	@Test
	void testFlavorGet() throws Exception {
		final OpenStackVim vim = new OpenStackVim(vimJpa, mapper);
		when(vimJpa.findById(id)).thenReturn(Optional.ofNullable(vimConnectionInformation));

		// Get m1.small
		final int numVcpu = 1;
		final long virtualMemorySize = 2 * 100000000L;
		final long disk = 20 * 1000000000L;
		final String lid = vim.getOrCreateFlavor(vimConnectionInformation, "Junit-flavor", numVcpu, virtualMemorySize, disk);
		assertNotNull(lid);
	}

	@Test
	void testRefinePlan() throws Exception {
		final OpenStackVim vim = new OpenStackVim(vimJpa, mapper);
		when(vimJpa.findById(id)).thenReturn(Optional.ofNullable(vimConnectionInformation));
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g = createNetwork();
		vim.refineExecutionPlan(g);

		final DOTExporter<UnitOfWork, ConnectivityEdge<UnitOfWork>> exporter = new DOTExporter<>(UnitOfWork::getName);
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		exporter.exportGraph(g, out);
		final byte[] res = out.toByteArray();
		Files.write(res, new File("plan.dot"));
		assertNotNull(out);
	}

	private static ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> createNetwork() {
		final ListenableGraph<UnitOfWork, ConnectivityEdge<UnitOfWork>> g = new DefaultListenableGraph(new DirectedAcyclicGraph(ConnectivityEdge.class));
		g.addGraphListener(new EdgeListener<UnitOfWork>());

		final VlProtocolData vlProtocolData = new VlProtocolData();
		final L3Data l3ProtocolData = new L3Data();
		final Set<IpPool> ipAllocationPools = new HashSet<>();
		final IpPool ipPool = new IpPool();
		ipAllocationPools.add(ipPool);
		ipPool.setStartIpAddress("192.168.20.1");
		vlProtocolData.setIpAllocationPools(ipAllocationPools);
		vlProtocolData.setL3ProtocolData(l3ProtocolData);
		final UnitOfWork vduA = new TestUnitOfWork("A");
		final UnitOfWork vduB = new VirtualLinkUow(null, vlProtocolData, "B", "");
		final UnitOfWork vduC = new TestUnitOfWork("C");

		final VlProtocolData vlProtocolData2 = new VlProtocolData();
		final L3Data l3ProtocolData2 = new L3Data();
		final Set<IpPool> ipAllocationPools2 = new HashSet<>();
		final IpPool ipPool2 = new IpPool();
		ipPool2.setStartIpAddress("192.168.10.1");
		final IpPool ipPool3 = new IpPool();
		ipPool3.setStartIpAddress("192.168.30.1");
		ipAllocationPools2.add(ipPool2);
		ipAllocationPools2.add(ipPool3);
		vlProtocolData2.setIpAllocationPools(ipAllocationPools2);
		vlProtocolData2.setL3ProtocolData(l3ProtocolData2);
		final UnitOfWork vduD = new VirtualLinkUow(null, vlProtocolData2, "D", "");
		final UnitOfWork vduE = new TestUnitOfWork("E");
		final UnitOfWork vduF = new TestUnitOfWork("F");
		g.addVertex(vduA);
		g.addVertex(vduB);
		g.addVertex(vduC);
		g.addVertex(vduD);
		g.addVertex(vduE);
		g.addVertex(vduF);

		g.addEdge(vduA, vduB);
		g.addEdge(vduA, vduC);

		g.addEdge(vduB, vduD);
		g.addEdge(vduC, vduD);

		g.addEdge(vduE, vduC);
		g.addEdge(vduE, vduF);
		final UnitOfWork root = new TestUnitOfWork("Start");
		g.addVertex(root);
		g.vertexSet().stream()
				.filter(key -> g.incomingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != root) {
						g.addEdge(root, key);
					}
				});
		// And end Node
		final UnitOfWork end = new TestUnitOfWork("End");
		g.addVertex(end);
		g.vertexSet().stream()
				.filter(key -> g.outgoingEdgesOf(key).isEmpty())
				.forEach(key -> {
					if (key != end) {
						g.addEdge(key, end);
					}
				});
		return g;
	}

	@Test
	void testCompute() throws Exception {
		final OpenStackVim vim = new OpenStackVim(vimJpa, mapper);
		when(vimJpa.findById(id)).thenReturn(Optional.ofNullable(vimConnectionInformation));

		final VnfCompute vnfc = new VnfCompute();
		final SoftwareImage softwareImage = new SoftwareImage();
		softwareImage.setName("cirros");
		vnfc.setSoftwareImage(softwareImage);
		vnfc.setName("vdu01");
		final List<String> networks = new ArrayList<>();
		final List<String> storages = new ArrayList<>();
		final String lid = vim.createCompute(vimConnectionInformation, "junit-name", "12745412-08b4-489c-95b0-eb2fd4a98b36", "e5429d68-3f1a-43e6-b46b-f83700d771da", networks, storages, null);
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
				.credentials("admin", "84612d9a2e404ac9", domainIdentifier)
				.scopeToProject(Identifier.byId("df1f081bf2d345099e6bb53f6b9407ff"))
				.authenticate();
	}

	@Test
	static void testTrain() throws Exception {
		final OSClientV3 os = getTrainConnection();
		final List<? extends Service> ep = os.identity().serviceEndpoints().list();
		final Optional<? extends Service> l = ep.stream().filter(x -> x.getType().equals("placement")).findFirst();
		System.out.println("l=" + l.get());
		os.networking().agent().list().forEach(x -> {
			System.out.println("" + x.getBinary());
		});
		assertNotNull(os);
	}

	@Test
	static void testQueens() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final List<? extends Service> ep = os.identity().serviceEndpoints().list();
		final Optional<? extends Service> l = ep.stream().filter(x -> x.getType().equals("placement")).findFirst();
		System.out.println("l=" + l.get());
		os.networking().agent().list().forEach(x -> {
			System.out.println("" + x.getBinary());
		});
		assertNotNull(os);
	}

	@Test
	static void testDeleteNetwork() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final List<? extends Port> ret = os.networking().port().list();
		ret.forEach(System.out::println);
		System.out.println("" + ret);
		assertNotNull(os);
	}

	@Test
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

	@Test
	static void testGetPublic() throws Exception {
		final OSClientV3 os = getQueensConnection();
		os.networking().network().list().forEach(x -> {
			System.out.println(x.getName() + " -- " + x.isRouterExternal() + " ==> " + x.getId());
		});
		assertNotNull(os);
	}

	@Test
	static void testAgentList() throws Exception {
		final OSClientV3 os = getQueensConnection();
		os.networking().agent().list().forEach(x -> {
			System.out.println("" + x.getTopic() + " " + x.getAgentType() + " " + x.getBinary());
		});
		assertNotNull(os);
	}

	@Test
	static void testDns01() throws Exception {
		final OSClientV3 os = getTrainConnection();
		os.dns().recordsets().list().forEach(x -> {
			System.out.println("" + x);
		});
		assertNotNull(os);
	}

	@Test
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

	@Test
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

	@Test
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

	@Test
	static void testgetIp() throws Exception {
		final OSClientV3 os = getQueensConnection();
		final List<? extends Subnet> l = os.networking().subnet().list();
		l.forEach(System.out::println);
		final List<? extends Port> l2 = os.networking().port().list();
		l2.forEach(System.out::println);
		assertNotNull(os);
	}

	@Test
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
	    List<? extends Extension> exts = os.networking().network().listExtensions();
	    exts.forEach(System.out::println);
	}
}
