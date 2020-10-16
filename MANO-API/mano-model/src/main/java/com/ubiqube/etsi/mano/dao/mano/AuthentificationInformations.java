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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;

import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Embeddable
public class AuthentificationInformations {
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private AuthType authType;

	private AuthParamBasic authParamBasic;
	private AuthParamOath2 authParamOath2;

	public AuthType getAuthType() {
		return authType;
	}

	public void setAuthType(final AuthType authType) {
		this.authType = authType;
	}

	public AuthParamBasic getAuthParamBasic() {
		return authParamBasic;
	}

	public void setAuthParamBasic(final AuthParamBasic authParamBasic) {
		this.authParamBasic = authParamBasic;
	}

	public AuthParamOath2 getAuthParamOath2() {
		return authParamOath2;
	}

	public void setAuthParamOath2(final AuthParamOath2 authParamOath2) {
		this.authParamOath2 = authParamOath2;
	}

}
