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
package com.ubiqube.etsi.mano.service.pkg.vnf;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.ubiqube.etsi.mano.dao.mano.SoftwareImage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.repository.VnfPackageRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class DownloaderService {

	private static final Logger LOG = LoggerFactory.getLogger(DownloaderService.class);

	private final ExecutorService executor;

	private VnfPackageRepository packageRepository;

	public DownloaderService() {
		executor = Executors.newFixedThreadPool(5);
	}

	public void doDownload(final List<SoftwareImage> sws, final UUID vnfPkgId) {
		sws.forEach(x -> executor.submit(() -> doDownload(x, vnfPkgId)));
	}

	private void doDownload(final SoftwareImage si, final UUID vnfPkgId) {
		final WebClient webclient = createWebClien();
		try (final PipedOutputStream osPipe = new PipedOutputStream();
				final PipedInputStream isPipe = new PipedInputStream(osPipe)) {
			final Flux<DataBuffer> wc = webclient
					.get()
					.uri(si.getImagePath())
					.accept(MediaType.APPLICATION_OCTET_STREAM, MediaType.ALL)
					.retrieve()
					.onRawStatus(i -> i != 200, exepctionFunction(osPipe))
					.bodyToFlux(DataBuffer.class);

			DataBufferUtils.write(wc, osPipe)
					.doOnComplete(onComplete(osPipe))
					.subscribe(DataBufferUtils.releaseConsumer());
			si.setNfvoPath(UUID.randomUUID().toString());
			packageRepository.storeBinary(vnfPkgId, si.getNfvoPath(), isPipe);
		} catch (final IOException e) {
			LOG.error("", e);
		}
	}

	private static Runnable onComplete(final PipedOutputStream osPipe) {
		return () -> {
			try {
				osPipe.close();
			} catch (final IOException e) {
				throw new GenericException(e);
			}
		};
	}

	private static WebClient createWebClien() {
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(false)))
				.build();
	}

	private static Function<ClientResponse, Mono<? extends Throwable>> exepctionFunction(final PipedOutputStream osPipe) {
		return response -> {
			try {
				try (osPipe) {
					//
				}
			} catch (final IOException e) {
				throw new GenericException(e);
			}
			throw new GenericException("An error occured." + response.rawStatusCode());
		};
	}
}
