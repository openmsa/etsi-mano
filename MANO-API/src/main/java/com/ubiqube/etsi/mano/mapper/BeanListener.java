package com.ubiqube.etsi.mano.mapper;

public interface BeanListener {

	void addProperty(Object source);

	void startList(String name);

	void endList();

	void listElementStart(int i);

	void complexStart(String name);

	void complexEnd();

	void listElementEnd();

}
