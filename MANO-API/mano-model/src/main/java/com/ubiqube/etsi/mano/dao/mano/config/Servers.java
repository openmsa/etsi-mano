package com.ubiqube.etsi.mano.dao.mano.config;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.ubiqube.etsi.mano.dao.mano.AuthentificationInformations;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Getter
@Setter
@Entity
public class Servers {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private AuthentificationInformations authentificationInformations;

	private String url;

	private boolean ignoreSsl;

	@Lob
	private String tlsCert;

	private String version;
}
