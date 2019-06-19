package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
  * This type represents modifications to the information of a VNF package.  It shall comply with the provisions defined in Table 9.5.2.3-1. 
 **/
@ApiModel(description="This type represents modifications to the information of a VNF package.  It shall comply with the provisions defined in Table 9.5.2.3-1. ")
public class VnfPackagesvnfPkgIdVnfPkgInfoModifications  {
  

@XmlType(name="OperationalStateEnum")
@XmlEnum(String.class)
public enum OperationalStateEnum {

@XmlEnumValue("ENABLED") ENABLED(String.valueOf("ENABLED")), @XmlEnumValue("DISABLED") DISABLED(String.valueOf("DISABLED"));


    private String value;

    OperationalStateEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static OperationalStateEnum fromValue(String v) {
        for (OperationalStateEnum b : OperationalStateEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(value = "\"The enumeration PackageOperationalStateType shall  comply with the provisions defined in Table 9.5.4.4-1.\" Acceptable values are: -ENABLED - The VNF package is enabled, i.e. it can be used for instantiation of new VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be used for further VNF instantiation requests (unless and until the VNF package is re-enabled). ")
 /**
   * \"The enumeration PackageOperationalStateType shall  comply with the provisions defined in Table 9.5.4.4-1.\" Acceptable values are: -ENABLED - The VNF package is enabled, i.e. it can be used for instantiation of new VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be used for further VNF instantiation requests (unless and until the VNF package is re-enabled). 
  **/
  private OperationalStateEnum operationalState = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object userDefinedData = null;
 /**
   * \&quot;The enumeration PackageOperationalStateType shall  comply with the provisions defined in Table 9.5.4.4-1.\&quot; Acceptable values are: -ENABLED - The VNF package is enabled, i.e. it can be used for instantiation of new VNF instances. -DISABLED - The VNF package is disabled, i.e. it cannot be used for further VNF instantiation requests (unless and until the VNF package is re-enabled). 
   * @return operationalState
  **/
  @JsonProperty("operationalState")
  public String getOperationalState() {
    if (operationalState == null) {
      return null;
    }
    return operationalState.value();
  }

  public void setOperationalState(OperationalStateEnum operationalState) {
    this.operationalState = operationalState;
  }

  public VnfPackagesvnfPkgIdVnfPkgInfoModifications operationalState(OperationalStateEnum operationalState) {
    this.operationalState = operationalState;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return userDefinedData
  **/
  @JsonProperty("userDefinedData")
  public Object getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(Object userDefinedData) {
    this.userDefinedData = userDefinedData;
  }

  public VnfPackagesvnfPkgIdVnfPkgInfoModifications userDefinedData(Object userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagesvnfPkgIdVnfPkgInfoModifications {\n");
    
    sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
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

