package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;

import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionAuthentication.AuthTypeEnum;
import com.ubiqube.etsi.mano.repository.jpa.EnumFieldBridge;

@Embeddable
public class AuthentificationInformations {
	@Enumerated(EnumType.STRING)
	@FieldBridge(impl = EnumFieldBridge.class)
	@Field
	private AuthTypeEnum authType;

	private AuthParamBasic authParamBasic;
	private AuthParamOath2 authParamOath2;

	public AuthTypeEnum getAuthType() {
		return authType;
	}

	public void setAuthType(final AuthTypeEnum authType) {
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
