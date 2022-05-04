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
package com.ubiqube.etsi.mano.tosca;

import java.nio.charset.Charset;

import com.ubiqube.etsi.mano.sol004.vfs.DirectZipMr;
import com.ubiqube.etsi.mano.sol004.vfs.VirtualFileSystem;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class ZipMrResolver extends AbstractResolver {
	private final VirtualFileSystem dzm;

	public ZipMrResolver(final DirectZipMr dzm) {
		this.dzm = dzm;
	}

	@Override
	protected boolean exist(final String string) {
		return dzm.exist(string);
	}

	@Override
	protected String handleContent(final String url) {
		return new String(dzm.getFileContent(url), Charset.defaultCharset());
	}

}
