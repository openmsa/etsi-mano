package com.ubiqube.etsi.mano.dao.mano;

import java.util.UUID;

import javax.annotation.Nonnull;

public interface BaseEntity {
	@Nonnull
	UUID getId();
}
