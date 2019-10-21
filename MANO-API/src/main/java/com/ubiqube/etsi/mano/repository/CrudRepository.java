package com.ubiqube.etsi.mano.repository;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface CrudRepository<T> {

	@Nonnull
	T get(@Nonnull String id);

	void delete(@Nonnull String id);

	@Nonnull
	T save(@Nonnull T entity);

	@Nonnull
	List<T> query(@Nullable String filter);
}
