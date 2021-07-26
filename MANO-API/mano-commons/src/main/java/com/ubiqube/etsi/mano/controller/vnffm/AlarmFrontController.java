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
package com.ubiqube.etsi.mano.controller.vnffm;

import static com.ubiqube.etsi.mano.Constants.ALARM_SEARCH_DEFAULT_EXCLUDE_FIELDS;
import static com.ubiqube.etsi.mano.Constants.ALARM_SEARCH_MANDATORY_FIELDS;

import java.util.UUID;
import java.util.function.Consumer;

import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.dao.mano.alarm.AckState;
import com.ubiqube.etsi.mano.dao.mano.alarm.Alarms;
import com.ubiqube.etsi.mano.dao.mano.alarm.PerceivedSeverityType;
import com.ubiqube.etsi.mano.service.AlarmVnfmController;

import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class AlarmFrontController {

	private final MapperFacade mapper;

	private final AlarmVnfmController alarmVnfmController;

	public AlarmFrontController(final MapperFacade mapper, final AlarmVnfmController alarmVnfmController) {
		super();
		this.mapper = mapper;
		this.alarmVnfmController = alarmVnfmController;
	}

	public ResponseEntity<Void> escalate(final String alarmId, @NotNull final PerceivedSeverityType perceivedSeverityRequest) {
		alarmVnfmController.escalate(UUID.fromString(alarmId), perceivedSeverityRequest);
		return ResponseEntity.noContent().build();
	}

	public <U> ResponseEntity<U> findById(final String alarmId, final Class<U> clazz, final Consumer<U> makeLink) {
		final Alarms alarm = alarmVnfmController.findById(UUID.fromString(alarmId));
		final U ret = mapper.map(alarm, clazz);
		makeLink.accept(ret);
		return ResponseEntity.ok(ret);
	}

	public <U> ResponseEntity<U> patch(final String alarmId, final AckState ackState, final String ifMatch, final Class<U> clazz) {
		final Alarms alarm = alarmVnfmController.modify(UUID.fromString(alarmId), ackState, ifMatch);
		return ResponseEntity.ok(mapper.map(alarm, clazz));
	}

	public <U> ResponseEntity<String> search(final MultiValueMap<String, String> requestParams, final Class<U> clazz, final Consumer<U> makeLink) {
		return alarmVnfmController.search(requestParams, clazz, ALARM_SEARCH_DEFAULT_EXCLUDE_FIELDS, ALARM_SEARCH_MANDATORY_FIELDS, makeLink);
	}

}
