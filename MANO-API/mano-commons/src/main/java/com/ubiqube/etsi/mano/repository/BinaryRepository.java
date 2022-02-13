/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.repository;

import java.io.InputStream;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public interface BinaryRepository {

	void storeObject(@NotNull UUID id, @NotNull String filename, Object object);

	void storeBinary(@NotNull UUID id, @NotNull String filename, InputStream stream);

	ManoResource getBinary(@NotNull UUID id, @NotNull String filename);

	ManoResource getBinary(@NotNull UUID id, @NotNull String filename, int min, Long max);

	void delete(@NotNull UUID id, @NotNull String filename);

	void delete(@NotNull UUID id);
}
