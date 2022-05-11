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
package com.ubiqube.etsi.mano.service.event;

import java.util.List;

import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfPackage;
import com.ubiqube.etsi.mano.service.event.AbstractGrantAction.QuotaNeeded;
import com.ubiqube.etsi.mano.service.event.elect.GroovyElection;

/**
 * The idea is to evict all VIM that doesn't have the require resources or
 * features. The difference with the {@link GroovyElection} is that this one is
 * boolean while the groovy one is a ranked decision one.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface PreVimSelection {

	List<VimConnectionInformation> selectVims(final VnfPackage vnfPackage, final GrantResponse grantResponse, QuotaNeeded needed);
}
