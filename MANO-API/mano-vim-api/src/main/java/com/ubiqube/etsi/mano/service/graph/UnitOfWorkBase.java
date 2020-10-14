package com.ubiqube.etsi.mano.service.graph;

public interface UnitOfWorkBase<U> {

	String getName();

	U getType();

	String getToscaName();
}
