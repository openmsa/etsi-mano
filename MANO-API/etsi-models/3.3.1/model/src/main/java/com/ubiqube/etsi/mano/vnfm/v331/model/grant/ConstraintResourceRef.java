package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type references a resource either by its VIM-level identifier for existing resources, or by the identifier of a \&quot;ResourceDefinition\&quot; structure in the \&quot;GrantRequest\&quot; structure for new resources. 
 */
@Schema(description = "This type references a resource either by its VIM-level identifier for existing resources, or by the identifier of a \"ResourceDefinition\" structure in the \"GrantRequest\" structure for new resources. ")
@Validated


public class ConstraintResourceRef   {
  /**
   * The type of the identifier. Permitted values: * RES_MGMT: Resource-management-level identifier; this identifier is   managed by the VIM in the direct mode of VNF-related resource   management, and is managed by the NFVO in the indirect mode) * GRANT: Reference to the identifier of a \"ResourceDefinition\" structure in the \"GrantRequest\" structure. 
   */
  public enum IdTypeEnum {
    RES_MGMT("RES_MGMT"),
    
    GRANT("GRANT");

    private String value;

    IdTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static IdTypeEnum fromValue(String text) {
      for (IdTypeEnum b : IdTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("idType")
  private IdTypeEnum idType = null;

  @JsonProperty("resourceId")
  private String resourceId = null;

  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  public ConstraintResourceRef idType(IdTypeEnum idType) {
    this.idType = idType;
    return this;
  }

  /**
   * The type of the identifier. Permitted values: * RES_MGMT: Resource-management-level identifier; this identifier is   managed by the VIM in the direct mode of VNF-related resource   management, and is managed by the NFVO in the indirect mode) * GRANT: Reference to the identifier of a \"ResourceDefinition\" structure in the \"GrantRequest\" structure. 
   * @return idType
   **/
  @Schema(required = true, description = "The type of the identifier. Permitted values: * RES_MGMT: Resource-management-level identifier; this identifier is   managed by the VIM in the direct mode of VNF-related resource   management, and is managed by the NFVO in the indirect mode) * GRANT: Reference to the identifier of a \"ResourceDefinition\" structure in the \"GrantRequest\" structure. ")
      @NotNull

    public IdTypeEnum getIdType() {
    return idType;
  }

  public void setIdType(IdTypeEnum idType) {
    this.idType = idType;
  }

  public ConstraintResourceRef resourceId(String resourceId) {
    this.resourceId = resourceId;
    return this;
  }

  /**
   * Get resourceId
   * @return resourceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public ConstraintResourceRef vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Get vimConnectionId
   * @return vimConnectionId
   **/
  @Schema(description = "")
  
    public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public ConstraintResourceRef resourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
    return this;
  }

  /**
   * Get resourceProviderId
   * @return resourceProviderId
   **/
  @Schema(description = "")
  
    public String getResourceProviderId() {
    return resourceProviderId;
  }

  public void setResourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ConstraintResourceRef constraintResourceRef = (ConstraintResourceRef) o;
    return Objects.equals(this.idType, constraintResourceRef.idType) &&
        Objects.equals(this.resourceId, constraintResourceRef.resourceId) &&
        Objects.equals(this.vimConnectionId, constraintResourceRef.vimConnectionId) &&
        Objects.equals(this.resourceProviderId, constraintResourceRef.resourceProviderId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idType, resourceId, vimConnectionId, resourceProviderId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ConstraintResourceRef {\n");
    
    sb.append("    idType: ").append(toIndentedString(idType)).append("\n");
    sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
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
