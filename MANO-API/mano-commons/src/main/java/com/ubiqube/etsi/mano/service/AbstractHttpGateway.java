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
package com.ubiqube.etsi.mano.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ubiqube.etsi.mano.controller.Protocol;
import com.ubiqube.etsi.mano.controller.Protocols;
import com.ubiqube.etsi.mano.dao.mano.common.ApiVersionType;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.utils.Version;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public abstract class AbstractHttpGateway implements HttpGateway {
	private static final String SOL003 = "sol003";
	private final ObjectMapper mapper = new ObjectMapper();
	private List<Protocol> protocols;

	protected AbstractHttpGateway() {
		final Path path = Paths.get("/", getVersion(), "mano-versions.json");
		try (final InputStream in = this.getClass().getResourceAsStream(path.toString())) {
			final TypeReference<List<Protocol>> tr = new TypeReference<>() {
				//
			};
			protocols = mapper.readValue(in, tr);
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	@Override
	public String getUrlFor(final ApiVersionType type) {
		return getUrlFor(type, this::getProtocols);
	}

	public static <U> U getUrlFor(final ApiVersionType type, final BiFunction<String, String, U> func) {
		switch (type) {
		case SOL003_VNFFM:
			return func.apply(SOL003, "vnffm");
		case SOL003_VNFIND:
			return func.apply(SOL003, "vnfind");
		case SOL003_VNFPM:
			return func.apply(SOL003, "vnfpm");
		case SOL003_VNFSNAPSHOTPKGM:
			return func.apply(SOL003, "vnfsnapshotpkgm");
		case SOL003_VNFLCM:
			return func.apply(SOL003, "vnflcm");
		case SOL003_VRQAN:
			return func.apply(SOL003, "vrqan");
		case SOL003_GRANT:
			return func.apply(SOL003, "grant");
		case SOL003_VNFPKGM:
			return func.apply(SOL003, "vnfpkgm");
		case SOL005_NSD:
			return func.apply("sol005", "nsd");
		default:
			throw new IllegalArgumentException("Unexpected value: " + type.name());
		}
	}

	@Override
	public Optional<String> getHeaderVersion(final ApiVersionType apiVersionType) {
		if ("2.6.1".equals(getVersion())) {
			return Optional.empty();
		}
		final BiFunction<String, String, MajorFragment> func = MajorFragment::new;
		final MajorFragment mf = getUrlFor(apiVersionType, func);
		return protocols.stream()
				.filter(x -> x.getProto().equals(mf.major()))
				.flatMap(x -> x.getProtocols().stream())
				.filter(x -> x.getFragment().equals(mf.fragment()))
				.map(Protocols::getVersion)
				.findFirst();
	}

	private record MajorFragment(String major, String fragment) {
		//
	}

	private String getProtocols(final String major, final String fragment) {
		final Optional<Version> version = protocols.stream()
				.filter(x -> x.getProto().equals(major))
				.flatMap(x -> x.getProtocols().stream())
				.filter(x -> x.getFragment().equals(fragment))
				.map(Protocols::getVersion)
				.map(Version::new)
				.findFirst();
		return version.map(x -> new File(fragment, "v" + x.getMajor()).toString())
				.orElse(new File(fragment, "v1").toString());
	}

}
