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
package com.ubiqube.etsi.mano.vim.aws;

import com.amazonaws.services.eks.AmazonEKS;
import com.amazonaws.services.eks.model.CreateClusterRequest;
import com.amazonaws.services.eks.model.KubernetesNetworkConfigRequest;
import com.amazonaws.services.eks.model.VpcConfigRequest;

public class AwsCnf {
	private AmazonEKS eks;

	public String createK8sTemplate() {
		final KubernetesNetworkConfigRequest kubernetesNetworkConfig;
		final VpcConfigRequest resourcesVpcConfig;
		final CreateClusterRequest createClusterRequest = new CreateClusterRequest()
				.withKubernetesNetworkConfig(kubernetesNetworkConfig)
				.withName("")
				.withResourcesVpcConfig(resourcesVpcConfig);
		eks.createCluster(createClusterRequest);
		return null;
	}

	public String startK8s() {
		eks.
		return null;
	}
}
