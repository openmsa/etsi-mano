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
package com.ubiqube.etsi.mano.repository;

import java.io.InputStream;
import java.util.List;

/**
 * XXX Maybe we can change String to Path.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface Low {

	/**
	 * Check if file or directory exist.
	 *
	 * @param _path The path.
	 * @return True if path or filename exist.
	 */
	boolean exist(String _path);

	/**
	 * Create a directory.
	 *
	 * @param _path the path to the directory.
	 */
	void mkdir(String _path);

	/**
	 * Convenient method to add a file to the repository.
	 *
	 * @param _path    The path where the file will be created.
	 * @param _content The content of the file.
	 */
	void add(String _path, byte[] _content);

	/**
	 * Convenient method to add a file to the repository.
	 *
	 * @param _path   The path where the file will be created.
	 * @param _stream The inputStream.
	 */
	void add(String _path, InputStream _stream);

	/**
	 * Delete a file in the repository.
	 *
	 * @param _path The path.
	 */
	void delete(String _path);

	/**
	 * Recursively search for file element.
	 *
	 * @param _path    The path to scan.
	 * @param _pattern the end the file name, extension.
	 * @return The list of matching files.
	 */
	List<String> find(String _path, String _pattern);

	/**
	 * Is directory.
	 *
	 * @param _path The path.
	 * @return True if path point on a directory.
	 */
	boolean isDirectory(String _path);

	/**
	 * Retrieve a file from the repository.
	 *
	 * @param path A file path.
	 * @param min  Start offset.
	 * @param max  End offset.
	 * @return The content of the file.
	 */
	byte[] get(String _path, int _min, Long _max);

	/**
	 * Convenient method for retrieving a file.
	 *
	 * @param _pathThe filename.
	 * @return The content of the file.
	 */
	byte[] get(String _path);
}