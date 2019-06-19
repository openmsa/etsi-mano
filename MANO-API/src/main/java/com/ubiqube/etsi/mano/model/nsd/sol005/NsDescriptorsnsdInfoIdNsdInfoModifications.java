package com.ubiqube.etsi.mano.model.nsd.sol005;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
  * This type represents attribute modifications for an individual NS descriptor resource based on the NsdInfo data type. The attributes of NsdInfo that can be modified are included in the NsdInfoModifications data type.NOTE: At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. 
 **/
@ApiModel(description="This type represents attribute modifications for an individual NS descriptor resource based on the NsdInfo data type. The attributes of NsdInfo that can be modified are included in the NsdInfoModifications data type.NOTE: At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. ")
public class NsDescriptorsnsdInfoIdNsdInfoModifications  {
  

@XmlType(name="NsdOperationalStateEnum")
@XmlEnum(String.class)
public enum NsdOperationalStateEnum {

@XmlEnumValue("ENABLED") ENABLED(String.valueOf("ENABLED")), @XmlEnumValue("DISABLED") DISABLED(String.valueOf("DISABLED"));


    private String value;

    NsdOperationalStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static NsdOperationalStateEnum fromValue(String v) {
        for (NsdOperationalStateEnum b : NsdOperationalStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "\"The enumeration NsdOperationalStateType shall comply with the provisions defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational state of the resource. ENABLED = The operational state of the resource is enabled.  DISABLED = The operational state of the resource is disabled.\" ")
 /**
   * \"The enumeration NsdOperationalStateType shall comply with the provisions defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational state of the resource. ENABLED = The operational state of the resource is enabled.  DISABLED = The operational state of the resource is disabled.\" 
  **/
  private NsdOperationalStateEnum nsdOperationalState = null;

  @ApiModelProperty(value = "Modifications of the userDefinedData attribute in NsdInfo data type. See note. If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396 [25]).  NOTE- At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. ")
 /**
   * Modifications of the userDefinedData attribute in NsdInfo data type. See note. If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396 [25]).  NOTE- At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. 
  **/
  private List<Object> userDefinedData = null;
 /**
   * \&quot;The enumeration NsdOperationalStateType shall comply with the provisions defined in Table 5.5.4.3-1 of GS NFV_SOL 005. It indicates the operational state of the resource. ENABLED &#x3D; The operational state of the resource is enabled.  DISABLED &#x3D; The operational state of the resource is disabled.\&quot; 
   * @return nsdOperationalState
  **/
  @JsonProperty("nsdOperationalState")
  public String getNsdOperationalState() {
    if (nsdOperationalState == null) {
      return null;
    }
    return nsdOperationalState.value();
  }

  public void setNsdOperationalState(NsdOperationalStateEnum nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
  }

  public NsDescriptorsnsdInfoIdNsdInfoModifications nsdOperationalState(NsdOperationalStateEnum nsdOperationalState) {
    this.nsdOperationalState = nsdOperationalState;
    return this;
  }

 /**
   * Modifications of the userDefinedData attribute in NsdInfo data type. See note. If present, these modifications shall be applied according to the rules of JSON Merge PATCH (see IETF RFC 7396 [25]).  NOTE- At least one of the attributes - nsdOperationalState and userDefinedData - shall be present. 
   * @return userDefinedData
  **/
  @JsonProperty("userDefinedData")
  public List<Object> getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(List<Object> userDefinedData) {
    this.userDefinedData = userDefinedData;
  }

  public NsDescriptorsnsdInfoIdNsdInfoModifications userDefinedData(List<Object> userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  public NsDescriptorsnsdInfoIdNsdInfoModifications addUserDefinedDataItem(Object userDefinedDataItem) {
    this.userDefinedData.add(userDefinedDataItem);
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NsDescriptorsnsdInfoIdNsdInfoModifications {\n");
    
    sb.append("    nsdOperationalState: ").append(toIndentedString(nsdOperationalState)).append("\n");
    sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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

