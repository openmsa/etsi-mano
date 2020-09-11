package com.ubiqube.etsi.mano.dao.mano;

public interface ToscaEntity extends BaseEntity {
	String getToscaId();

	void setToscaId(final String toscaId);

	String getToscaName();

	void setToscaName(final String toscaName);

	String getState();

	void setState(final String state);
}
