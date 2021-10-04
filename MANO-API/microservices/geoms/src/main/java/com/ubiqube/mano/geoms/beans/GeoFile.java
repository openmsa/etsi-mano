/**
 * This copy of Woodstox XML processor is licensed under the
 * Apache (Software) License, version 2.0 ("the License").
 * See the License for details about distribution rights, and the
 * specific rights regarding derivate works.
 *
 * You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/
 *
 * A copy is also included in the downloadable source code package
 * containing Woodstox, in file "ASL2.0", under the same directory
 * as this file.
 */
package com.ubiqube.mano.geoms.beans;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Olivier Vignaud <ovi@ubiqube.com>
 *
 */
@Setter
@Getter
public class GeoFile {
	@CsvBindByName
	private String city;

	@CsvBindByName
	private double lat;

	@CsvBindByName
	private double lng;

	@CsvBindByName
	private String country;

	@CsvBindByName
	private String iso2;

	@CsvBindByName
	private String iso3;

	@CsvBindByName
	private long id;
}
