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

import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import com.ubiqube.etsi.mano.service.HttpGateway;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ManoQueryBuilder {
	private final MapperFacade mapper;

	private final ManoClient client;
	private Function<HttpGateway, ParameterizedTypeReference<List<Class<?>>>> inClassList;
	private Function<HttpGateway, Class<?>> wireInClass;
	private Class<?> outClass;
	private Function<HttpGateway, Class<?>> wireOutClass;

	public ManoQueryBuilder(final MapperFacade mapper, final ManoClient manoClient) {
		this.mapper = mapper;
		this.client = manoClient;
	}

	public ManoQueryBuilder setInClassList(final Function<HttpGateway, ParameterizedTypeReference<List<Class<?>>>> func) {
		this.inClassList = func;
		return this;
	}

	public ManoQueryBuilder setWireInClass(final Function<HttpGateway, Class<?>> func) {
		this.wireInClass = func;
		return this;
	}

	public ManoQueryBuilder setOutClass(final Class<?> class1) {
		this.outClass = class1;
		return this;
	}

	public ManoQueryBuilder setWireOutClass(final Function<HttpGateway, Class<?>> func) {
		this.wireOutClass = func;
		return this;
	}

	public void delete() {
		final ServerAdapter server = client.getServer();
		final HttpGateway httpGateway = server.httpGateway();
		final URI uri = buildUri(server);
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		server.rest().delete(uri, Object.class, version);
	}

	public <T> ResponseEntity<T> getRaw() {
		final ServerAdapter server = client.getServer();
		final HttpGateway httpGateway = server.httpGateway();
		final URI uri = buildUri(server);
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		return (ResponseEntity<T>) server.rest().getWithReturn(uri, this.wireOutClass.apply(httpGateway), version);
	}

	public <T> T getSingle() {
		final ServerAdapter server = client.getServer();
		final HttpGateway httpGateway = server.httpGateway();
		final URI uri = buildUri(server);
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		final ResponseEntity<?> resp = server.rest().getWithReturn(uri, this.wireOutClass.apply(httpGateway), version);
		return (T) mapper.map(resp.getBody(), this.outClass);
	}

	public <T> List<T> getList() {
		final ServerAdapter server = client.getServer();
		final URI uri = buildUri(server);
		final HttpGateway httpGateway = server.httpGateway();
		final ParameterizedTypeReference<List<Class<?>>> clazz = this.inClassList.apply(httpGateway);
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		final List<?> resp = server.rest().get(uri, clazz, version);
		return (List<T>) mapper.mapAsList(resp, this.outClass);
	}

	public <T> T post(final Object req) {
		final ServerAdapter server = client.getServer();
		final URI uri = buildUri(server);
		final HttpGateway httpGateway = server.httpGateway();
		final Object reqMap = remapRequest(req);
		final Class<?> clazz = wireOutClass.apply(httpGateway);
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		final var res = server.rest().post(uri, reqMap, clazz, version);
		return (T) mapper.map(res, this.outClass);
	}

	public <T> ResponseEntity<T> postRaw(final Object req) {
		final ServerAdapter server = client.getServer();
		final HttpGateway httpGateway = server.httpGateway();
		final URI uri = buildUri(server);
		final Object reqMap = remapRequest(req);
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		return (ResponseEntity<T>) server.rest().postWithReturn(uri, reqMap, this.wireOutClass.apply(httpGateway), version);
	}

	public <T> ResponseEntity<T> postRaw() {
		final ServerAdapter server = client.getServer();
		final HttpGateway httpGateway = server.httpGateway();
		final URI uri = buildUri(server);
		final Object reqMap = client.getRequestObject().apply(httpGateway);
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		return (ResponseEntity<T>) server.rest().postWithReturn(uri, reqMap, this.wireOutClass.apply(httpGateway), version);
	}

	private Object remapRequest(final Object req) {
		final HttpGateway httpGateway = client.getServer().httpGateway();
		final Object obj = wireInClass.apply(httpGateway);
		if (obj instanceof final Class<?> clz) {
			return mapper.map(req, clz);
		}
		return obj;
	}

	public <T> T post() {
		final ServerAdapter server = client.getServer();
		final URI uri = buildUri(server);
		final HttpGateway httpGateway = server.httpGateway();
		final Object reqMap = client.getRequestObject().apply(httpGateway);
		final Class<?> clazz = wireOutClass.apply(httpGateway);
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		final var res = server.rest().post(uri, reqMap, clazz, version);
		return (T) mapper.map(res, this.outClass);
	}

	private URI buildUri(final ServerAdapter server) {
		final Map<String, Object> uriParams = Optional.ofNullable(client.getObjectId()).map(x -> Map.of("id", (Object) x.toString())).orElseGet(Map::of);
		return server.getUriFor(client.getQueryType(), client.getSetFragment(), uriParams);
	}

	public void download(final Path file) {
		final ServerAdapter server = client.getServer();
		final URI uri = buildUri(server);
		final HttpGateway httpGateway = server.httpGateway();
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		server.rest().download(uri, file, version);
	}

	public void upload(final Path path, final String accept) {
		final ServerAdapter server = client.getServer();
		final URI uri = buildUri(server);
		final HttpGateway httpGateway = server.httpGateway();
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		server.rest().upload(uri, path, accept, version);
	}

	public <T> T patch(final String ifMatch, final Map<String, Object> patch) {
		final ServerAdapter server = client.getServer();
		final URI uri = buildUri(server);
		final HttpGateway httpGateway = server.httpGateway();
		final Class<?> clazz = wireOutClass.apply(httpGateway);
		final String version = httpGateway.getHeaderVersion(client.getQueryType()).orElse(null);
		final Object res = server.rest().patch(uri, clazz, ifMatch, patch, version);
		return (T) mapper.map(res, this.outClass);
	}

}
