package com.ubiqube.etsi.mano.service;

/**
 * Simple abstraction for a very complex task => Patching.
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
public interface Patcher {
	/**
	 * Apply a JSON document to an entity.
	 * 
	 * @param _patchDocument A JSON document as a string.
	 * @param _entity        An object.
	 */
	void patch(String _patchDocument, Object _entity);
}
