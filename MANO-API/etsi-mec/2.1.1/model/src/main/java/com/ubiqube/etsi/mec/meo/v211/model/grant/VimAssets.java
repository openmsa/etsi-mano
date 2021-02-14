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
package com.ubiqube.etsi.mec.meo.v211.model.grant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Information about assets for the application that are managed by the MEO in the VIM, such as software images.
 */
@ApiModel(description = "Information about assets for the application that are managed by the MEO in the VIM, such as software images.")
@Validated
public class VimAssets {
	@JsonProperty("softwareImages")
	@Valid
	private List<SoftwareImages> softwareImages = null;

	// XXX: OVI we need the flavor.
	private String vimFlavourId;

	public String getVimFlavourId() {
		return vimFlavourId;
	}

	public void setVimFlavourId(final String vimFlavourId) {
		this.vimFlavourId = vimFlavourId;
	}

	public VimAssets softwareImages(final List<SoftwareImages> softwareImages) {
		this.softwareImages = softwareImages;
		return this;
	}

	public VimAssets addSoftwareImagesItem(final SoftwareImages softwareImagesItem) {
		if (this.softwareImages == null) {
			this.softwareImages = new ArrayList<>();
		}
		this.softwareImages.add(softwareImagesItem);
		return this;
	}

	/**
	 * Get softwareImages
	 *
	 * @return softwareImages
	 **/
	@ApiModelProperty(value = "")
	@Valid
	public List<SoftwareImages> getSoftwareImages() {
		return softwareImages;
	}

	public void setSoftwareImages(final List<SoftwareImages> softwareImages) {
		this.softwareImages = softwareImages;
	}

	@Override
	public boolean equals(final java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}
		final VimAssets vimAssets = (VimAssets) o;
		return Objects.equals(this.softwareImages, vimAssets.softwareImages);
	}

	@Override
	public int hashCode() {
		return Objects.hash(softwareImages);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("class VimAssets {\n");

		sb.append("    softwareImages: ").append(toIndentedString(softwareImages)).append("\n");
		sb.append("    vimFlavourId: ").append(toIndentedString(vimFlavourId)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 */
	private String toIndentedString(final java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
