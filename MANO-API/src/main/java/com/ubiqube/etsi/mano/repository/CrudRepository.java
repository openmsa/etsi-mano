package com.ubiqube.etsi.mano.repository;

import javax.annotation.Nonnull;

public interface CrudRepository<T> {

	@Nonnull
	T get(@Nonnull String id);

	void delete(@Nonnull String id);

	@Nonnull
	T save(@Nonnull T entity);
}
