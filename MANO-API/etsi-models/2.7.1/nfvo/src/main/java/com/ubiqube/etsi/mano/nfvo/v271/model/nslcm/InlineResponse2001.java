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

package com.ubiqube.etsi.mano.nfvo.v271.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v271.model.nslcm.NsLcmOpOcc;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse2001
 */
@Validated
public class InlineResponse2001   {
  @JsonProperty("NsLcmOpOcc")
  private NsLcmOpOcc nsLcmOpOcc = null;

  public InlineResponse2001 nsLcmOpOcc(NsLcmOpOcc nsLcmOpOcc) {
    this.nsLcmOpOcc = nsLcmOpOcc;
    return this;
  }

  /**
   * Get nsLcmOpOcc
   * @return nsLcmOpOcc
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public NsLcmOpOcc getNsLcmOpOcc() {
    return nsLcmOpOcc;
  }

  public void setNsLcmOpOcc(NsLcmOpOcc nsLcmOpOcc) {
    this.nsLcmOpOcc = nsLcmOpOcc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse2001 inlineResponse2001 = (InlineResponse2001) o;
    return Objects.equals(this.nsLcmOpOcc, inlineResponse2001.nsLcmOpOcc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nsLcmOpOcc);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2001 {\n");
    
    sb.append("    nsLcmOpOcc: ").append(toIndentedString(nsLcmOpOcc)).append("\n");
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
