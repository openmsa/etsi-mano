package com.ubiqube.api.entities.repository;

public interface RepositoryElement {
	public enum RepositoryElementType {
		FOLDER, FILE, CUSTOMER, OPERATOR, UPLOAD, MANUFACTURER, MODEL, ROOT, REPOSITORY, DIRECTORY;
	}

	String getName();

	RepositoryElementType getType();
}
