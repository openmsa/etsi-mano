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
package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

@Embeddable
public class AuthParamOath2 {
	private String clientId = null;
	private String clientPassword = null;
	private String tokenEndpoint = null;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(final String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getTokenEndpoint() {
		return tokenEndpoint;
	}

	public void setTokenEndpoint(final String tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
	}

}
