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
package com.ubiqube.etsi.mano.dao.mano.v2;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.Audit;
import com.ubiqube.etsi.mano.dao.mano.ChangeType;

public class AbstractTask implements Task {

	@Override
	public UUID getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Audit getAudit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAudit(final Audit audit) {
		// TODO Auto-generated method stub

	}

	@Override
	public ChangeType getChangeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlias() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToscaName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStartDate(final LocalDateTime now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(final PlanStatusType started) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEndDate(final LocalDateTime now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVimResourceId(final String res) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getVimResourceId() {
		// TODO Auto-generated method stub
		return null;
	}

}
