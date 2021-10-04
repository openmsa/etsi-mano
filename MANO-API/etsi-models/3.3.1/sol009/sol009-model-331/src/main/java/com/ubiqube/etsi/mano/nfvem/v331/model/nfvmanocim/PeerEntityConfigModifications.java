package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.AdministrativeStateEnumType;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ConsumedManoInterfaceInfo;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.OperationalStateEnumType;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents attribute modifications that were performed on an  \&quot;Individual peer entity\&quot; resource. The attributes that can be included  consist of those requested to be modified explicitly in the  \&quot;PeerEntityConfigModificationRequest\&quot; data structure. If applicable,  additional attributes of the \&quot;PeerEntity\&quot; data structure that were  modified implicitly shall also be provided. 
 */
@ApiModel(description = "This type represents attribute modifications that were performed on an  \"Individual peer entity\" resource. The attributes that can be included  consist of those requested to be modified explicitly in the  \"PeerEntityConfigModificationRequest\" data structure. If applicable,  additional attributes of the \"PeerEntity\" data structure that were  modified implicitly shall also be provided. ")
@Validated
public class PeerEntityConfigModifications   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("consumedManoInterfaces")
  @Valid
  private List<ConsumedManoInterfaceInfo> consumedManoInterfaces = null;

  @JsonProperty("consumedManoInterfaceDeleteIds")
  @Valid
  private List<String> consumedManoInterfaceDeleteIds = null;

  @JsonProperty("operationalState")
  private OperationalStateEnumType operationalState = null;

  @JsonProperty("administrativeState")
  private AdministrativeStateEnumType administrativeState = null;

  public PeerEntityConfigModifications name(String name) {
    this.name = name;
    return this;
  }

  /**
   * If present, this attribute signals modifications of the \"name\" attribute  in \"PeerEntity\", as defined in clause 5.6.2.15. 
   * @return name
  **/
  @ApiModelProperty(value = "If present, this attribute signals modifications of the \"name\" attribute  in \"PeerEntity\", as defined in clause 5.6.2.15. ")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PeerEntityConfigModifications description(String description) {
    this.description = description;
    return this;
  }

  /**
   * If present, this attribute signals modifications of the \"description\"  attribute in \"PeerEntity\", as defined in clause 5.6.2.15. 
   * @return description
  **/
  @ApiModelProperty(value = "If present, this attribute signals modifications of the \"description\"  attribute in \"PeerEntity\", as defined in clause 5.6.2.15. ")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PeerEntityConfigModifications consumedManoInterfaces(List<ConsumedManoInterfaceInfo> consumedManoInterfaces) {
    this.consumedManoInterfaces = consumedManoInterfaces;
    return this;
  }

  public PeerEntityConfigModifications addConsumedManoInterfacesItem(ConsumedManoInterfaceInfo consumedManoInterfacesItem) {
    if (this.consumedManoInterfaces == null) {
      this.consumedManoInterfaces = new ArrayList<>();
    }
    this.consumedManoInterfaces.add(consumedManoInterfacesItem);
    return this;
  }

  /**
   * If present, this attribute signals modifications of certain entries in  \"consumedManoInterfaces\" attribute in \"PeerEntity\", as defined in  clause 5.6.2.15.  NOTE: Due to the security sensitive information contained within the  attribute (refer to \"securityInfo\" within the \"ConsumedManoInterfaceInfo\"),  based on access control policies, the API consumer might have read only,  write only, read/write, or no access at all to the attribute’s value.  In case the API consumer is not allowed to read the value of the security  sensitive attribute, the attribute shall be omitted when the information  is to be provided in a response message. 
   * @return consumedManoInterfaces
  **/
  @ApiModelProperty(value = "If present, this attribute signals modifications of certain entries in  \"consumedManoInterfaces\" attribute in \"PeerEntity\", as defined in  clause 5.6.2.15.  NOTE: Due to the security sensitive information contained within the  attribute (refer to \"securityInfo\" within the \"ConsumedManoInterfaceInfo\"),  based on access control policies, the API consumer might have read only,  write only, read/write, or no access at all to the attribute’s value.  In case the API consumer is not allowed to read the value of the security  sensitive attribute, the attribute shall be omitted when the information  is to be provided in a response message. ")
      @Valid
    public List<ConsumedManoInterfaceInfo> getConsumedManoInterfaces() {
    return consumedManoInterfaces;
  }

  public void setConsumedManoInterfaces(List<ConsumedManoInterfaceInfo> consumedManoInterfaces) {
    this.consumedManoInterfaces = consumedManoInterfaces;
  }

  public PeerEntityConfigModifications consumedManoInterfaceDeleteIds(List<String> consumedManoInterfaceDeleteIds) {
    this.consumedManoInterfaceDeleteIds = consumedManoInterfaceDeleteIds;
    return this;
  }

  public PeerEntityConfigModifications addConsumedManoInterfaceDeleteIdsItem(String consumedManoInterfaceDeleteIdsItem) {
    if (this.consumedManoInterfaceDeleteIds == null) {
      this.consumedManoInterfaceDeleteIds = new ArrayList<>();
    }
    this.consumedManoInterfaceDeleteIds.add(consumedManoInterfaceDeleteIdsItem);
    return this;
  }

  /**
   * If present, this attribute signals the deletions of certain entries in the  \"consumedManoInterfaces\" attribute in \"PeerEntity\", as defined in  clause 5.6.2.15. 
   * @return consumedManoInterfaceDeleteIds
  **/
  @ApiModelProperty(value = "If present, this attribute signals the deletions of certain entries in the  \"consumedManoInterfaces\" attribute in \"PeerEntity\", as defined in  clause 5.6.2.15. ")
  
    public List<String> getConsumedManoInterfaceDeleteIds() {
    return consumedManoInterfaceDeleteIds;
  }

  public void setConsumedManoInterfaceDeleteIds(List<String> consumedManoInterfaceDeleteIds) {
    this.consumedManoInterfaceDeleteIds = consumedManoInterfaceDeleteIds;
  }

  public PeerEntityConfigModifications operationalState(OperationalStateEnumType operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  /**
   * Get operationalState
   * @return operationalState
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public OperationalStateEnumType getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(OperationalStateEnumType operationalState) {
    this.operationalState = operationalState;
  }

  public PeerEntityConfigModifications administrativeState(AdministrativeStateEnumType administrativeState) {
    this.administrativeState = administrativeState;
    return this;
  }

  /**
   * Get administrativeState
   * @return administrativeState
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public AdministrativeStateEnumType getAdministrativeState() {
    return administrativeState;
  }

  public void setAdministrativeState(AdministrativeStateEnumType administrativeState) {
    this.administrativeState = administrativeState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeerEntityConfigModifications peerEntityConfigModifications = (PeerEntityConfigModifications) o;
    return Objects.equals(this.name, peerEntityConfigModifications.name) &&
        Objects.equals(this.description, peerEntityConfigModifications.description) &&
        Objects.equals(this.consumedManoInterfaces, peerEntityConfigModifications.consumedManoInterfaces) &&
        Objects.equals(this.consumedManoInterfaceDeleteIds, peerEntityConfigModifications.consumedManoInterfaceDeleteIds) &&
        Objects.equals(this.operationalState, peerEntityConfigModifications.operationalState) &&
        Objects.equals(this.administrativeState, peerEntityConfigModifications.administrativeState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, consumedManoInterfaces, consumedManoInterfaceDeleteIds, operationalState, administrativeState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PeerEntityConfigModifications {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    consumedManoInterfaces: ").append(toIndentedString(consumedManoInterfaces)).append("\n");
    sb.append("    consumedManoInterfaceDeleteIds: ").append(toIndentedString(consumedManoInterfaceDeleteIds)).append("\n");
    sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
    sb.append("    administrativeState: ").append(toIndentedString(administrativeState)).append("\n");
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
