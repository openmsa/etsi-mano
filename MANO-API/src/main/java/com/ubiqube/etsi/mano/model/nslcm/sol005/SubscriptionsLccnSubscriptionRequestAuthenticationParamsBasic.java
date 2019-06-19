package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
  * Parameters for authentication/authorization using BASIC. Shall be present if authType is \"BASIC\" and the contained information has not been provisioned out of band. Shall be absent otherwise. 
 **/
@ApiModel(description="Parameters for authentication/authorization using BASIC. Shall be present if authType is \"BASIC\" and the contained information has not been provisioned out of band. Shall be absent otherwise. ")
public class SubscriptionsLccnSubscriptionRequestAuthenticationParamsBasic  {
  
  @ApiModelProperty(value = "Username to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. ")
 /**
   * Username to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. 
  **/
  private String userName = null;

  @ApiModelProperty(value = "Password to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. ")
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

  public SubscriptionsLccnSubscriptionRequestAuthenticationParamsBasic userName(String userName) {
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

  public SubscriptionsLccnSubscriptionRequestAuthenticationParamsBasic password(String password) {
    this.password = password;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionsLccnSubscriptionRequestAuthenticationParamsBasic {\n");
    
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

