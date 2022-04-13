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
package tosca.interfaces.nfv;

import com.ubiqube.parser.tosca.OperationDefinition;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public class Vnflcm {

	private OperationDefinition instantiate;

	private OperationDefinition terminate;

	public OperationDefinition getInstantiate() {
		return instantiate;
	}

	public void setInstantiate(final OperationDefinition instantiate) {
		this.instantiate = instantiate;
	}

	public OperationDefinition getTerminate() {
		return terminate;
	}

	public void setTerminate(final OperationDefinition terminate) {
		this.terminate = terminate;
	}

}
