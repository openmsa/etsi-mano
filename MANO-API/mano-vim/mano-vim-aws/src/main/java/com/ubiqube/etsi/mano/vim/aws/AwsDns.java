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
package com.ubiqube.etsi.mano.vim.aws;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.route53.AmazonRoute53;
import com.amazonaws.services.route53.AmazonRoute53Client;
import com.amazonaws.services.route53.model.Change;
import com.amazonaws.services.route53.model.ChangeAction;
import com.amazonaws.services.route53.model.ChangeBatch;
import com.amazonaws.services.route53.model.ChangeResourceRecordSetsRequest;
import com.amazonaws.services.route53.model.ChangeResourceRecordSetsResult;
import com.amazonaws.services.route53.model.CreateHostedZoneRequest;
import com.amazonaws.services.route53.model.DeleteHostedZoneRequest;
import com.amazonaws.services.route53.model.RRType;
import com.amazonaws.services.route53.model.ResourceRecord;
import com.amazonaws.services.route53.model.ResourceRecordSet;
import com.ubiqube.etsi.mano.service.vim.Dns;

public class AwsDns implements Dns {
	private final AmazonRoute53 client;
	private final AWSStaticCredentialsProvider credential;

	public AwsDns(final AWSStaticCredentialsProvider credential) {
		this.credential = credential;
		client = AmazonRoute53Client.builder().build();
	}

	@Override
	public String createDnsZone(final String zoneName) {
		final CreateHostedZoneRequest createHostedZoneRequest = new CreateHostedZoneRequest()
				.withName(zoneName)
				.withRequestCredentialsProvider(credential);
		return client.createHostedZone(createHostedZoneRequest).getHostedZone().getId();
	}

	@Override
	public void deleteDnsZone(final String resourceId) {
		final DeleteHostedZoneRequest deleteHostedZoneRequest = new DeleteHostedZoneRequest(resourceId)
				.withRequestCredentialsProvider(credential);
		client.deleteHostedZone(deleteHostedZoneRequest);
	}

	@Override
	public String createDnsRecordSet(final String zoneId, final String hostname, final String networkName) {
		final ResourceRecord record = new ResourceRecord(hostname);
		final List<ResourceRecord> records = new ArrayList<>();
		records.add(record);
		final ResourceRecordSet recordsSet = new ResourceRecordSet();
		recordsSet.setResourceRecords(records);
		recordsSet.setType(RRType.CNAME);
		recordsSet.setTTL(900L);
		recordsSet.setName(networkName + ".");
		final Change change = new Change(ChangeAction.CREATE, recordsSet);
		final List<Change> changes = new ArrayList<>();
		changes.add(change);
		final ChangeBatch batch = new ChangeBatch(changes);
		final ChangeResourceRecordSetsRequest request = new ChangeResourceRecordSetsRequest()
				.withRequestCredentialsProvider(credential);
		request.setChangeBatch(batch);
		request.setHostedZoneId(zoneId);
		final ChangeResourceRecordSetsResult result = client.changeResourceRecordSets(request);
		return result.getChangeInfo().getId();
	}

	@Override
	public void deleteDnsRecordSet(final String resourceId, final String zoneId, final Set<String> ips) {
		// TODO Auto-generated method stub

	}

}
