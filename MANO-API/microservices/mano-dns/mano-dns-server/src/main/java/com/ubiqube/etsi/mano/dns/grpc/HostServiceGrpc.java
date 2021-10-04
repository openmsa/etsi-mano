/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.etsi.mano.dns.grpc;

import org.lognet.springboot.grpc.GRpcService;

import com.ubiqube.etsi.mano.dns.api.v1.AddHostRequest;
import com.ubiqube.etsi.mano.dns.api.v1.AddHostResponse;
import com.ubiqube.etsi.mano.dns.api.v1.DnsServiceGrpc.DnsServiceImplBase;
import com.ubiqube.etsi.mano.dns.api.v1.ListHostsRequest;
import com.ubiqube.etsi.mano.dns.api.v1.ListHostsResponse;

import io.grpc.stub.StreamObserver;

@GRpcService
public class HostServiceGrpc extends DnsServiceImplBase {

	@Override
	public void addHost(final AddHostRequest request, final StreamObserver<AddHostResponse> responseObserver) {
		final AddHostResponse host = AddHostResponse.newBuilder().build();
		responseObserver.onNext(host);
		responseObserver.onCompleted();
	}

	@Override
	public void listHosts(final ListHostsRequest request, final StreamObserver<ListHostsResponse> responseObserver) {
		// TODO Auto-generated method stub
		super.listHosts(request, responseObserver);
	}

}
