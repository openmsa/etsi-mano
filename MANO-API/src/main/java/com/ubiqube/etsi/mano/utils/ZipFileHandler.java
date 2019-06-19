package com.ubiqube.etsi.mano.utils;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.interfaces.repository.RepositoryService;
import com.ubiqube.etsi.mano.exception.RequestRangeNotSatisfiableException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileHandler {

    private List<String> filesUri = new LinkedList<String>();
    private RepositoryService repositoryService;
    private long zipFileByteArrayLength = 0;

    public ZipFileHandler(RepositoryService repositoryService, List<String> filesUri) {
        this.filesUri = filesUri;
        this.repositoryService = repositoryService;
    }

    /**
     * ZipFileHandler class constructor.
     *
     * @param repositoryService
     * @param filesUri
     */
    public ZipFileHandler(RepositoryService repositoryService, String filesUri) {
        this.filesUri.add(filesUri);
        this.repositoryService = repositoryService;
    }

    /**
     * Get length of ZIP byte array.
     *
     * @return
     *      Byte array length
     */
    public long zipFileByteArrayLength() {
        return this.zipFileByteArrayLength;
    }

    /**
     *  Create ZIP from a list of files.
     *
     * @return ByteArrayOutputStream
     * @throws IOException
     */
    public ByteArrayOutputStream getZipFile() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos);

        // Retrieve all VNF packages details from repository data
        for (String vnfPckgContentUri : this.filesUri) {
            RepositoryElement repositoryElement = repositoryService.getElement(vnfPckgContentUri);
            repositoryElement.getName();
            byte[] fileContent = repositoryService.getRepositoryElementContent(repositoryElement);

            zos.putNextEntry(new ZipEntry(repositoryElement.getName()));
            zos.write(fileContent);
            zos.closeEntry();
        }
        zos.close();
        bos.close();

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
    public ByteArrayOutputStream getByteRangeZipFile(int from, int to) throws IOException, RequestRangeNotSatisfiableException {
        byte[] bytesInput = this.getZipFile().toByteArray();
        zipFileByteArrayLength = bytesInput.length;
        if (from < 0 || to <= 0 || from >= zipFileByteArrayLength || to >= zipFileByteArrayLength) {
            throw new RequestRangeNotSatisfiableException("Request Range Not Satisfiable.");
        }
        byte[] bytesOutput = Arrays.copyOfRange(bytesInput, from, to);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(bytesOutput.length);
        bos.write(bytesOutput, 0, bytesOutput.length);
        return bos;
    }
}
