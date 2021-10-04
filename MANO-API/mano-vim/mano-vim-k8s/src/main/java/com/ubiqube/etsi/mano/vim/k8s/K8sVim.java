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
package com.ubiqube.etsi.mano.vim.k8s;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.ubiqube.etsi.mano.dao.mano.GrantInformationExt;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.sys.ServerGroup;
import com.ubiqube.etsi.mano.service.vim.Dns;
import com.ubiqube.etsi.mano.service.vim.Network;
import com.ubiqube.etsi.mano.service.vim.PhysResources;
import com.ubiqube.etsi.mano.service.vim.ResourceQuota;
import com.ubiqube.etsi.mano.service.vim.Storage;
import com.ubiqube.etsi.mano.service.vim.Vim;
import com.ubiqube.etsi.mano.service.vim.VimCapability;
import com.ubiqube.etsi.mano.service.vim.mon.VimMonitoring;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.util.Config;

public class K8sVim implements Vim {
	private static final String TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6InlrcmdtSkdKNzNPVHEwX0E3NlhxNzRad0NhX2dEWG55YjY4OEhvQmsxaUkifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi11c2VyLXRva2VuLWdzOHBrIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImFkbWluLXVzZXIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiI1NTg1ZDMwMi0yMzA0LTRkOGYtOTdlMS1hZGYxYWM4N2VlZGEiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZXJuZXRlcy1kYXNoYm9hcmQ6YWRtaW4tdXNlciJ9.X7vEXEYX2WeUcYg5zFB7g8van_SJaSZ5Y_4FmjhQb0f0x0MhDp6MuJM25h2bMCr9vDeR4jQiLbs6dJl6f2T5q2dTtvfiU44x8mf7ETSdJnq8FJMa65ErQULdul1mTPAXJs5lHwTSGSpdNOk52odnpk_d2I2tZsCIjDFU099MWd8Za5lXBwJ3PqD6XgOvcLBYAuj0ULYiHupTKXtuJxoiBBfUVcdleL4zg8UIHhnVmFBbiEbKUbP9UqSHkuut7W3bZMlgFMsMujNytgbJEJJysIVrmY3R5-NO2u7A39UasCheovOJIdgSPPV7jMiHMGNUUryiAo5f8P8zz_hTZHMtxQ";

	private CoreV1Api doConnect(final VimConnectionInformation vimConnectionInformation) throws IOException, ApiException {
		final ApiClient client = Config.fromUrl("").setBasePath("");
		Configuration.setDefaultApiClient(client);
		final CoreV1Api v1 = new CoreV1Api(client);
		final V1Pod body = new V1Pod();
		final String namespace = null;
		final String pretty = null;
		final String dryRun = null;
		final String fieldManager = null;
		v1.createNamespacedPod(namespace, body, pretty, dryRun, fieldManager);
		return null;
	}

	@Override
	public String getType() {
		return "K8S";
	}

	@Override
	public @NotNull Network network(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Storage storage(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull Dns dns(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @NotNull VimMonitoring getMonitoring(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrCreateFlavor(final VimConnectionInformation vimConnectionInformation, final String name, final int numVcpu, final long virtualMemorySize, final long disk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createCompute(final VimConnectionInformation vimConnectionInformation, final String instanceName, final String flavorId, final String imageId, final List<String> networks, final List<String> storages, final String cloudInitData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCompute(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startServer(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopServer(final VimConnectionInformation vimConnectionInformation, final String resourceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ServerGroup> getServerGroup(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getZoneAvailableList(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourceQuota getQuota(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VimCapability> getCaps(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void allocateResources(final VimConnectionInformation vimConnectionInformation, final GrantInformationExt x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void freeResources(final VimConnectionInformation vimConnectionInformation, final GrantInformationExt x) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhysResources getPhysicalResources(final VimConnectionInformation vimConnectionInformation) {
		// TODO Auto-generated method stub
		return null;
	}

}
