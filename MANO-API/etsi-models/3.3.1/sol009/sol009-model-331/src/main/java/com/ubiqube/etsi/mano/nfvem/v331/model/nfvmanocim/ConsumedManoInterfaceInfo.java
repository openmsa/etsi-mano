package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ClientInterfaceSecurityInfo;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ConsumedManoInterfaceInfoApiEndpoint;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an interface consumed by the producer NFV MANO  functional entity from another peer functional entity.  
 */
@ApiModel(description = "This type represents an interface consumed by the producer NFV MANO  functional entity from another peer functional entity.  ")
@Validated
public class ConsumedManoInterfaceInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("standardVersion")
  private String standardVersion = null;

  @JsonProperty("apiVersion")
  private String apiVersion = null;

  @JsonProperty("apiEndpoint")
  private ConsumedManoInterfaceInfoApiEndpoint apiEndpoint = null;

  @JsonProperty("securityInfo")
  private ClientInterfaceSecurityInfo securityInfo = null;

  public ConsumedManoInterfaceInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ConsumedManoInterfaceInfo name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name of the NFV-MANO interface. 
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Human-readable name of the NFV-MANO interface. ")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ConsumedManoInterfaceInfo type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Type of the NFV-MANO service interface consumed by the NFV-MANO  functional entity. Valid values are defined in clause 5.6.4.3. 
   * @return type
  **/
  @ApiModelProperty(required = true, value = "Type of the NFV-MANO service interface consumed by the NFV-MANO  functional entity. Valid values are defined in clause 5.6.4.3. ")
      @NotNull

    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ConsumedManoInterfaceInfo standardVersion(String standardVersion) {
    this.standardVersion = standardVersion;
    return this;
  }

  /**
   * Get standardVersion
   * @return standardVersion
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getStandardVersion() {
    return standardVersion;
  }

  public void setStandardVersion(String standardVersion) {
    this.standardVersion = standardVersion;
  }

  public ConsumedManoInterfaceInfo apiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
    return this;
  }

  /**
   * Get apiVersion
   * @return apiVersion
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getApiVersion() {
    return apiVersion;
  }

  public void setApiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
  }

  public ConsumedManoInterfaceInfo apiEndpoint(ConsumedManoInterfaceInfoApiEndpoint apiEndpoint) {
    this.apiEndpoint = apiEndpoint;
    return this;
  }

  /**
   * Get apiEndpoint
   * @return apiEndpoint
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ConsumedManoInterfaceInfoApiEndpoint getApiEndpoint() {
    return apiEndpoint;
  }

  public void setApiEndpoint(ConsumedManoInterfaceInfoApiEndpoint apiEndpoint) {
    this.apiEndpoint = apiEndpoint;
  }

  public ConsumedManoInterfaceInfo securityInfo(ClientInterfaceSecurityInfo securityInfo) {
    this.securityInfo = securityInfo;
    return this;
  }

  /**
   * Get securityInfo
   * @return securityInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ClientInterfaceSecurityInfo getSecurityInfo() {
    return securityInfo;
  }

  public void setSecurityInfo(ClientInterfaceSecurityInfo securityInfo) {
    this.securityInfo = securityInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConsumedManoInterfaceInfo consumedManoInterfaceInfo = (ConsumedManoInterfaceInfo) o;
    return Objects.equals(this.id, consumedManoInterfaceInfo.id) &&
        Objects.equals(this.name, consumedManoInterfaceInfo.name) &&
        Objects.equals(this.type, consumedManoInterfaceInfo.type) &&
        Objects.equals(this.standardVersion, consumedManoInterfaceInfo.standardVersion) &&
        Objects.equals(this.apiVersion, consumedManoInterfaceInfo.apiVersion) &&
        Objects.equals(this.apiEndpoint, consumedManoInterfaceInfo.apiEndpoint) &&
        Objects.equals(this.securityInfo, consumedManoInterfaceInfo.securityInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type, standardVersion, apiVersion, apiEndpoint, securityInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConsumedManoInterfaceInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    standardVersion: ").append(toIndentedString(standardVersion)).append("\n");
    sb.append("    apiVersion: ").append(toIndentedString(apiVersion)).append("\n");
    sb.append("    apiEndpoint: ").append(toIndentedString(apiEndpoint)).append("\n");
    sb.append("    securityInfo: ").append(toIndentedString(securityInfo)).append("\n");
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
