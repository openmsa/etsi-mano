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

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.magnum.Cluster;
import org.openstack4j.model.magnum.Clustertemplate;
import org.openstack4j.openstack.magnum.MagnumCluster;
import org.openstack4j.openstack.magnum.MagnumClustertemplate;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class OsCnf implements Cnf {

	private final OSClientV3 os;

	public OsCnf(final OSClientV3 os) {
		this.os = os;
	}

	@Override
	public String createK8sTemplate(final CnfK8sParams params) {
		final Clustertemplate template = MagnumClustertemplate.builder()
				.clusterDistro(params.getClusterDistro())
				.coe("kubernetes")
				.dnsNameserver(params.getDnsServer())
				.dockerStorageDriver("")
				.dockerVolumeSize(params.getVolumeSize())
				.externalNetworkId(params.getExternalNetworkId())
				.flavorId(params.getFlavorId())
				.imageId(params.getImageId())
				.keypairId(params.getKeypair())
				.masterFlavorId(params.getMasterFlavor())
				.name(params.getName())
				.networkDriver("flannel")
				.serverType("vm")
				.build();
		final Clustertemplate obj = os.magnum().createClustertemplate(template);
		return obj.getUuid();
	}

	@Override
	public String startK8s(final String clusterTemplateId, final String keypair, final Integer masterCount, final String name, final Integer nodeCount) {
		final Cluster cluster = MagnumCluster.builder()
				.clusterTemplateId(clusterTemplateId)
				.keypair(keypair)
				.masterCount(masterCount)
				.name(name)
				.nodeCount(nodeCount)
				.build();
		final Cluster res = os.magnum().createCluster(cluster);
		return res.getUuid();
	}
}
