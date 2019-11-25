package com.ubiqube.etsi.mano.dao.mano;

import javax.persistence.Embeddable;

import com.ubiqube.etsi.mano.model.vnf.sol005.SubscriptionAuthentication.AuthTypeEnum;

// Todo Change AuthTypeEnum
@Embeddable
public class AuthentificationInformations {
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
