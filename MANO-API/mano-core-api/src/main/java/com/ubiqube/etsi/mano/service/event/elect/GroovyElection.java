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
package com.ubiqube.etsi.mano.service.event.elect;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.codehaus.groovy.control.CompilationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.config.properties.ManoElectionProperties;
import com.ubiqube.etsi.mano.dao.mano.GrantResponse;
import com.ubiqube.etsi.mano.dao.mano.VimConnectionInformation;
import com.ubiqube.etsi.mano.dao.mano.VnfCompute;
import com.ubiqube.etsi.mano.dao.mano.VnfStorage;
import com.ubiqube.etsi.mano.exception.GenericException;
import com.ubiqube.etsi.mano.service.vim.VimManager;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
public class GroovyElection implements VimElection {

	private static final Logger LOG = LoggerFactory.getLogger(GroovyElection.class);

	private final VimManager vimManager;
	private final ManoElectionProperties properties;

	public GroovyElection(final VimManager _vimManager, final ManoElectionProperties _properties) {
		vimManager = _vimManager;
		properties = _properties;
	}

	@Override
	public VimConnectionInformation doElection(final GrantResponse grant, final Set<VnfCompute> vnfcs, final Set<VnfStorage> storages) {
		final ExecutorService executor = Executors.newFixedThreadPool(5);
		final CompletionService<VoteResponse> completionService = new ExecutorCompletionService<>(executor);
		final List<Path> scripts = findScript();
		final List<String> list = scripts.stream().map(Path::toString).collect(Collectors.toList());
		final Iterable<VimConnectionInformation> ite = vimManager.findAllVimconnections();
		int numberOfVim = 0;
		for (final VimConnectionInformation vimConnectionInformation : ite) {
			completionService.submit(() -> executueScripts(list, vimConnectionInformation));
			numberOfVim++;
		}
		int received = 0;
		final List<VimConnectionInformation> vims = new ArrayList<>();
		while (received < numberOfVim) {
			try {
				final Future<VoteResponse> resultFuture = completionService.take();
				final VoteResponse result = resultFuture.get();
				if (result.getVoteStatus() == VoteStatus.GRANTED) {
					vims.add(result.getVimConnectionInformation());
				}
				received++;
			} catch (final Exception e) {
				LOG.error("", e);
				executor.shutdownNow();
				return null;
			}
		}
		executor.shutdown();
		if (vims.isEmpty()) {
			return null;
		}
		return vims.get(0);
	}

	List<Path> findScript() {
		final String scriPtath = properties.getScriptPath();
		final Path path = Paths.get(scriPtath);
		if (!path.toFile().exists()) {
			return new ArrayList<>();
		}
		try (Stream<Path> stream = Files.walk(path)) {
			return stream.filter(x -> x.toFile().isFile())
					.filter(x -> x.getFileName().endsWith("elect"))
					.collect(Collectors.toList());
		} catch (final IOException e) {
			throw new GenericException(e);
		}
	}

	private static VoteResponse executueScripts(final List<String> list, final VimConnectionInformation vimConnectionInformation) {
		LOG.info("Running Groovy script against {}", vimConnectionInformation.getVimId());
		final VoteResponse ret = new VoteResponse(VoteStatus.GRANTED, vimConnectionInformation);
		for (final String string : list) {
			final VoteStatus res = executueScript(string, vimConnectionInformation);
			if (res == VoteStatus.DENIED) {
				ret.setVoteStatus(VoteStatus.DENIED);
				return ret;
			}
		}
		return ret;
	}

	static VoteStatus executueScript(final String script, final VimConnectionInformation vimConnectionInformation) {
		final Binding binding = new Binding();
		binding.setVariable("vimConnectionInformation", vimConnectionInformation);
		final GroovyShell shell = new GroovyShell(binding);
		try {
			return (VoteStatus) shell.evaluate(new File(script));
		} catch (CompilationFailedException | IOException e) {
			throw new GenericException(e);
		}
	}

	@Setter
	@Getter
	@AllArgsConstructor
	static class VoteResponse {
		private VoteStatus voteStatus;

		private VimConnectionInformation vimConnectionInformation;
	}
}
