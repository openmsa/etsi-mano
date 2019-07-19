package com.ubiqube.etsi.mano.repository;

public interface CrudRepository<T> {

	 T get(String id);

	void delete(String id);

	T save(T entity);
}
