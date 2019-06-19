package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.List;

/**
  * This type represents an externally provided link port or network address information per instance of an external connection point. In case a link port is provided, the VNFM shall use that link port when connecting the external CP to the external VL. In a link port is not provided, the VNFM shall create a link port on the external VL, and use that link port to connect the external CP to the external VL. 
 **/
@ApiModel(description="This type represents an externally provided link port or network address information per instance of an external connection point. In case a link port is provided, the VNFM shall use that link port when connecting the external CP to the external VL. In a link port is not provided, the VNFM shall create a link port on the external VL, and use that link port to connect the external CP to the external VL. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig  {
  
  @ApiModelProperty(value = "An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. ")
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
  **/
  private String cpInstanceId = null;

  @ApiModelProperty(value = "An identifier with the intention of being globally unique. ")
 /**
   * An identifier with the intention of being globally unique. 
  **/
  private String linkPortId = null;

  @ApiModelProperty(value = "Parameters for configuring the network protocols on the link port that connects the CP to a VL.  The following conditions apply to the attributes \"linkPortId\" and \"cpProtocolData\":  * The \"linkPortId\" and \"cpProtocolData\" attributes shall both be   absent for the deletion of an existing external CP instance   addressed by cpInstanceId.  * At least one of these attributes shall be present for a   to-be-created external CP instance or an existing external   CP instance. * If the \"linkPortId\" attribute is absent, the VNFM shall create a   link port. * If the \"cpProtocolData\" attribute is absent, the \"linkPortId\"   attribute shall be provided referencing a pre-created link port,   and the VNFM can use means outside the scope of the present   document to obtain the pre-configured address information for the   connection point from the resource representing the link port. * If both \"cpProtocolData\" and \"linkportId\" are provided, the API   consumer shall ensure that the cpProtocolData can be used with the   pre-created link port referenced by \"linkPortId\". ")
  @Valid
 /**
   * Parameters for configuring the network protocols on the link port that connects the CP to a VL.  The following conditions apply to the attributes \"linkPortId\" and \"cpProtocolData\":  * The \"linkPortId\" and \"cpProtocolData\" attributes shall both be   absent for the deletion of an existing external CP instance   addressed by cpInstanceId.  * At least one of these attributes shall be present for a   to-be-created external CP instance or an existing external   CP instance. * If the \"linkPortId\" attribute is absent, the VNFM shall create a   link port. * If the \"cpProtocolData\" attribute is absent, the \"linkPortId\"   attribute shall be provided referencing a pre-created link port,   and the VNFM can use means outside the scope of the present   document to obtain the pre-configured address information for the   connection point from the resource representing the link port. * If both \"cpProtocolData\" and \"linkportId\" are provided, the API   consumer shall ensure that the cpProtocolData can be used with the   pre-created link port referenced by \"linkPortId\". 
  **/
  private List<NsInstancesNsInstanceCpInfoCpProtocolData> cpProtocolData = null;
 /**
   * An identifier that is unique for the respective type within a VNF instance, but may not be globally unique. 
   * @return cpInstanceId
  **/
  @JsonProperty("cpInstanceId")
  public String getCpInstanceId() {
    return cpInstanceId;
  }

  public void setCpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig cpInstanceId(String cpInstanceId) {
    this.cpInstanceId = cpInstanceId;
    return this;
  }

 /**
   * An identifier with the intention of being globally unique. 
   * @return linkPortId
  **/
  @JsonProperty("linkPortId")
  public String getLinkPortId() {
    return linkPortId;
  }

  public void setLinkPortId(String linkPortId) {
    this.linkPortId = linkPortId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig linkPortId(String linkPortId) {
    this.linkPortId = linkPortId;
    return this;
  }

 /**
   * Parameters for configuring the network protocols on the link port that connects the CP to a VL.  The following conditions apply to the attributes \&quot;linkPortId\&quot; and \&quot;cpProtocolData\&quot;:  * The \&quot;linkPortId\&quot; and \&quot;cpProtocolData\&quot; attributes shall both be   absent for the deletion of an existing external CP instance   addressed by cpInstanceId.  * At least one of these attributes shall be present for a   to-be-created external CP instance or an existing external   CP instance. * If the \&quot;linkPortId\&quot; attribute is absent, the VNFM shall create a   link port. * If the \&quot;cpProtocolData\&quot; attribute is absent, the \&quot;linkPortId\&quot;   attribute shall be provided referencing a pre-created link port,   and the VNFM can use means outside the scope of the present   document to obtain the pre-configured address information for the   connection point from the resource representing the link port. * If both \&quot;cpProtocolData\&quot; and \&quot;linkportId\&quot; are provided, the API   consumer shall ensure that the cpProtocolData can be used with the   pre-created link port referenced by \&quot;linkPortId\&quot;. 
   * @return cpProtocolData
  **/
  @JsonProperty("cpProtocolData")
  public List<NsInstancesNsInstanceCpInfoCpProtocolData> getCpProtocolData() {
    return cpProtocolData;
  }

  public void setCpProtocolData(List<NsInstancesNsInstanceCpInfoCpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig cpProtocolData(List<NsInstancesNsInstanceCpInfoCpProtocolData> cpProtocolData) {
    this.cpProtocolData = cpProtocolData;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig addCpProtocolDataItem(NsInstancesNsInstanceCpInfoCpProtocolData cpProtocolDataItem) {
    this.cpProtocolData.add(cpProtocolDataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequestCpConfig {\n");
    
    sb.append("    cpInstanceId: ").append(toIndentedString(cpInstanceId)).append("\n");
    sb.append("    linkPortId: ").append(toIndentedString(linkPortId)).append("\n");
    sb.append("    cpProtocolData: ").append(toIndentedString(cpProtocolData)).append("\n");
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

