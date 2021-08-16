package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ConsumedManoInterfaceInfo;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.CreatePeerEntityRequestPeerEntityState;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.PeerEntityEnumType;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents request parameters for the creation of a new peer entity resource.  
 */
@ApiModel(description = "This type represents request parameters for the creation of a new peer entity resource.  ")
@Validated
public class CreatePeerEntityRequest   {
  @JsonProperty("peerEntityId")
  private String peerEntityId = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("type")
  private PeerEntityEnumType type = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("consumedManoInterfaces")
  @Valid
  private List<ConsumedManoInterfaceInfo> consumedManoInterfaces = null;

  @JsonProperty("peerEntityState")
  private CreatePeerEntityRequestPeerEntityState peerEntityState = null;

  public CreatePeerEntityRequest peerEntityId(String peerEntityId) {
    this.peerEntityId = peerEntityId;
    return this;
  }

  /**
   * Get peerEntityId
   * @return peerEntityId
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getPeerEntityId() {
    return peerEntityId;
  }

  public void setPeerEntityId(String peerEntityId) {
    this.peerEntityId = peerEntityId;
  }

  public CreatePeerEntityRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name of the peer functional entity. 
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Human-readable name of the peer functional entity. ")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CreatePeerEntityRequest type(PeerEntityEnumType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public PeerEntityEnumType getType() {
    return type;
  }

  public void setType(PeerEntityEnumType type) {
    this.type = type;
  }

  public CreatePeerEntityRequest description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Human-readable description of the peer functional entity. 
   * @return description
  **/
  @ApiModelProperty(value = "Human-readable description of the peer functional entity. ")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CreatePeerEntityRequest consumedManoInterfaces(List<ConsumedManoInterfaceInfo> consumedManoInterfaces) {
    this.consumedManoInterfaces = consumedManoInterfaces;
    return this;
  }

  public CreatePeerEntityRequest addConsumedManoInterfacesItem(ConsumedManoInterfaceInfo consumedManoInterfacesItem) {
    if (this.consumedManoInterfaces == null) {
      this.consumedManoInterfaces = new ArrayList<>();
    }
    this.consumedManoInterfaces.add(consumedManoInterfacesItem);
    return this;
  }

  /**
   * Information of the interface consumed by the NFV-MANO functional entity  from the peer functional entity. 
   * @return consumedManoInterfaces
  **/
  @ApiModelProperty(value = "Information of the interface consumed by the NFV-MANO functional entity  from the peer functional entity. ")
      @Valid
    public List<ConsumedManoInterfaceInfo> getConsumedManoInterfaces() {
    return consumedManoInterfaces;
  }

  public void setConsumedManoInterfaces(List<ConsumedManoInterfaceInfo> consumedManoInterfaces) {
    this.consumedManoInterfaces = consumedManoInterfaces;
  }

  public CreatePeerEntityRequest peerEntityState(CreatePeerEntityRequestPeerEntityState peerEntityState) {
    this.peerEntityState = peerEntityState;
    return this;
  }

  /**
   * Get peerEntityState
   * @return peerEntityState
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public CreatePeerEntityRequestPeerEntityState getPeerEntityState() {
    return peerEntityState;
  }

  public void setPeerEntityState(CreatePeerEntityRequestPeerEntityState peerEntityState) {
    this.peerEntityState = peerEntityState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreatePeerEntityRequest createPeerEntityRequest = (CreatePeerEntityRequest) o;
    return Objects.equals(this.peerEntityId, createPeerEntityRequest.peerEntityId) &&
        Objects.equals(this.name, createPeerEntityRequest.name) &&
        Objects.equals(this.type, createPeerEntityRequest.type) &&
        Objects.equals(this.description, createPeerEntityRequest.description) &&
        Objects.equals(this.consumedManoInterfaces, createPeerEntityRequest.consumedManoInterfaces) &&
        Objects.equals(this.peerEntityState, createPeerEntityRequest.peerEntityState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(peerEntityId, name, type, description, consumedManoInterfaces, peerEntityState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreatePeerEntityRequest {\n");
    
    sb.append("    peerEntityId: ").append(toIndentedString(peerEntityId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    consumedManoInterfaces: ").append(toIndentedString(consumedManoInterfaces)).append("\n");
    sb.append("    peerEntityState: ").append(toIndentedString(peerEntityState)).append("\n");
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
