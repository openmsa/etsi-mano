package com.ubiqube.etsi.mano.repository;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public interface CrudRepository<T> {

	@Nonnull
	T get(@Nonnull String id);

	void delete(@Nonnull String id);

	@Nonnull
	T save(@Nonnull T entity);

	@Nonnull
	@NotNull
	List<T> query(@Nullable String filter);
}
