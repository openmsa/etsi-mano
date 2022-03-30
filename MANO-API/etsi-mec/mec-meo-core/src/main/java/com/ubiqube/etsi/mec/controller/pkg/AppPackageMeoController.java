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
package com.ubiqube.etsi.mec.controller.pkg;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.ubiqube.etsi.mano.dao.mano.OperationalStateType;
import com.ubiqube.etsi.mano.dao.mec.pkg.AppPkg;

public interface AppPackageMeoController {

	AppPkg findById(@Nonnull UUID fromString);

	AppPkg save(@Nonnull AppPkg appPkg);

	AppPkg getAppd(@Nonnull UUID appPkgId);

	AppPkg setOperationState(@Nonnull UUID fromString, @NotNull OperationalStateType operationState);

	List<AppPkg> query(String filter);

	ResponseEntity<Resource> getPackage(@Nonnull UUID fromString);

	void deleteById(@Nonnull UUID fromString);

	void store(UUID fromString, byte[] bytes);

}
