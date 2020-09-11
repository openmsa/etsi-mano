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

public class SpringUtil {
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
		final List<ResourceRegion> body = HttpRange.toResourceRegions(ranges, resource);
		return bodyBuilder.body(body);
	}
}
