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
package com.ubiqube.etsi.mano.service.rest;

import java.util.UUID;
import java.util.function.Function;

import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.service.HttpGateway;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ManoClient {
	private final MapperFacade mapper;
	private final ServerAdapter server;
	private Function<HttpGateway, ?> requestObject;
	private String setFragment;
	private ApiVersionType setQueryType;
	private UUID objectId;

	public ManoClient(final MapperFacade mapper, final ServerAdapter server) {
		this.mapper = mapper;
		this.server = server;
	}

	public ManoVnfInstance vnfInstance() {
		return new ManoVnfInstance(this);
	}

	public ManoVnfInstanceId vnfInstance(final UUID vnfInstanceId) {
		return new ManoVnfInstanceId(this, vnfInstanceId);
	}

	public ManoVnfLcmOpOccs vnfLcmOpOccs(final UUID id) {
		return new ManoVnfLcmOpOccs(this, id);
	}

	public ManoQueryBuilder createQuery() {
		return new ManoQueryBuilder(mapper, this);
	}

	public void setQueryType(final ApiVersionType sol003Vnflcm) {
		this.setQueryType = sol003Vnflcm;
	}

	public void setObjectId(final UUID vnfInstanceId) {
		this.objectId = vnfInstanceId;
	}

	public ManoQueryBuilder createQuery(final Function<HttpGateway, ?> func) {
		this.requestObject = func;
		return new ManoQueryBuilder(mapper, this);
	}

	public void setFragment(final String string) {
		this.setFragment = string;
	}

	public ServerAdapter getServer() {
		return server;
	}

	public <T> Function<HttpGateway, T> getRequestObject() {
		return (Function<HttpGateway, T>) requestObject;
	}

	public String getSetFragment() {
		return setFragment;
	}

	public UUID getObjectId() {
		return objectId;
	}

	public ApiVersionType getQueryType() {
		return setQueryType;
	}

	public ManoGrant grant() {
		return new ManoGrant(this);
	}

	public ManoGrant grant(final UUID id) {
		return new ManoGrant(this, id);
	}

	public ManoVnfPackageId vnfPackage(final UUID id) {
		return new ManoVnfPackageId(this, id);
	}

	public ManoVnfPackage vnfPackage() {
		return new ManoVnfPackage(this);
	}

	public MapperFacade getMapper() {
		return mapper;
	}

	public ManoVnfLcmOpOccs vnfLcmOpOccs() {
		return new ManoVnfLcmOpOccs(this);
	}

}
