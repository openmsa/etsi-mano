package com.ubiqube.etsi.mano.em.v331.model.vnfind;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Parameters for authentication/authorization using BASIC. Shall be present if authType is \&quot;BASIC\&quot; and the contained information has not been provisioned out of band. Shall be absent otherwise. 
 */
@Schema(description = "Parameters for authentication/authorization using BASIC. Shall be present if authType is \"BASIC\" and the contained information has not been provisioned out of band. Shall be absent otherwise. ")
@Validated


public class SubscriptionAuthenticationParamsBasic   {
  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("password")
  private String password = null;

  public SubscriptionAuthenticationParamsBasic userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Username to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. 
   * @return userName
   **/
  @Schema(description = "Username to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. ")
  
    public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public SubscriptionAuthenticationParamsBasic password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Password to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. 
   * @return password
   **/
  @Schema(description = "Password to be used in HTTP Basic authentication. Shall be present if it has not been provisioned out of band. ")
  
    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionAuthenticationParamsBasic subscriptionAuthenticationParamsBasic = (SubscriptionAuthenticationParamsBasic) o;
    return Objects.equals(this.userName, subscriptionAuthenticationParamsBasic.userName) &&
        Objects.equals(this.password, subscriptionAuthenticationParamsBasic.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionAuthenticationParamsBasic {\n");
    
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
