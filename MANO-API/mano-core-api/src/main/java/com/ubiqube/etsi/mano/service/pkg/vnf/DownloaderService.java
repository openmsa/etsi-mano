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
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
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
import com.ubiqube.etsi.mano.service.vim.VimException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Service
public class DownloaderService {

	private static final Logger LOG = LoggerFactory.getLogger(DownloaderService.class);

	private final VnfPackageRepository packageRepository;

	public DownloaderService(final VnfPackageRepository packageRepository) {
		super();
		this.packageRepository = packageRepository;
	}

	public void doDownload(final List<SoftwareImage> sws, final UUID vnfPkgId) {
		final ExecutorService executor = Executors.newFixedThreadPool(5);
		final CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
		final List<Future<String>> all = new ArrayList<>();
		sws.forEach(x -> {
			final Future<String> res = completionService.submit(() -> doDownload(x, vnfPkgId));
			all.add(res);
		});
		final Throwable ex = waitForCompletion(completionService, all);
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.MINUTES);
		} catch (final InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new VimException(e);
		}
		if (null != ex) {
			throw new VimException(ex);
		}
	}

	private static Throwable waitForCompletion(final CompletionService<String> completionService, final List<Future<String>> all) {
		int received = 0;
		while (received < all.size()) {
			try {
				final Future<?> resultFuture = completionService.take();
				resultFuture.get();
				received++;
			} catch (final InterruptedException e) {
				LOG.info("", e);
				Thread.currentThread().interrupt();
				return e;
			} catch (final ExecutionException e) {
				return e.getCause();
			}
		}
		return null;
	}

	private String doDownload(final SoftwareImage si, final UUID vnfPkgId) {
		final ExceptionHandler eh = new ExceptionHandler();
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
					.doFinally(onComplete(osPipe))
					.onErrorResume(Throwable.class, e -> {
						eh.setE(e);
						eh.setMessage(e.getMessage());
						return Mono.error(e);
					})
					.subscribe(DataBufferUtils.releaseConsumer());
			si.setNfvoPath(UUID.randomUUID().toString());
			packageRepository.storeBinary(vnfPkgId, si.getNfvoPath(), isPipe);
		} catch (final IOException e) {
			LOG.error("", e);
			return null;
		}
		if (eh.getE() != null) {
			throw new GenericException(eh.getMessage(), eh.getE());
		}
		LOG.error("OK");
		return "OK";
	}

	private static Consumer<SignalType> onComplete(final PipedOutputStream osPipe) {
		return s -> {
			closePipe(osPipe);
		};
	}

	private static WebClient createWebClien() {
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(HttpClient.create().followRedirect(false)))
				.build();
	}

	private static Function<ClientResponse, Mono<? extends Throwable>> exepctionFunction(final PipedOutputStream osPipe) {
		return response -> {
			closePipe(osPipe);
			throw new GenericException("An error occured." + response.rawStatusCode());
		};
	}

	private static void closePipe(final OutputStream osPipe) {
		try (osPipe) {
			//
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private class ExceptionHandler {
		private String message;
		private Throwable e;

		public String getMessage() {
			return message;
		}

		public void setMessage(final String message) {
			this.message = message;
		}

		public Throwable getE() {
			return e;
		}

		public void setE(final Throwable e) {
			this.e = e;
		}

	}
}
