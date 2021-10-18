package com.ubiqube.etsi.mano.dao.mano.common;

import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiVersion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String uriPrefix;

	private ApiVersionType type;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ApiVersionInformation> apiVersions;
}
