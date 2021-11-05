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
package com.ubiqube.etsi.mano.vnfm.v261.controller.vrqan;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubiqube.etsi.mano.vnfm.v261.model.vrqan.AlarmClearedNotification;
import com.ubiqube.etsi.mano.vnfm.v261.model.vrqan.AlarmListRebuiltNotification;
import com.ubiqube.etsi.mano.vnfm.v261.model.vrqan.AlarmNotification;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@RequestMapping("/sol003/vrqan/v1/notification")
@RolesAllowed({ "ROLE_NFVO" })
public interface VrQanNotification261Sol003Api {

	@GetMapping(produces = { "application/json" })
	default ResponseEntity<Void> defaultVrQanNotificationUrl() {
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/rebuilt", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<Void> rebuildAlarmNotification(@Valid @RequestBody AlarmListRebuiltNotification alarmListRebuiltNotification);

	@PostMapping(value = "/info", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<Void> creatingTheSubscriptionAlarmNotificationPost(@Valid @RequestBody AlarmNotification alarmNotification);

	@PostMapping(value = "/cleared", produces = { "application/json" }, consumes = { "application/json" })
	ResponseEntity<Void> clearedNotificationPost(@Valid @RequestBody AlarmClearedNotification alarmClearedNotification);
}
