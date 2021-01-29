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
package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreateAppInstanceRequest
 */
@Validated
public class CreateAppInstanceRequest   {
  @JsonProperty("appDId")
  private String appDId = null;

  @JsonProperty("appInstanceDescription")
  private String appInstanceDescription = null;

  @JsonProperty("appInstanceName")
  private String appInstanceName = null;

  public CreateAppInstanceRequest appDId(String appDId) {
    this.appDId = appDId;
    return this;
  }

  /**
   * The application descriptor identifier. It is managed by the application provider to identify the application descriptor in a globally unique way.
   * @return appDId
  **/
  @ApiModelProperty(required = true, value = "The application descriptor identifier. It is managed by the application provider to identify the application descriptor in a globally unique way.")
      @NotNull

    public String getAppDId() {
    return appDId;
  }

  public void setAppDId(String appDId) {
    this.appDId = appDId;
  }

  public CreateAppInstanceRequest appInstanceDescription(String appInstanceDescription) {
    this.appInstanceDescription = appInstanceDescription;
    return this;
  }

  /**
   * Human-readable description of the application instance to be created.
   * @return appInstanceDescription
  **/
  @ApiModelProperty(value = "Human-readable description of the application instance to be created.")
  
    public String getAppInstanceDescription() {
    return appInstanceDescription;
  }

  public void setAppInstanceDescription(String appInstanceDescription) {
    this.appInstanceDescription = appInstanceDescription;
  }

  public CreateAppInstanceRequest appInstanceName(String appInstanceName) {
    this.appInstanceName = appInstanceName;
    return this;
  }

  /**
   * Human-readable name of the application instance to be created.
   * @return appInstanceName
  **/
  @ApiModelProperty(value = "Human-readable name of the application instance to be created.")
  
    public String getAppInstanceName() {
    return appInstanceName;
  }

  public void setAppInstanceName(String appInstanceName) {
    this.appInstanceName = appInstanceName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateAppInstanceRequest createAppInstanceRequest = (CreateAppInstanceRequest) o;
    return Objects.equals(this.appDId, createAppInstanceRequest.appDId) &&
        Objects.equals(this.appInstanceDescription, createAppInstanceRequest.appInstanceDescription) &&
        Objects.equals(this.appInstanceName, createAppInstanceRequest.appInstanceName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(appDId, appInstanceDescription, appInstanceName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateAppInstanceRequest {\n");
    
    sb.append("    appDId: ").append(toIndentedString(appDId)).append("\n");
    sb.append("    appInstanceDescription: ").append(toIndentedString(appInstanceDescription)).append("\n");
    sb.append("    appInstanceName: ").append(toIndentedString(appInstanceName)).append("\n");
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
