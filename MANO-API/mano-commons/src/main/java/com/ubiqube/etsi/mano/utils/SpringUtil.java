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
package com.ubiqube.etsi.mano.utils;

import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

import com.ubiqube.etsi.mano.exception.RequestRangeNotSatisfiableException;

public class SpringUtil {
	private SpringUtil() {
		// Nothing.
	}

	public static ResponseEntity<List<ResourceRegion>> handleBytes(final byte[] bytes, final String _range) {
		final Optional<String> oRange = Optional.ofNullable(_range);
		final List<HttpRange> ranges = HttpRange.parseRanges(oRange.orElse("bytes=0-"));
		BodyBuilder bodyBuilder = ResponseEntity.status(oRange.isPresent() ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK);
		if (oRange.isPresent()) {
			bodyBuilder = bodyBuilder.contentType(MediaType.APPLICATION_OCTET_STREAM);
		} else {
			final String mime = MimeType.findMatch(bytes);
			bodyBuilder = bodyBuilder.header("Content-Type", mime);
		}
		final ByteArrayResource resource = new ByteArrayResource(bytes);
		try {
			final List<ResourceRegion> body = HttpRange.toResourceRegions(ranges, resource);
			return bodyBuilder.body(body);
		} catch (final IllegalArgumentException e) {
			throw new RequestRangeNotSatisfiableException(e.getMessage(), e);
		}
	}
}
