package com.ubiqube.etsi.mano.service;

public interface Configuration {

	<T> T get(String key);
}
