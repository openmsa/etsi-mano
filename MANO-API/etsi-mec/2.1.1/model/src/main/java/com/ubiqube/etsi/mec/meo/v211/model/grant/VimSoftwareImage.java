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

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VimSoftwareImage
 */
@Validated
public class VimSoftwareImage   {
  @JsonProperty("appDSoftwareImageId")
  private String appDSoftwareImageId = null;

  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("vimSoftwareImageId")
  private String vimSoftwareImageId = null;

  public VimSoftwareImage appDSoftwareImageId(String appDSoftwareImageId) {
    this.appDSoftwareImageId = appDSoftwareImageId;
    return this;
  }

  /**
   * Identifier which references the software image descriptor in the AppD.
   * @return appDSoftwareImageId
  **/
  @ApiModelProperty(required = true, value = "Identifier which references the software image descriptor in the AppD.")
      @NotNull

    public String getAppDSoftwareImageId() {
    return appDSoftwareImageId;
  }

  public void setAppDSoftwareImageId(String appDSoftwareImageId) {
    this.appDSoftwareImageId = appDSoftwareImageId;
  }

  public VimSoftwareImage vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Identifier of the VIM connection to access the software image referenced in this structure. 
   * @return vimConnectionId
  **/
  @ApiModelProperty(value = "Identifier of the VIM connection to access the software image referenced in this structure. ")
  
    public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public VimSoftwareImage vimSoftwareImageId(String vimSoftwareImageId) {
    this.vimSoftwareImageId = vimSoftwareImageId;
    return this;
  }

  /**
   * Identifier of the software image in the resource management layer (i.e. VIM).
   * @return vimSoftwareImageId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the software image in the resource management layer (i.e. VIM).")
      @NotNull

    public String getVimSoftwareImageId() {
    return vimSoftwareImageId;
  }

  public void setVimSoftwareImageId(String vimSoftwareImageId) {
    this.vimSoftwareImageId = vimSoftwareImageId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VimSoftwareImage vimSoftwareImage = (VimSoftwareImage) o;
    return Objects.equals(this.appDSoftwareImageId, vimSoftwareImage.appDSoftwareImageId) &&
        Objects.equals(this.vimConnectionId, vimSoftwareImage.vimConnectionId) &&
        Objects.equals(this.vimSoftwareImageId, vimSoftwareImage.vimSoftwareImageId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appDSoftwareImageId, vimConnectionId, vimSoftwareImageId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VimSoftwareImage {\n");
    
    sb.append("    appDSoftwareImageId: ").append(toIndentedString(appDSoftwareImageId)).append("\n");
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    vimSoftwareImageId: ").append(toIndentedString(vimSoftwareImageId)).append("\n");
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
