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

package com.ubiqube.etsi.mano.nfvo.v261.model.nsperfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


/**
  * Parameters for authentication/authorization using BASIC. Shall be present if authType is \"BASIC\" and the contained information has not been provisioned out of band. Shall be absent otherwise. 
 **/
@Schema(description="Parameters for authentication/authorization using BASIC. Shall be present if authType is \"BASIC\" and the contained information has not been provisioned out of band. Shall be absent otherwise. ")
public class SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic  {
  
  @Schema(description = "Username to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. ")
 /**
   * Username to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. 
  **/
  private String userName = null;

  @Schema(description = "Password to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. ")
 /**
   * Password to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. 
  **/
  private String password = null;
 /**
   * Username to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. 
   * @return userName
  **/
  @JsonProperty("userName")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic userName(String userName) {
    this.userName = userName;
    return this;
  }

 /**
   * Password to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. 
   * @return password
  **/
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic password(String password) {
    this.password = password;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsPmSubscriptionRequestAuthenticationParamsBasic {\n");
    
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

