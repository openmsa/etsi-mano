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
package com.ubiqube.etsi.mano.service.graph.nfvo;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.dao.mano.NsInstantiatedBase;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.service.VnfmInterface;
import com.ubiqube.etsi.mano.service.graph.UnitOfWorkBase;
import com.ubiqube.etsi.mano.service.graph.nfvo.NsUnitOfWork.NsUowType;
import com.ubiqube.etsi.mano.service.vim.Vim;

public interface NsUnitOfWork extends UnitOfWorkBase<NsUowType>, Serializable {
	public enum NsUowType {
		NSD("NSD"),
		VNF("VNF"),
		PNF("PNF"),
		SAP("SAP"),
		NSVL("NSVL"),
		VNFFG("VNFFG");

		private String value;

		NsUowType(final String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}

		@JsonCreator
		public static NsUowType fromValue(final String text) {
			for (final NsUowType b : NsUowType.values()) {
				if (String.valueOf(b.value).equals(text)) {
					return b;
				}
			}
			return null;
		}
	}

	String exec(final VimConnectionInformation vimConnectionInformation, VnfmInterface vnfm, Vim vim, Map<String, String> context);

	NsInstantiatedBase getResourceHandleEntity();

	String rollback(VimConnectionInformation vimConnectionInformation, VnfmInterface vnfm, Vim vim, String resourceId, Map<String, String> context);

}
