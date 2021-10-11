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
package com.ubiqube.etsi.mano.vnfm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.dao.mano.ApiTypesEnum;
import com.ubiqube.etsi.mano.dao.mano.Subscription;
import com.ubiqube.etsi.mano.dao.mano.subs.SubscriptionType;
import com.ubiqube.etsi.mano.service.NfvoService;

@Service
@ConditionalOnMissingBean(NfvoService.class)
public class NfvoRegisterService implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(NfvoRegisterService.class);

	private final VnfmVersionManager versionManager;

	public NfvoRegisterService(final VnfmVersionManager versionManager) {
		super();
		this.versionManager = versionManager;
	}

	@Override
	public void run(final String... args) throws Exception {
		final Subscription subscription = new Subscription();
		subscription.setApi(ApiTypesEnum.SOL003);
		subscription.setSubscriptionType(SubscriptionType.NSDVNF);
		try {
			versionManager.subscribe(subscription);
		} catch (final RuntimeException e) {
			LOG.warn("Unable to register with NFVO.", e);
		}
	}
}
