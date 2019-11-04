package com.ubiqube.api.interfaces.repository;

import java.util.List;

import com.ubiqube.api.entities.repository.RepositoryElement;
import com.ubiqube.api.exception.ServiceException;

/**
 * MSA Repository interface.
 */
public interface RepositoryService {
	/**
	 * Used to read an element from disk.
	 *
	 * @param uri URI (from repository root) of the element to get
	 * @return the element read from disk
	 * @throws RepositoryException
	 */

	RepositoryElement getElement(String uri);

	/**
	 * Used to retrieve the element content
	 *
	 * @param repositoryElement The repository Element. OVI: In fact we just need an
	 *                          Url not the whole object.
	 * @return An array of bytes (the content).
	 * @throws RepositoryException
	 */
	byte[] getRepositoryElementContent(RepositoryElement repositoryElement);

	/**
	 * Check that the given URI represent an existing object in the repository.
	 *
	 * @param uri URI to check
	 * @return true when the object exists in the repository, false elsewhere.
	 * @throws ServiceException On error.
	 */
	boolean exists(String uri) throws ServiceException;

	/**
	 * Used to delete element recursivly.
	 *
	 * @param repositoryElement TODO: Change for the exact parameter once we remove
	 *                          EJB.
	 * @param user              TODO AKA callerLogin must be removed on ubiqube
	 *                          side.
	 * @throws RepositoryException On error.
	 */
	void deleteRepositoryElement(RepositoryElement repositoryElement, String user);

	/**
	 * Upload a file to the repository.
	 *
	 * @param uri         Full URI of the file to upload
	 * @param tag         Tag used in the search process
	 * @param comment     Comment used in the search process
	 * @param content     The full file content
	 * @param callerLogin TODO Have to be removed on Ubiqube side.
	 * @throws ServiceException On error.
	 */
	void addFile(String uri, String tag, String comment, String content, String callerLogin) throws ServiceException;

	/**
	 * Upload a binary file to the repository.
	 *
	 * @param uri         Full URI of the file to upload
	 * @param tag         Tag used in the search process
	 * @param comment     Comment used in the search process
	 * @param content     The full file content
	 * @param callerLogin TODO Remove.
	 * @throws ServiceException On error.
	 */
	void addFile(String uri, String tag, String comment, byte[] content, String callerLogin) throws ServiceException;

	/**
	 * Add a node in the repository used to help the data management. This is used
	 * to separate the data into logical parts. Generaly, the folders are for human
	 * control.
	 *
	 * @param uri         full URI: contains the repository name
	 * @param tag         A tag ??
	 * @param comment     A comment ??
	 * @param callerLogin TODO To be removed.
	 * @throws ServiceException On Error
	 */
	void addDirectory(String uri, String tag, String comment, String callerLogin) throws ServiceException;

	/**
	 * Search URI elements to Repository
	 *
	 * @param path    The path to recursively search.
	 * @param pattern A string for matching 'end with pattern'.
	 * @return A list of matching pattern.
	 * @throws ServiceException On error.
	 */
	List<String> doSearch(String path, String pattern) throws ServiceException;
}
