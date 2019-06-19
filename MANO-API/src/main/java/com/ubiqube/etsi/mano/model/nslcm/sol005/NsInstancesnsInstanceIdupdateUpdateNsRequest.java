package com.ubiqube.etsi.mano.model.nslcm.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.List;

/**
  * This operation supports the update of a NS instance,  It shall comply with the provisions defined in Table 6.5.2.12-1. 
 **/
@ApiModel(description="This operation supports the update of a NS instance,  It shall comply with the provisions defined in Table 6.5.2.12-1. ")
public class NsInstancesnsInstanceIdupdateUpdateNsRequest  {
  

@XmlType(name="UpdateTypeEnum")
@XmlEnum(String.class)
public enum UpdateTypeEnum {

@XmlEnumValue("ADD_VNF") ADD_VNF(String.valueOf("ADD_VNF")), @XmlEnumValue("REMOVE_VNF") REMOVE_VNF(String.valueOf("REMOVE_VNF")), @XmlEnumValue("INSTANTIATE_VNF") INSTANTIATE_VNF(String.valueOf("INSTANTIATE_VNF")), @XmlEnumValue("CHANGE_VNF_DF") CHANGE_VNF_DF(String.valueOf("CHANGE_VNF_DF")), @XmlEnumValue("OPERATE_VNF") OPERATE_VNF(String.valueOf("OPERATE_VNF")), @XmlEnumValue("MODIFY_VNF_INFORMATION") MODIFY_VNF_INFORMATION(String.valueOf("MODIFY_VNF_INFORMATION")), @XmlEnumValue("CHANGE_EXTERNAL_VNF_CONNECTIVITY") CHANGE_EXTERNAL_VNF_CONNECTIVITY(String.valueOf("CHANGE_EXTERNAL_VNF_CONNECTIVITY")), @XmlEnumValue("REMOVE_SAP") REMOVE_SAP(String.valueOf("REMOVE_SAP")), @XmlEnumValue("ADD_NESTED_NS") ADD_NESTED_NS(String.valueOf("ADD_NESTED_NS")), @XmlEnumValue("REMOVE_NESTED_NS") REMOVE_NESTED_NS(String.valueOf("REMOVE_NESTED_NS")), @XmlEnumValue("ASSOC_NEW_NSD_VERSION") ASSOC_NEW_NSD_VERSION(String.valueOf("ASSOC_NEW_NSD_VERSION")), @XmlEnumValue("MOVE_VNF") MOVE_VNF(String.valueOf("MOVE_VNF")), @XmlEnumValue("ADD_VNFFG") ADD_VNFFG(String.valueOf("ADD_VNFFG")), @XmlEnumValue("REMOVE_VNFFG") REMOVE_VNFFG(String.valueOf("REMOVE_VNFFG")), @XmlEnumValue("UPDATE_VNFFG") UPDATE_VNFFG(String.valueOf("UPDATE_VNFFG")), @XmlEnumValue("CHANGE_NS_DF") CHANGE_NS_DF(String.valueOf("CHANGE_NS_DF")), @XmlEnumValue("ADD_PNF") ADD_PNF(String.valueOf("ADD_PNF")), @XmlEnumValue("MODIFY_PNF") MODIFY_PNF(String.valueOf("MODIFY_PNF")), @XmlEnumValue("REMOVE_PNF") REMOVE_PNF(String.valueOf("REMOVE_PNF"));


    private String value;

    UpdateTypeEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static UpdateTypeEnum fromValue(String v) {
        for (UpdateTypeEnum b : UpdateTypeEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "The type of update. It determines also which one of the following parameters is present in the operation. Possible values include: * ADD_VNF: Adding existing VNF instance(s) * REMOVE_VNF: Removing VNF instance(s) * INSTANTIATE_VNF: Instantiating new VNF(s) * CHANGE_VNF_DF: Changing VNF DF * OPERATE_VNF: Changing VNF state, * MODIFY_VNF_INFORMATION: Modifying VNF information and/or the configurable properties of VNF instance(s) * CHANGE_EXTERNAL_VNF_CONNECTIVITY: Changing the external connectivity of VNF instance(s)ADD_SAP: Adding SAP(s) * REMOVE_SAP: Removing SAP(s) * ADD_NESTED_NS: Adding existing NS instance(s) as nested NS(s) * REMOVE_NESTED_NS: Removing existing nested NS instance(s) * ASSOC_NEW_NSD_VERSION: Associating a new NSD version to the NS instance * MOVE_VNF: Moving VNF instance(s) from one origin NS instance to another target NS instance * ADD_VNFFG: Adding VNFFG(s) * REMOVE_VNFFG: Removing VNFFG(s) * UPDATE_VNFFG: Updating VNFFG(s) * CHANGE_NS_DF: Changing NS DF * ADD_PNF: Adding PNF * MODIFY_PNF: Modifying PNF * REMOVE_PNF: Removing PNF ")
 /**
   * The type of update. It determines also which one of the following parameters is present in the operation. Possible values include: * ADD_VNF: Adding existing VNF instance(s) * REMOVE_VNF: Removing VNF instance(s) * INSTANTIATE_VNF: Instantiating new VNF(s) * CHANGE_VNF_DF: Changing VNF DF * OPERATE_VNF: Changing VNF state, * MODIFY_VNF_INFORMATION: Modifying VNF information and/or the configurable properties of VNF instance(s) * CHANGE_EXTERNAL_VNF_CONNECTIVITY: Changing the external connectivity of VNF instance(s)ADD_SAP: Adding SAP(s) * REMOVE_SAP: Removing SAP(s) * ADD_NESTED_NS: Adding existing NS instance(s) as nested NS(s) * REMOVE_NESTED_NS: Removing existing nested NS instance(s) * ASSOC_NEW_NSD_VERSION: Associating a new NSD version to the NS instance * MOVE_VNF: Moving VNF instance(s) from one origin NS instance to another target NS instance * ADD_VNFFG: Adding VNFFG(s) * REMOVE_VNFFG: Removing VNFFG(s) * UPDATE_VNFFG: Updating VNFFG(s) * CHANGE_NS_DF: Changing NS DF * ADD_PNF: Adding PNF * MODIFY_PNF: Modifying PNF * REMOVE_PNF: Removing PNF 
  **/
  private UpdateTypeEnum updateType = null;

  @ApiModelProperty(value = "Identifies an existing VNF instance to be added to the NS instance. It shall be present only if updateType = \"ADD_VNF\".          ")
  @Valid
 /**
   * Identifies an existing VNF instance to be added to the NS instance. It shall be present only if updateType = \"ADD_VNF\".          
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> addVnfIstance = null;

  @ApiModelProperty(value = "Identifies an existing VNF instance to be removed from the NS instance. It contains the identifier(s) of the VNF instances to be removed. It shall be present only if updateType = \"REMOVE_VNF.\" Note: If a VNF instance is removed from a NS and this NS was the last one for which this VNF instance was a part, the VNF instance is terminated by the NFVO. ")
 /**
   * Identifies an existing VNF instance to be removed from the NS instance. It contains the identifier(s) of the VNF instances to be removed. It shall be present only if updateType = \"REMOVE_VNF.\" Note: If a VNF instance is removed from a NS and this NS was the last one for which this VNF instance was a part, the VNF instance is terminated by the NFVO. 
  **/
  private List<String> removeVnfInstanceId = null;

  @ApiModelProperty(value = "Identifies the new VNF to be instantiated. It can be used e.g. for the bottom-up NS creation. It shall be present only if updateType = \"INSTANTIATE_VNF\". ")
  @Valid
 /**
   * Identifies the new VNF to be instantiated. It can be used e.g. for the bottom-up NS creation. It shall be present only if updateType = \"INSTANTIATE_VNF\". 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData> instantiateVnfData = null;

  @ApiModelProperty(value = "Identifies the new DF of the VNF instance to be changed to. It shall be present only if updateType = \"CHANGE_VNF_DF\". ")
  @Valid
 /**
   * Identifies the new DF of the VNF instance to be changed to. It shall be present only if updateType = \"CHANGE_VNF_DF\". 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData> changeVnfFlavourData = null;

  @ApiModelProperty(value = "Identifies the state of the VNF instance to be changed.  It shall be present only if updateType = \"OPERATE_VNF\". ")
  @Valid
 /**
   * Identifies the state of the VNF instance to be changed.  It shall be present only if updateType = \"OPERATE_VNF\". 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData> operateVnfData = null;

  @ApiModelProperty(value = "Identifies the VNF information parameters and/or the configurable properties of VNF instance to be modified. It shall be present only if updateType = \"MODIFY_VNF_INFORMATION\". ")
  @Valid
 /**
   * Identifies the VNF information parameters and/or the configurable properties of VNF instance to be modified. It shall be present only if updateType = \"MODIFY_VNF_INFORMATION\". 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestModifyVnfInfoData> modifyVnfInfoData = null;

  @ApiModelProperty(value = "Specifies the new external connectivity data of the VNF instance to be changed. It shall be present only if updateType = \"CHANGE_EXTERNAL_VNF_CONNECTIVITY\". ")
  @Valid
 /**
   * Specifies the new external connectivity data of the VNF instance to be changed. It shall be present only if updateType = \"CHANGE_EXTERNAL_VNF_CONNECTIVITY\". 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData> changeExtVnfConnectivityData = null;

  @ApiModelProperty(value = "Identifies a new SAP to be added to the NS instance. It shall be present only if updateType = \"ADD_SAP.\" ")
  @Valid
 /**
   * Identifies a new SAP to be added to the NS instance. It shall be present only if updateType = \"ADD_SAP.\" 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData> addSap = null;

  @ApiModelProperty(value = "The identifier an existing SAP to be removed from the NS instance. It shall be present only if updateType = \"REMOVE_SAP.\" ")
 /**
   * The identifier an existing SAP to be removed from the NS instance. It shall be present only if updateType = \"REMOVE_SAP.\" 
  **/
  private List<String> removeSapId = null;

  @ApiModelProperty(value = "The identifier of an existing nested NS instance to be added to (nested within) the NS instance. It shall be present only if updateType = \"ADD_NESTED_NS\". ")
 /**
   * The identifier of an existing nested NS instance to be added to (nested within) the NS instance. It shall be present only if updateType = \"ADD_NESTED_NS\". 
  **/
  private List<String> addNestedNsId = null;

  @ApiModelProperty(value = "The identifier of an existing nested NS instance to be removed from the NS instance. It shall be present only if updateType = \"REMOVE_NESTED_NS\". ")
 /**
   * The identifier of an existing nested NS instance to be removed from the NS instance. It shall be present only if updateType = \"REMOVE_NESTED_NS\". 
  **/
  private List<String> removeNestedNsId = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdupdateUpdateNsRequestAssocNewNsdVersionData assocNewNsdVersionData = null;

  @ApiModelProperty(value = "Specify existing VNF instance to be moved from one NS instance to another NS instance. It shall be present only if updateType = MOVE_VNF\". ")
  @Valid
 /**
   * Specify existing VNF instance to be moved from one NS instance to another NS instance. It shall be present only if updateType = MOVE_VNF\". 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData> moveVnfInstanceData = null;

  @ApiModelProperty(value = "Specify the new VNFFG to be created to the NS Instance. It shall be present only if updateType = \"ADD_VNFFG\". ")
  @Valid
 /**
   * Specify the new VNFFG to be created to the NS Instance. It shall be present only if updateType = \"ADD_VNFFG\". 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg> addVnffg = null;

  @ApiModelProperty(value = "Identifier of an existing VNFFG to be removed from the NS Instance. It shall be present only if updateType = \"REMOVE_VNFFG\". ")
 /**
   * Identifier of an existing VNFFG to be removed from the NS Instance. It shall be present only if updateType = \"REMOVE_VNFFG\". 
  **/
  private List<String> removeVnffgId = null;

  @ApiModelProperty(value = "Specify the new VNFFG Information data to be updated for a VNFFG of the NS Instance. It shall be present only if updateType = \"UPDATE_VNFFG\". ")
  @Valid
 /**
   * Specify the new VNFFG Information data to be updated for a VNFFG of the NS Instance. It shall be present only if updateType = \"UPDATE_VNFFG\". 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg> updateVnffg = null;

  @ApiModelProperty(value = "")
  @Valid
  private NsInstancesnsInstanceIdupdateUpdateNsRequestChangeNsFlavourData changeNsFlavourData = null;

  @ApiModelProperty(value = "specifies the PNF to be added into the NS instance.  It shall be present only if updateType = \"ADD_PNF\". ")
  @Valid
 /**
   * specifies the PNF to be added into the NS instance.  It shall be present only if updateType = \"ADD_PNF\". 
  **/
  private List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData> addPnfData = null;

  @ApiModelProperty(value = "Specifies the PNF to be modified in the NS instance.  It shall be present only if updateType = \"MODIFY_PNF\". ")
  @Valid
 /**
   * Specifies the PNF to be modified in the NS instance.  It shall be present only if updateType = \"MODIFY_PNF\". 
  **/
  private List<NsInstancesnsInstanceIdupdateUpdateNsRequestModifyPnfData> modifyPnfData = null;

  @ApiModelProperty(value = "Identifier of the PNF to be deleted from the NS instance. It shall be present only if updateType = \"REMOVE_PNF\". ")
 /**
   * Identifier of the PNF to be deleted from the NS instance. It shall be present only if updateType = \"REMOVE_PNF\". 
  **/
  private List<String> removePnfId = null;

  @ApiModelProperty(value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date updateTime = null;
 /**
   * The type of update. It determines also which one of the following parameters is present in the operation. Possible values include: * ADD_VNF: Adding existing VNF instance(s) * REMOVE_VNF: Removing VNF instance(s) * INSTANTIATE_VNF: Instantiating new VNF(s) * CHANGE_VNF_DF: Changing VNF DF * OPERATE_VNF: Changing VNF state, * MODIFY_VNF_INFORMATION: Modifying VNF information and/or the configurable properties of VNF instance(s) * CHANGE_EXTERNAL_VNF_CONNECTIVITY: Changing the external connectivity of VNF instance(s)ADD_SAP: Adding SAP(s) * REMOVE_SAP: Removing SAP(s) * ADD_NESTED_NS: Adding existing NS instance(s) as nested NS(s) * REMOVE_NESTED_NS: Removing existing nested NS instance(s) * ASSOC_NEW_NSD_VERSION: Associating a new NSD version to the NS instance * MOVE_VNF: Moving VNF instance(s) from one origin NS instance to another target NS instance * ADD_VNFFG: Adding VNFFG(s) * REMOVE_VNFFG: Removing VNFFG(s) * UPDATE_VNFFG: Updating VNFFG(s) * CHANGE_NS_DF: Changing NS DF * ADD_PNF: Adding PNF * MODIFY_PNF: Modifying PNF * REMOVE_PNF: Removing PNF 
   * @return updateType
  **/
  @JsonProperty("updateType")
  @NotNull
  public String getUpdateType() {
    if (updateType == null) {
      return null;
    }
    return updateType.value();
  }

  public void setUpdateType(UpdateTypeEnum updateType) {
    this.updateType = updateType;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest updateType(UpdateTypeEnum updateType) {
    this.updateType = updateType;
    return this;
  }

 /**
   * Identifies an existing VNF instance to be added to the NS instance. It shall be present only if updateType &#x3D; \&quot;ADD_VNF\&quot;.          
   * @return addVnfIstance
  **/
  @JsonProperty("addVnfIstance")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> getAddVnfIstance() {
    return addVnfIstance;
  }

  public void setAddVnfIstance(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> addVnfIstance) {
    this.addVnfIstance = addVnfIstance;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addVnfIstance(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData> addVnfIstance) {
    this.addVnfIstance = addVnfIstance;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addAddVnfIstanceItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestVnfInstanceData addVnfIstanceItem) {
    this.addVnfIstance.add(addVnfIstanceItem);
    return this;
  }

 /**
   * Identifies an existing VNF instance to be removed from the NS instance. It contains the identifier(s) of the VNF instances to be removed. It shall be present only if updateType &#x3D; \&quot;REMOVE_VNF.\&quot; Note: If a VNF instance is removed from a NS and this NS was the last one for which this VNF instance was a part, the VNF instance is terminated by the NFVO. 
   * @return removeVnfInstanceId
  **/
  @JsonProperty("removeVnfInstanceId")
  public List<String> getRemoveVnfInstanceId() {
    return removeVnfInstanceId;
  }

  public void setRemoveVnfInstanceId(List<String> removeVnfInstanceId) {
    this.removeVnfInstanceId = removeVnfInstanceId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest removeVnfInstanceId(List<String> removeVnfInstanceId) {
    this.removeVnfInstanceId = removeVnfInstanceId;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addRemoveVnfInstanceIdItem(String removeVnfInstanceIdItem) {
    this.removeVnfInstanceId.add(removeVnfInstanceIdItem);
    return this;
  }

 /**
   * Identifies the new VNF to be instantiated. It can be used e.g. for the bottom-up NS creation. It shall be present only if updateType &#x3D; \&quot;INSTANTIATE_VNF\&quot;. 
   * @return instantiateVnfData
  **/
  @JsonProperty("instantiateVnfData")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData> getInstantiateVnfData() {
    return instantiateVnfData;
  }

  public void setInstantiateVnfData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData> instantiateVnfData) {
    this.instantiateVnfData = instantiateVnfData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest instantiateVnfData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData> instantiateVnfData) {
    this.instantiateVnfData = instantiateVnfData;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addInstantiateVnfDataItem(NsInstancesnsInstanceIdupdateUpdateNsRequestInstantiateVnfData instantiateVnfDataItem) {
    this.instantiateVnfData.add(instantiateVnfDataItem);
    return this;
  }

 /**
   * Identifies the new DF of the VNF instance to be changed to. It shall be present only if updateType &#x3D; \&quot;CHANGE_VNF_DF\&quot;. 
   * @return changeVnfFlavourData
  **/
  @JsonProperty("changeVnfFlavourData")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData> getChangeVnfFlavourData() {
    return changeVnfFlavourData;
  }

  public void setChangeVnfFlavourData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData> changeVnfFlavourData) {
    this.changeVnfFlavourData = changeVnfFlavourData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest changeVnfFlavourData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData> changeVnfFlavourData) {
    this.changeVnfFlavourData = changeVnfFlavourData;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addChangeVnfFlavourDataItem(NsInstancesnsInstanceIdupdateUpdateNsRequestChangeVnfFlavourData changeVnfFlavourDataItem) {
    this.changeVnfFlavourData.add(changeVnfFlavourDataItem);
    return this;
  }

 /**
   * Identifies the state of the VNF instance to be changed.  It shall be present only if updateType &#x3D; \&quot;OPERATE_VNF\&quot;. 
   * @return operateVnfData
  **/
  @JsonProperty("operateVnfData")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData> getOperateVnfData() {
    return operateVnfData;
  }

  public void setOperateVnfData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData> operateVnfData) {
    this.operateVnfData = operateVnfData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest operateVnfData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData> operateVnfData) {
    this.operateVnfData = operateVnfData;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addOperateVnfDataItem(NsInstancesnsInstanceIdupdateUpdateNsRequestOperateVnfData operateVnfDataItem) {
    this.operateVnfData.add(operateVnfDataItem);
    return this;
  }

 /**
   * Identifies the VNF information parameters and/or the configurable properties of VNF instance to be modified. It shall be present only if updateType &#x3D; \&quot;MODIFY_VNF_INFORMATION\&quot;. 
   * @return modifyVnfInfoData
  **/
  @JsonProperty("modifyVnfInfoData")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestModifyVnfInfoData> getModifyVnfInfoData() {
    return modifyVnfInfoData;
  }

  public void setModifyVnfInfoData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestModifyVnfInfoData> modifyVnfInfoData) {
    this.modifyVnfInfoData = modifyVnfInfoData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest modifyVnfInfoData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestModifyVnfInfoData> modifyVnfInfoData) {
    this.modifyVnfInfoData = modifyVnfInfoData;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addModifyVnfInfoDataItem(NsInstancesnsInstanceIdupdateUpdateNsRequestModifyVnfInfoData modifyVnfInfoDataItem) {
    this.modifyVnfInfoData.add(modifyVnfInfoDataItem);
    return this;
  }

 /**
   * Specifies the new external connectivity data of the VNF instance to be changed. It shall be present only if updateType &#x3D; \&quot;CHANGE_EXTERNAL_VNF_CONNECTIVITY\&quot;. 
   * @return changeExtVnfConnectivityData
  **/
  @JsonProperty("changeExtVnfConnectivityData")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData> getChangeExtVnfConnectivityData() {
    return changeExtVnfConnectivityData;
  }

  public void setChangeExtVnfConnectivityData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData> changeExtVnfConnectivityData) {
    this.changeExtVnfConnectivityData = changeExtVnfConnectivityData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest changeExtVnfConnectivityData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData> changeExtVnfConnectivityData) {
    this.changeExtVnfConnectivityData = changeExtVnfConnectivityData;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addChangeExtVnfConnectivityDataItem(NsInstancesnsInstanceIdupdateUpdateNsRequestChangeExtVnfConnectivityData changeExtVnfConnectivityDataItem) {
    this.changeExtVnfConnectivityData.add(changeExtVnfConnectivityDataItem);
    return this;
  }

 /**
   * Identifies a new SAP to be added to the NS instance. It shall be present only if updateType &#x3D; \&quot;ADD_SAP.\&quot; 
   * @return addSap
  **/
  @JsonProperty("addSap")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData> getAddSap() {
    return addSap;
  }

  public void setAddSap(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData> addSap) {
    this.addSap = addSap;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addSap(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData> addSap) {
    this.addSap = addSap;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addAddSapItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestSapData addSapItem) {
    this.addSap.add(addSapItem);
    return this;
  }

 /**
   * The identifier an existing SAP to be removed from the NS instance. It shall be present only if updateType &#x3D; \&quot;REMOVE_SAP.\&quot; 
   * @return removeSapId
  **/
  @JsonProperty("removeSapId")
  public List<String> getRemoveSapId() {
    return removeSapId;
  }

  public void setRemoveSapId(List<String> removeSapId) {
    this.removeSapId = removeSapId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest removeSapId(List<String> removeSapId) {
    this.removeSapId = removeSapId;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addRemoveSapIdItem(String removeSapIdItem) {
    this.removeSapId.add(removeSapIdItem);
    return this;
  }

 /**
   * The identifier of an existing nested NS instance to be added to (nested within) the NS instance. It shall be present only if updateType &#x3D; \&quot;ADD_NESTED_NS\&quot;. 
   * @return addNestedNsId
  **/
  @JsonProperty("addNestedNsId")
  public List<String> getAddNestedNsId() {
    return addNestedNsId;
  }

  public void setAddNestedNsId(List<String> addNestedNsId) {
    this.addNestedNsId = addNestedNsId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addNestedNsId(List<String> addNestedNsId) {
    this.addNestedNsId = addNestedNsId;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addAddNestedNsIdItem(String addNestedNsIdItem) {
    this.addNestedNsId.add(addNestedNsIdItem);
    return this;
  }

 /**
   * The identifier of an existing nested NS instance to be removed from the NS instance. It shall be present only if updateType &#x3D; \&quot;REMOVE_NESTED_NS\&quot;. 
   * @return removeNestedNsId
  **/
  @JsonProperty("removeNestedNsId")
  public List<String> getRemoveNestedNsId() {
    return removeNestedNsId;
  }

  public void setRemoveNestedNsId(List<String> removeNestedNsId) {
    this.removeNestedNsId = removeNestedNsId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest removeNestedNsId(List<String> removeNestedNsId) {
    this.removeNestedNsId = removeNestedNsId;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addRemoveNestedNsIdItem(String removeNestedNsIdItem) {
    this.removeNestedNsId.add(removeNestedNsIdItem);
    return this;
  }

 /**
   * Get assocNewNsdVersionData
   * @return assocNewNsdVersionData
  **/
  @JsonProperty("assocNewNsdVersionData")
  public NsInstancesnsInstanceIdupdateUpdateNsRequestAssocNewNsdVersionData getAssocNewNsdVersionData() {
    return assocNewNsdVersionData;
  }

  public void setAssocNewNsdVersionData(NsInstancesnsInstanceIdupdateUpdateNsRequestAssocNewNsdVersionData assocNewNsdVersionData) {
    this.assocNewNsdVersionData = assocNewNsdVersionData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest assocNewNsdVersionData(NsInstancesnsInstanceIdupdateUpdateNsRequestAssocNewNsdVersionData assocNewNsdVersionData) {
    this.assocNewNsdVersionData = assocNewNsdVersionData;
    return this;
  }

 /**
   * Specify existing VNF instance to be moved from one NS instance to another NS instance. It shall be present only if updateType &#x3D; MOVE_VNF\&quot;. 
   * @return moveVnfInstanceData
  **/
  @JsonProperty("moveVnfInstanceData")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData> getMoveVnfInstanceData() {
    return moveVnfInstanceData;
  }

  public void setMoveVnfInstanceData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData> moveVnfInstanceData) {
    this.moveVnfInstanceData = moveVnfInstanceData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest moveVnfInstanceData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData> moveVnfInstanceData) {
    this.moveVnfInstanceData = moveVnfInstanceData;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addMoveVnfInstanceDataItem(NsInstancesnsInstanceIdupdateUpdateNsRequestMoveVnfInstanceData moveVnfInstanceDataItem) {
    this.moveVnfInstanceData.add(moveVnfInstanceDataItem);
    return this;
  }

 /**
   * Specify the new VNFFG to be created to the NS Instance. It shall be present only if updateType &#x3D; \&quot;ADD_VNFFG\&quot;. 
   * @return addVnffg
  **/
  @JsonProperty("addVnffg")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg> getAddVnffg() {
    return addVnffg;
  }

  public void setAddVnffg(List<NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg> addVnffg) {
    this.addVnffg = addVnffg;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addVnffg(List<NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg> addVnffg) {
    this.addVnffg = addVnffg;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addAddVnffgItem(NsInstancesnsInstanceIdupdateUpdateNsRequestAddVnffg addVnffgItem) {
    this.addVnffg.add(addVnffgItem);
    return this;
  }

 /**
   * Identifier of an existing VNFFG to be removed from the NS Instance. It shall be present only if updateType &#x3D; \&quot;REMOVE_VNFFG\&quot;. 
   * @return removeVnffgId
  **/
  @JsonProperty("removeVnffgId")
  public List<String> getRemoveVnffgId() {
    return removeVnffgId;
  }

  public void setRemoveVnffgId(List<String> removeVnffgId) {
    this.removeVnffgId = removeVnffgId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest removeVnffgId(List<String> removeVnffgId) {
    this.removeVnffgId = removeVnffgId;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addRemoveVnffgIdItem(String removeVnffgIdItem) {
    this.removeVnffgId.add(removeVnffgIdItem);
    return this;
  }

 /**
   * Specify the new VNFFG Information data to be updated for a VNFFG of the NS Instance. It shall be present only if updateType &#x3D; \&quot;UPDATE_VNFFG\&quot;. 
   * @return updateVnffg
  **/
  @JsonProperty("updateVnffg")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg> getUpdateVnffg() {
    return updateVnffg;
  }

  public void setUpdateVnffg(List<NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg> updateVnffg) {
    this.updateVnffg = updateVnffg;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest updateVnffg(List<NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg> updateVnffg) {
    this.updateVnffg = updateVnffg;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addUpdateVnffgItem(NsInstancesnsInstanceIdupdateUpdateNsRequestUpdateVnffg updateVnffgItem) {
    this.updateVnffg.add(updateVnffgItem);
    return this;
  }

 /**
   * Get changeNsFlavourData
   * @return changeNsFlavourData
  **/
  @JsonProperty("changeNsFlavourData")
  public NsInstancesnsInstanceIdupdateUpdateNsRequestChangeNsFlavourData getChangeNsFlavourData() {
    return changeNsFlavourData;
  }

  public void setChangeNsFlavourData(NsInstancesnsInstanceIdupdateUpdateNsRequestChangeNsFlavourData changeNsFlavourData) {
    this.changeNsFlavourData = changeNsFlavourData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest changeNsFlavourData(NsInstancesnsInstanceIdupdateUpdateNsRequestChangeNsFlavourData changeNsFlavourData) {
    this.changeNsFlavourData = changeNsFlavourData;
    return this;
  }

 /**
   * specifies the PNF to be added into the NS instance.  It shall be present only if updateType &#x3D; \&quot;ADD_PNF\&quot;. 
   * @return addPnfData
  **/
  @JsonProperty("addPnfData")
  public List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData> getAddPnfData() {
    return addPnfData;
  }

  public void setAddPnfData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData> addPnfData) {
    this.addPnfData = addPnfData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addPnfData(List<NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData> addPnfData) {
    this.addPnfData = addPnfData;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addAddPnfDataItem(NsInstancesnsInstanceIdinstantiateInstantiateNsRequestAddpnfData addPnfDataItem) {
    this.addPnfData.add(addPnfDataItem);
    return this;
  }

 /**
   * Specifies the PNF to be modified in the NS instance.  It shall be present only if updateType &#x3D; \&quot;MODIFY_PNF\&quot;. 
   * @return modifyPnfData
  **/
  @JsonProperty("modifyPnfData")
  public List<NsInstancesnsInstanceIdupdateUpdateNsRequestModifyPnfData> getModifyPnfData() {
    return modifyPnfData;
  }

  public void setModifyPnfData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestModifyPnfData> modifyPnfData) {
    this.modifyPnfData = modifyPnfData;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest modifyPnfData(List<NsInstancesnsInstanceIdupdateUpdateNsRequestModifyPnfData> modifyPnfData) {
    this.modifyPnfData = modifyPnfData;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addModifyPnfDataItem(NsInstancesnsInstanceIdupdateUpdateNsRequestModifyPnfData modifyPnfDataItem) {
    this.modifyPnfData.add(modifyPnfDataItem);
    return this;
  }

 /**
   * Identifier of the PNF to be deleted from the NS instance. It shall be present only if updateType &#x3D; \&quot;REMOVE_PNF\&quot;. 
   * @return removePnfId
  **/
  @JsonProperty("removePnfId")
  public List<String> getRemovePnfId() {
    return removePnfId;
  }

  public void setRemovePnfId(List<String> removePnfId) {
    this.removePnfId = removePnfId;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest removePnfId(List<String> removePnfId) {
    this.removePnfId = removePnfId;
    return this;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest addRemovePnfIdItem(String removePnfIdItem) {
    this.removePnfId.add(removePnfIdItem);
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return updateTime
  **/
  @JsonProperty("updateTime")
  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public NsInstancesnsInstanceIdupdateUpdateNsRequest updateTime(Date updateTime) {
    this.updateTime = updateTime;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsInstancesnsInstanceIdupdateUpdateNsRequest {\n");
    
    sb.append("    updateType: ").append(toIndentedString(updateType)).append("\n");
    sb.append("    addVnfIstance: ").append(toIndentedString(addVnfIstance)).append("\n");
    sb.append("    removeVnfInstanceId: ").append(toIndentedString(removeVnfInstanceId)).append("\n");
    sb.append("    instantiateVnfData: ").append(toIndentedString(instantiateVnfData)).append("\n");
    sb.append("    changeVnfFlavourData: ").append(toIndentedString(changeVnfFlavourData)).append("\n");
    sb.append("    operateVnfData: ").append(toIndentedString(operateVnfData)).append("\n");
    sb.append("    modifyVnfInfoData: ").append(toIndentedString(modifyVnfInfoData)).append("\n");
    sb.append("    changeExtVnfConnectivityData: ").append(toIndentedString(changeExtVnfConnectivityData)).append("\n");
    sb.append("    addSap: ").append(toIndentedString(addSap)).append("\n");
    sb.append("    removeSapId: ").append(toIndentedString(removeSapId)).append("\n");
    sb.append("    addNestedNsId: ").append(toIndentedString(addNestedNsId)).append("\n");
    sb.append("    removeNestedNsId: ").append(toIndentedString(removeNestedNsId)).append("\n");
    sb.append("    assocNewNsdVersionData: ").append(toIndentedString(assocNewNsdVersionData)).append("\n");
    sb.append("    moveVnfInstanceData: ").append(toIndentedString(moveVnfInstanceData)).append("\n");
    sb.append("    addVnffg: ").append(toIndentedString(addVnffg)).append("\n");
    sb.append("    removeVnffgId: ").append(toIndentedString(removeVnffgId)).append("\n");
    sb.append("    updateVnffg: ").append(toIndentedString(updateVnffg)).append("\n");
    sb.append("    changeNsFlavourData: ").append(toIndentedString(changeNsFlavourData)).append("\n");
    sb.append("    addPnfData: ").append(toIndentedString(addPnfData)).append("\n");
    sb.append("    modifyPnfData: ").append(toIndentedString(modifyPnfData)).append("\n");
    sb.append("    removePnfId: ").append(toIndentedString(removePnfId)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
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

