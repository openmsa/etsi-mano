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
package com.ubiqube.etsi.mano.tf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.juniper.contrail.api.Status.ErrorHandler;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class LogErrorHandler implements ErrorHandler {

	private static final Logger LOG = LoggerFactory.getLogger(LogErrorHandler.class);

	@Override
	public void handle(final String errorMessage) {
		LOG.error("> {}", errorMessage);
	}

}
