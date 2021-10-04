package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoServiceInterfaceApiEndpoint;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoServiceInterfaceInterfaceState;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoServiceInterfaceSupportedOperations;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ServerInterfaceSecurityInfo;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an individual NFV-MANO service interface produced by  an NFV-MANO functional entity.  
 */
@ApiModel(description = "This type represents an individual NFV-MANO service interface produced by  an NFV-MANO functional entity.  ")
@Validated
public class ManoServiceInterface   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("standardVersion")
  private String standardVersion = null;

  @JsonProperty("providerSpecificApiVersion")
  private String providerSpecificApiVersion = null;

  @JsonProperty("apiVersion")
  private String apiVersion = null;

  @JsonProperty("apiEndpoint")
  private ManoServiceInterfaceApiEndpoint apiEndpoint = null;

  @JsonProperty("maxConcurrentIntOpNumber")
  private Integer maxConcurrentIntOpNumber = null;

  @JsonProperty("supportedOperations")
  @Valid
  private List<ManoServiceInterfaceSupportedOperations> supportedOperations = new ArrayList<>();

  @JsonProperty("interfaceState")
  private ManoServiceInterfaceInterfaceState interfaceState = null;

  @JsonProperty("securityInfo")
  private ServerInterfaceSecurityInfo securityInfo = null;

  @JsonProperty("metadata")
  private Map<String, String> metadata = null;

  public ManoServiceInterface id(String id) {
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

  public ManoServiceInterface name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name of the NFV-MANO functional entity interface. This attribute can be modified with the PATCH method. 
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Human-readable name of the NFV-MANO functional entity interface. This attribute can be modified with the PATCH method. ")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ManoServiceInterface type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Type of the NFV-MANO service interface produced by the NFV-MANO functional  entity. Valid values are defined in clause 5.6.4.3. 
   * @return type
  **/
  @ApiModelProperty(required = true, value = "Type of the NFV-MANO service interface produced by the NFV-MANO functional  entity. Valid values are defined in clause 5.6.4.3. ")
      @NotNull

    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ManoServiceInterface standardVersion(String standardVersion) {
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

  public ManoServiceInterface providerSpecificApiVersion(String providerSpecificApiVersion) {
    this.providerSpecificApiVersion = providerSpecificApiVersion;
    return this;
  }

  /**
   * Get providerSpecificApiVersion
   * @return providerSpecificApiVersion
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getProviderSpecificApiVersion() {
    return providerSpecificApiVersion;
  }

  public void setProviderSpecificApiVersion(String providerSpecificApiVersion) {
    this.providerSpecificApiVersion = providerSpecificApiVersion;
  }

  public ManoServiceInterface apiVersion(String apiVersion) {
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

  public ManoServiceInterface apiEndpoint(ManoServiceInterfaceApiEndpoint apiEndpoint) {
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
    public ManoServiceInterfaceApiEndpoint getApiEndpoint() {
    return apiEndpoint;
  }

  public void setApiEndpoint(ManoServiceInterfaceApiEndpoint apiEndpoint) {
    this.apiEndpoint = apiEndpoint;
  }

  public ManoServiceInterface maxConcurrentIntOpNumber(Integer maxConcurrentIntOpNumber) {
    this.maxConcurrentIntOpNumber = maxConcurrentIntOpNumber;
    return this;
  }

  /**
   * Maximum number of concurrent operation requests supported on this interface.  NOTE: If this attribute is not present, the value of this parameter  is undefined. Overload is handled by the error handling schemes defined  by the applicable API specification. 
   * @return maxConcurrentIntOpNumber
  **/
  @ApiModelProperty(value = "Maximum number of concurrent operation requests supported on this interface.  NOTE: If this attribute is not present, the value of this parameter  is undefined. Overload is handled by the error handling schemes defined  by the applicable API specification. ")
  
    public Integer getMaxConcurrentIntOpNumber() {
    return maxConcurrentIntOpNumber;
  }

  public void setMaxConcurrentIntOpNumber(Integer maxConcurrentIntOpNumber) {
    this.maxConcurrentIntOpNumber = maxConcurrentIntOpNumber;
  }

  public ManoServiceInterface supportedOperations(List<ManoServiceInterfaceSupportedOperations> supportedOperations) {
    this.supportedOperations = supportedOperations;
    return this;
  }

  public ManoServiceInterface addSupportedOperationsItem(ManoServiceInterfaceSupportedOperations supportedOperationsItem) {
    this.supportedOperations.add(supportedOperationsItem);
    return this;
  }

  /**
   * Information about supported operations of this interface. 
   * @return supportedOperations
  **/
  @ApiModelProperty(required = true, value = "Information about supported operations of this interface. ")
      @NotNull
    @Valid
  @Size(min=1)   public List<ManoServiceInterfaceSupportedOperations> getSupportedOperations() {
    return supportedOperations;
  }

  public void setSupportedOperations(List<ManoServiceInterfaceSupportedOperations> supportedOperations) {
    this.supportedOperations = supportedOperations;
  }

  public ManoServiceInterface interfaceState(ManoServiceInterfaceInterfaceState interfaceState) {
    this.interfaceState = interfaceState;
    return this;
  }

  /**
   * Get interfaceState
   * @return interfaceState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ManoServiceInterfaceInterfaceState getInterfaceState() {
    return interfaceState;
  }

  public void setInterfaceState(ManoServiceInterfaceInterfaceState interfaceState) {
    this.interfaceState = interfaceState;
  }

  public ManoServiceInterface securityInfo(ServerInterfaceSecurityInfo securityInfo) {
    this.securityInfo = securityInfo;
    return this;
  }

  /**
   * Get securityInfo
   * @return securityInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public ServerInterfaceSecurityInfo getSecurityInfo() {
    return securityInfo;
  }

  public void setSecurityInfo(ServerInterfaceSecurityInfo securityInfo) {
    this.securityInfo = securityInfo;
  }

  public ManoServiceInterface metadata(Map<String, String> metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManoServiceInterface manoServiceInterface = (ManoServiceInterface) o;
    return Objects.equals(this.id, manoServiceInterface.id) &&
        Objects.equals(this.name, manoServiceInterface.name) &&
        Objects.equals(this.type, manoServiceInterface.type) &&
        Objects.equals(this.standardVersion, manoServiceInterface.standardVersion) &&
        Objects.equals(this.providerSpecificApiVersion, manoServiceInterface.providerSpecificApiVersion) &&
        Objects.equals(this.apiVersion, manoServiceInterface.apiVersion) &&
        Objects.equals(this.apiEndpoint, manoServiceInterface.apiEndpoint) &&
        Objects.equals(this.maxConcurrentIntOpNumber, manoServiceInterface.maxConcurrentIntOpNumber) &&
        Objects.equals(this.supportedOperations, manoServiceInterface.supportedOperations) &&
        Objects.equals(this.interfaceState, manoServiceInterface.interfaceState) &&
        Objects.equals(this.securityInfo, manoServiceInterface.securityInfo) &&
        Objects.equals(this.metadata, manoServiceInterface.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type, standardVersion, providerSpecificApiVersion, apiVersion, apiEndpoint, maxConcurrentIntOpNumber, supportedOperations, interfaceState, securityInfo, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoServiceInterface {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    standardVersion: ").append(toIndentedString(standardVersion)).append("\n");
    sb.append("    providerSpecificApiVersion: ").append(toIndentedString(providerSpecificApiVersion)).append("\n");
    sb.append("    apiVersion: ").append(toIndentedString(apiVersion)).append("\n");
    sb.append("    apiEndpoint: ").append(toIndentedString(apiEndpoint)).append("\n");
    sb.append("    maxConcurrentIntOpNumber: ").append(toIndentedString(maxConcurrentIntOpNumber)).append("\n");
    sb.append("    supportedOperations: ").append(toIndentedString(supportedOperations)).append("\n");
    sb.append("    interfaceState: ").append(toIndentedString(interfaceState)).append("\n");
    sb.append("    securityInfo: ").append(toIndentedString(securityInfo)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
