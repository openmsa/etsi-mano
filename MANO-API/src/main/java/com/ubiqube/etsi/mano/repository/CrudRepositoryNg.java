package com.ubiqube.etsi.mano.repository;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public interface CrudRepositoryNg<T> {
	@Nonnull
	T get(@Nonnull UUID id);

	void delete(@Nonnull UUID id);

	@Nonnull
	T save(@Nonnull T entity);

	@Nonnull
	@NotNull
	List<T> query(@Nullable String filter);

}
