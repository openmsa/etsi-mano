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
package com.ubiqube.etsi.mano.service.pkg.vnf;

import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import com.ubiqube.etsi.mano.dao.mano.AuthParamBasic;
import com.ubiqube.etsi.mano.dao.mano.AuthParamOauth2;
import com.ubiqube.etsi.mano.dao.mano.AuthType;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;
import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations.AuthentificationInformationsBuilder;
import com.ubiqube.etsi.mano.dao.mano.OAuth2GrantType;
import com.ubiqube.etsi.mano.dao.mano.config.Servers;
import com.ubiqube.etsi.mano.dao.mano.pkg.ParamsOauth2ClientCredentials;
import com.ubiqube.etsi.mano.dao.mano.pkg.UploadUriParameters;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.repository.HttpRequestor;
import com.ubiqube.etsi.mano.service.rest.FluxRest;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class FluxRequestor implements HttpRequestor, Closeable {
	private final FluxRest fr;

	private Path tmpPath;

	private FileInputStream fis;

	private final UploadUriParameters params;

	public FluxRequestor(final UploadUriParameters params) {
		this.params = params;
		AuthentificationInformations authentification = null;
		if (params.getAuthType() == AuthType.OAUTH2_CLIENT_CREDENTIALS) {
			final AuthentificationInformationsBuilder authentificationBuilder = AuthentificationInformations.builder();
			final ParamsOauth2ClientCredentials oAuth2 = params.getParamsOauth2ClientCredentials();
			final AuthParamOauth2 authParamOath2 = AuthParamOauth2.builder()
					.clientId(oAuth2.getClientId())
					.clientSecret(oAuth2.getClientPassword())
					.grantType(OAuth2GrantType.CLIENT_CREDENTIAL)
					.tokenEndpoint(oAuth2.getTokenEndpoint())
					.build();
			authentification = authentificationBuilder.authParamOath2(authParamOath2).build();
		}
		if (params.getAuthType() == AuthType.BASIC) {
			final AuthentificationInformationsBuilder authentificationBuilder = AuthentificationInformations.builder();
			final AuthParamBasic authParamBasic = AuthParamBasic.builder().userName(params.getUsername()).password(params.getPassword()).build();
			authentification = authentificationBuilder.authParamBasic(authParamBasic).build();
		}
		final Servers server = Servers.builder().authentification(authentification).build();
		this.fr = new FluxRest(server);
	}

	@Override
	public InputStream getInputStream() {
		try {
			tmpPath = Files.createTempFile("dwn", "mano");
			fr.download(URI.create(params.getAddressInformation()), tmpPath, null);
			fis = new FileInputStream(tmpPath.toFile());
			return fis;
		} catch (final IOException e) {
			throw new GenericException(e);
		}

	}

	@Override
	public void close() throws IOException {
		if (fis != null) {
			fis.close();
		}
		if (tmpPath != null) {
			Files.delete(tmpPath);
		}
	}

}
