package com.ubiqube.etsi.mano.model.lcmgrant.sol003;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * If the VIM requires the use of virtual compute resource flavours during compute resource instantiation, it is assumed that such flavours are selected or created by the NFVO based on the information in the virtual compute descriptor defined in the VNFD. This type defines the mapping between a virtual compute descriptor in the VNFD and the corresponding compute resource flavour managed by the NFVO in the VIM. 
 */
@ApiModel(description = "If the VIM requires the use of virtual compute resource flavours during compute resource instantiation, it is assumed that such flavours are selected or created by the NFVO based on the information in the virtual compute descriptor defined in the VNFD. This type defines the mapping between a virtual compute descriptor in the VNFD and the corresponding compute resource flavour managed by the NFVO in the VIM. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-08-30T10:47:24.034+02:00")

public class VimComputeResourceFlavour   {
  @JsonProperty("vimConnectionId")
  private String vimConnectionId = null;

  @JsonProperty("resourceProviderId")
  private String resourceProviderId = null;

  @JsonProperty("vnfdVirtualComputeDescId")
  private String vnfdVirtualComputeDescId = null;

  @JsonProperty("vimFlavourId")
  private String vimFlavourId = null;

  public VimComputeResourceFlavour vimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
    return this;
  }

  /**
   * Identifier of the VIM connection to access the flavour referenced in this structure. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable. 
   * @return vimConnectionId
  **/
  @ApiModelProperty(value = "Identifier of the VIM connection to access the flavour referenced in this structure. The applicable \"VimConnectionInfo\" structure, which is referenced by vimConnectionId, can be obtained from the \"vimConnectionInfo\" attribute of the \"VnfInstance\" structure. This attribute shall only be supported and present if VNF-related resource management in direct mode is applicable. ")


  public String getVimConnectionId() {
    return vimConnectionId;
  }

  public void setVimConnectionId(String vimConnectionId) {
    this.vimConnectionId = vimConnectionId;
  }

  public VimComputeResourceFlavour resourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
    return this;
  }

  /**
   * Identifies the entity responsible for the management of the virtualised resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document. 
   * @return resourceProviderId
  **/
  @ApiModelProperty(value = "Identifies the entity responsible for the management of the virtualised resource. This attribute shall only be supported and present if VNF-related resource management in indirect mode is applicable. The identification scheme is outside the scope of the present document. ")


  public String getResourceProviderId() {
    return resourceProviderId;
  }

  public void setResourceProviderId(String resourceProviderId) {
    this.resourceProviderId = resourceProviderId;
  }

  public VimComputeResourceFlavour vnfdVirtualComputeDescId(String vnfdVirtualComputeDescId) {
    this.vnfdVirtualComputeDescId = vnfdVirtualComputeDescId;
    return this;
  }

  /**
   * Identifier which references the virtual compute descriptor in the VNFD that maps to this flavour. 
   * @return vnfdVirtualComputeDescId
  **/
  @ApiModelProperty(required = true, value = "Identifier which references the virtual compute descriptor in the VNFD that maps to this flavour. ")
  @NotNull


  public String getVnfdVirtualComputeDescId() {
    return vnfdVirtualComputeDescId;
  }

  public void setVnfdVirtualComputeDescId(String vnfdVirtualComputeDescId) {
    this.vnfdVirtualComputeDescId = vnfdVirtualComputeDescId;
  }

  public VimComputeResourceFlavour vimFlavourId(String vimFlavourId) {
    this.vimFlavourId = vimFlavourId;
    return this;
  }

  /**
   * Identifier of the compute resource flavour in the resource management layer (i.e. VIM). 
   * @return vimFlavourId
  **/
  @ApiModelProperty(required = true, value = "Identifier of the compute resource flavour in the resource management layer (i.e. VIM). ")
  @NotNull


  public String getVimFlavourId() {
    return vimFlavourId;
  }

  public void setVimFlavourId(String vimFlavourId) {
    this.vimFlavourId = vimFlavourId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VimComputeResourceFlavour vimComputeResourceFlavour = (VimComputeResourceFlavour) o;
    return Objects.equals(this.vimConnectionId, vimComputeResourceFlavour.vimConnectionId) &&
        Objects.equals(this.resourceProviderId, vimComputeResourceFlavour.resourceProviderId) &&
        Objects.equals(this.vnfdVirtualComputeDescId, vimComputeResourceFlavour.vnfdVirtualComputeDescId) &&
        Objects.equals(this.vimFlavourId, vimComputeResourceFlavour.vimFlavourId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vimConnectionId, resourceProviderId, vnfdVirtualComputeDescId, vimFlavourId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VimComputeResourceFlavour {\n");
    
    sb.append("    vimConnectionId: ").append(toIndentedString(vimConnectionId)).append("\n");
    sb.append("    resourceProviderId: ").append(toIndentedString(resourceProviderId)).append("\n");
    sb.append("    vnfdVirtualComputeDescId: ").append(toIndentedString(vnfdVirtualComputeDescId)).append("\n");
    sb.append("    vimFlavourId: ").append(toIndentedString(vimFlavourId)).append("\n");
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

