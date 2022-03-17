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

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.nsd.Classifier;
import com.ubiqube.etsi.mano.orchestrator.entities.SystemConnections;

import net.juniper.contrail.api.types.ActionListType;
import net.juniper.contrail.api.types.AddressType;
import net.juniper.contrail.api.types.NetworkPolicy;
import net.juniper.contrail.api.types.PolicyEntriesType;
import net.juniper.contrail.api.types.PolicyRuleType;
import net.juniper.contrail.api.types.PortTuple;
import net.juniper.contrail.api.types.Project;
import net.juniper.contrail.api.types.ServiceInstance;
import net.juniper.contrail.api.types.ServiceInstanceInterfaceType;
import net.juniper.contrail.api.types.ServiceInstanceType;
import net.juniper.contrail.api.types.ServiceTemplate;
import net.juniper.contrail.api.types.ServiceTemplateInterfaceType;
import net.juniper.contrail.api.types.ServiceTemplateType;
import net.juniper.contrail.api.types.VirtualMachineInterface;
import net.juniper.contrail.api.types.VirtualMachineInterfacePropertiesType;
import net.juniper.contrail.api.types.VirtualNetwork;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ContrailApi {

	private final ContrailFacade facade;

	public ContrailApi() {
		this.facade = new ContrailFacade();
	}

	public String createServiceTemplate(final SystemConnections vimConnectionInformation, final String name) {
		final ServiceTemplate root = new ServiceTemplate();
		root.setDisplayName(name);
		root.setName(name);
		final ServiceTemplateType props = new ServiceTemplateType();
		props.setServiceVirtualizationType("virtual-machine"); // Any of [‘virtual-machine’, ‘network-namespace’, ‘vrouter-instance’,
																// ‘physical-device’]
		props.addInterfaceType(new ServiceTemplateInterfaceType("left"));
		props.addInterfaceType(new ServiceTemplateInterfaceType("right"));
		props.setServiceMode("in-network"); // Any of [‘transparent’, ‘in-network’, ‘in-network-nat’]
		props.setServiceType("firewall"); // Any of [‘firewall’, ‘analyzer’, ‘source-nat’, ‘loadbalancer’]
		props.setVersion(2);
		root.setProperties(props);
		return facade.create(vimConnectionInformation, root);
	}

	public String createServiceInstance(final SystemConnections vimConnectionInformation, final String name, final String serviceTemplateId, final String left, final String right) {
		final Project prj = new Project();
		prj.setName("admin");
		final ServiceInstance root = new ServiceInstance();
		root.setParent(prj);
		root.setName(name);
		final ServiceTemplate st = facade.findById(vimConnectionInformation, ServiceTemplate.class, serviceTemplateId);
		root.setServiceTemplate(st);
		final ServiceInstanceType serviceInstanceProperties = new ServiceInstanceType();
		final String l = facade.uuidToFq(vimConnectionInformation, VirtualNetwork.class, left);
		serviceInstanceProperties.addInterface(new ServiceInstanceInterfaceType(l));
		final String r = facade.uuidToFq(vimConnectionInformation, VirtualNetwork.class, right);
		serviceInstanceProperties.addInterface(new ServiceInstanceInterfaceType(r));
		root.setProperties(serviceInstanceProperties);
		return facade.create(vimConnectionInformation, root);
	}

	public String createPortTuple(final SystemConnections vimConnectionInformation, final String name, final String serviceInstanceId) {
		final PortTuple root = new PortTuple();
		root.setDisplayName(name);
		root.setName(name);
		final ServiceInstance parent = facade.findById(vimConnectionInformation, ServiceInstance.class, serviceInstanceId);
		root.setParent(parent);
		return facade.create(vimConnectionInformation, root);
	}

	public void updatePort(final SystemConnections vimConnectionInformation, final String uuid, final String portTupleId, final String mode) {
		final VirtualMachineInterface vmi = new VirtualMachineInterface();
		vmi.setUuid(uuid);
		final Project prj = new Project();
		prj.setName("admin");
		vmi.setParent(prj);
		final VirtualMachineInterfacePropertiesType virtualMachineInterfaceProperties = new VirtualMachineInterfacePropertiesType();
		virtualMachineInterfaceProperties.setServiceInterfaceType(mode);
		vmi.setProperties(virtualMachineInterfaceProperties);
		final PortTuple pt = facade.findById(vimConnectionInformation, PortTuple.class, portTupleId);
		vmi.setPortTuple(pt);
		facade.update(vimConnectionInformation, vmi);
	}

	public void deletePortTuple(final SystemConnections vimConnectionInformation, final String vimResourceId) {
		final PortTuple root = new PortTuple();
		root.setUuid(vimResourceId);
		facade.delete(vimConnectionInformation, root);
	}

	public void deleteServiceTemplate(final SystemConnections vimConnectionInformation, final String vimResourceId) {
		final ServiceTemplate root = new ServiceTemplate();
		root.setUuid(vimResourceId);
		facade.delete(vimConnectionInformation, root);
	}

	public void deleteServiceInstance(final SystemConnections vimConnectionInformation, final String vimResourceId) {
		final ServiceInstance root = new ServiceInstance();
		root.setUuid(vimResourceId);
		facade.delete(vimConnectionInformation, root);
	}

	public String createNetworkPolicy(final SystemConnections vimConnectionInformation, final String name, final Classifier classifier, final String serviceInstance, final String left, final String right) {
		final NetworkPolicy root = new NetworkPolicy();
		final Project prj = new Project();
		prj.setName("admin");
		root.setParent(prj);
		root.setName(name);
		final String leftFq = facade.uuidToFq(vimConnectionInformation, VirtualNetwork.class, left);
		final String rightFq = facade.uuidToFq(vimConnectionInformation, VirtualNetwork.class, right);
		final String serviceInstanceFq = facade.uuidToFq(vimConnectionInformation, ServiceInstance.class, serviceInstance);
		final PolicyRuleType rule = createPolicyRuleType(classifier, serviceInstanceFq, leftFq, rightFq);
		final List<PolicyRuleType> policyRule = List.of(rule);
		final PolicyEntriesType networkPolicyEntries = new PolicyEntriesType(policyRule);
		root.setEntries(networkPolicyEntries);
		return facade.create(vimConnectionInformation, root);
	}

	private static PolicyRuleType createPolicyRuleType(final Classifier classifier, final String serviceInstanceFq, final String leftFq, final String rightFq) {
		final PolicyRuleType rule = new PolicyRuleType();
		rule.setDirection("<>");
		rule.setEthertype(classifier.getEtherType());
		rule.setProtocol(classifier.getProtocol());
		final ActionListType actionList = new ActionListType("pass");
		actionList.addApplyService(serviceInstanceFq);
		rule.setActionList(actionList);
		classifier.setDscp(classifier.getDscp());
		rule.addDstAddresses(makeAddress(leftFq));
		rule.addDstPorts(Long.valueOf(classifier.getDestinationPortRangeMin()).intValue(), Long.valueOf(classifier.getDestinationPortRangeMax()).intValue());
		rule.addSrcAddresses(makeAddress(rightFq));
		rule.addSrcPorts(Long.valueOf(classifier.getSourcePortRangeMin()).intValue(), Long.valueOf(classifier.getSourcePortRangeMax()).intValue());
		rule.setEthertype("IPv4");
		return rule;
	}

	private static AddressType makeAddress(final String leftFq) {
		final AddressType at = new AddressType();
		at.setVirtualNetwork(leftFq);
		return at;
	}

	public void deleteNetworkPolicy(final SystemConnections vimConnectionInformation, final String vimResourceId) {
		final NetworkPolicy root = new NetworkPolicy();
		root.setUuid(vimResourceId);
		facade.delete(vimConnectionInformation, root);
	}
}
