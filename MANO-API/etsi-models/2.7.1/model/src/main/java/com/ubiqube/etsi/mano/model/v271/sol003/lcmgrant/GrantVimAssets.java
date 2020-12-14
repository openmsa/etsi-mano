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
package com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.VimComputeResourceFlavour;
import com.ubiqube.etsi.mano.model.v271.sol003.lcmgrant.VimSoftwareImage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information about assets for the VNF that are managed by the NFVO in the VIM, such as software images and virtualised compute resource flavours. The Grant response allows the NFVO to pass to the VNFM VIM assets related to the VNF package that is identified by the vnfdId attribute in the corresponding Grant request. The NFVO may send in each Grant response the full set of VIM assets related to the VNF package defined by the vnfdId in the related Grant request, but shall send this information if the vnfdId in the related Grant request differs from the vnfdId passed in the previous Grant request, or if the Grant response is related to an InstantiateVnf operation. The set of VIM assets shall not change between subsequent Grant responses if the vnfdId has not changed. During each LCM operation occurrence, the VIM assets that relate to the VNF package identified by the current value of the vnfdId attribute in the “VnfInstance” structure shall be used by the VNFM for newly created resources. If the VNF package identifier of the VNF instance has been updated, VIM assets that relate to the previously-used VNF package(s), and that were communicated in previous Grant responses, apply to existing resources. 
 */
@ApiModel(description = "Information about assets for the VNF that are managed by the NFVO in the VIM, such as software images and virtualised compute resource flavours. The Grant response allows the NFVO to pass to the VNFM VIM assets related to the VNF package that is identified by the vnfdId attribute in the corresponding Grant request. The NFVO may send in each Grant response the full set of VIM assets related to the VNF package defined by the vnfdId in the related Grant request, but shall send this information if the vnfdId in the related Grant request differs from the vnfdId passed in the previous Grant request, or if the Grant response is related to an InstantiateVnf operation. The set of VIM assets shall not change between subsequent Grant responses if the vnfdId has not changed. During each LCM operation occurrence, the VIM assets that relate to the VNF package identified by the current value of the vnfdId attribute in the “VnfInstance” structure shall be used by the VNFM for newly created resources. If the VNF package identifier of the VNF instance has been updated, VIM assets that relate to the previously-used VNF package(s), and that were communicated in previous Grant responses, apply to existing resources. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-09T17:47:42.824+01:00")

public class GrantVimAssets   {
  @JsonProperty("computeResourceFlavours")
  @Valid
  private List<VimComputeResourceFlavour> computeResourceFlavours = null;

  @JsonProperty("softwareImages")
  @Valid
  private List<VimSoftwareImage> softwareImages = null;

  public GrantVimAssets computeResourceFlavours(List<VimComputeResourceFlavour> computeResourceFlavours) {
    this.computeResourceFlavours = computeResourceFlavours;
    return this;
  }

  public GrantVimAssets addComputeResourceFlavoursItem(VimComputeResourceFlavour computeResourceFlavoursItem) {
    if (this.computeResourceFlavours == null) {
      this.computeResourceFlavours = new ArrayList<>();
    }
    this.computeResourceFlavours.add(computeResourceFlavoursItem);
    return this;
  }

  /**
   * Mappings between virtual compute descriptors defined in the VNFD and compute resource flavours managed in the VIM. 
   * @return computeResourceFlavours
  **/
  @ApiModelProperty(value = "Mappings between virtual compute descriptors defined in the VNFD and compute resource flavours managed in the VIM. ")

  @Valid

  public List<VimComputeResourceFlavour> getComputeResourceFlavours() {
    return computeResourceFlavours;
  }

  public void setComputeResourceFlavours(List<VimComputeResourceFlavour> computeResourceFlavours) {
    this.computeResourceFlavours = computeResourceFlavours;
  }

  public GrantVimAssets softwareImages(List<VimSoftwareImage> softwareImages) {
    this.softwareImages = softwareImages;
    return this;
  }

  public GrantVimAssets addSoftwareImagesItem(VimSoftwareImage softwareImagesItem) {
    if (this.softwareImages == null) {
      this.softwareImages = new ArrayList<>();
    }
    this.softwareImages.add(softwareImagesItem);
    return this;
  }

  /**
   * Mappings between software images defined in the VNFD and software images managed in the VIM. 
   * @return softwareImages
  **/
  @ApiModelProperty(value = "Mappings between software images defined in the VNFD and software images managed in the VIM. ")

  @Valid

  public List<VimSoftwareImage> getSoftwareImages() {
    return softwareImages;
  }

  public void setSoftwareImages(List<VimSoftwareImage> softwareImages) {
    this.softwareImages = softwareImages;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GrantVimAssets grantVimAssets = (GrantVimAssets) o;
    return Objects.equals(this.computeResourceFlavours, grantVimAssets.computeResourceFlavours) &&
        Objects.equals(this.softwareImages, grantVimAssets.softwareImages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(computeResourceFlavours, softwareImages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GrantVimAssets {\n");
    
    sb.append("    computeResourceFlavours: ").append(toIndentedString(computeResourceFlavours)).append("\n");
    sb.append("    softwareImages: ").append(toIndentedString(softwareImages)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

