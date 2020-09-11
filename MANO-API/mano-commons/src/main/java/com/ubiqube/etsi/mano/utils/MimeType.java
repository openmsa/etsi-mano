package com.ubiqube.etsi.mano.utils;

import java.nio.charset.Charset;

import org.springframework.util.MimeTypeUtils;

/**
 * We are using simple idenfication. Basically we have only 2 mime types, Json
 * and Zip. If we need more types we can probably use compile group:
 * 'com.j256.simplemagic', name: 'simplemagic', version: '1.16'
 *
 * @author ovi@ubiqube.com
 *
 */
public class MimeType {

	private MimeType() {
		// Nothing.
	}

	public static String findMatch(final byte[] bytes) {
		if ((bytes.length > 2) && (bytes[0] == 'P') && (bytes[1] == 'K')) {
			return "application/zip";
		}
		if (bytes.length > 1) {
			String str = new String(bytes, Charset.defaultCharset());
			str = str.trim();
			if (str.startsWith("{")) {
				return MimeTypeUtils.APPLICATION_JSON_VALUE;
			}
		}

		return MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE;
	}
}