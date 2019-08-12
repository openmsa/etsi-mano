package com.ubiqube.etsi.mano.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.RequestRangeNotSatisfiableException;

public class ZipFileHandler {

	private List<String> filesUri = new LinkedList<>();
	private final RepositoryService repositoryService;
	private long zipFileByteArrayLength = 0;

	public ZipFileHandler(RepositoryService _repositoryService, List<String> _filesUri) {
		this.filesUri = _filesUri;
		this.repositoryService = _repositoryService;
	}

	/**
	 * ZipFileHandler class constructor.
	 *
	 * @param repositoryService
	 * @param filesUri
	 */
	public ZipFileHandler(RepositoryService _repositoryService, String _filesUri) {
		this.filesUri.add(_filesUri);
		this.repositoryService = _repositoryService;
	}

	/**
	 * Get length of ZIP byte array.
	 *
	 * @return Byte array length
	 */
	public long zipFileByteArrayLength() {
		return this.zipFileByteArrayLength;
	}

	/**
	 * Create ZIP from a list of files.
	 *
	 * @return ByteArrayOutputStream
	 * @throws IOException
	 */
	public ByteArrayOutputStream getZipFile() throws IOException {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (final ZipOutputStream zos = new ZipOutputStream(bos)) {

			// Retrieve all VNF packages details from repository data
			for (final String vnfPckgContentUri : this.filesUri) {
				final RepositoryElement repositoryElement = repositoryService.getElement(vnfPckgContentUri);
				repositoryElement.getName();
				final byte[] fileContent = repositoryService.getRepositoryElementContent(repositoryElement);

				zos.putNextEntry(new ZipEntry(repositoryElement.getName()));
				zos.write(fileContent);
				zos.closeEntry();
			}
			bos.close();
		}
		return bos;
	}

	/**
	 * Get ZIP byte range.
	 *
	 * @param from
	 * @param to
	 * @return ByteArrayOutputStream
	 * @throws IOException
	 * @throws RequestRangeNotSatisfiableException
	 */
	public ByteArrayOutputStream getByteRangeZipFile(int from, Integer to) throws IOException, RequestRangeNotSatisfiableException {
		final byte[] bytesInput = this.getZipFile().toByteArray();
		zipFileByteArrayLength = bytesInput.length;
		if ((from < 0) || (to <= 0) || (from >= zipFileByteArrayLength) || (to >= zipFileByteArrayLength)) {
			throw new RequestRangeNotSatisfiableException("Request Range Not Satisfiable.");
		}
		final byte[] bytesOutput = Arrays.copyOfRange(bytesInput, from, to.intValue());

		final ByteArrayOutputStream bos = new ByteArrayOutputStream(bytesOutput.length);
		bos.write(bytesOutput, 0, bytesOutput.length);
		return bos;
	}
}
