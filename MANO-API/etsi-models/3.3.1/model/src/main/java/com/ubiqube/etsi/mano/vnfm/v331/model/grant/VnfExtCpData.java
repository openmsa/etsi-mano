package com.ubiqube.etsi.mano.vnfm.v331.model.grant;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.vnfm.v331.model.grant.VnfExtCpConfig;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents configuration information for external CPs created from a CPD. 
 */
@Schema(description = "This type represents configuration information for external CPs created from a CPD. ")
@Validated


public class VnfExtCpData   {
  @JsonProperty("cpdId")
  private String cpdId = null;

  @JsonProperty("cpConfig")
  @Valid
  private Map<String, VnfExtCpConfig> cpConfig = null;

  public VnfExtCpData cpdId(String cpdId) {
    this.cpdId = cpdId;
    return this;
  }

  /**
   * Get cpdId
   * @return cpdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getCpdId() {
    return cpdId;
  }

  public void setCpdId(String cpdId) {
    this.cpdId = cpdId;
  }

  public VnfExtCpData cpConfig(Map<String, VnfExtCpConfig> cpConfig) {
    this.cpConfig = cpConfig;
    return this;
  }

  public VnfExtCpData putCpConfigItem(String key, VnfExtCpConfig cpConfigItem) {
    if (this.cpConfig == null) {
      this.cpConfig = new HashMap<>();
    }
    this.cpConfig.put(key, cpConfigItem);
    return this;
  }

  /**
   * Map of instance data that need to be configured on the CP instances created from the respective CPD. The key of the map which identifies the individual VnfExtCpConfig entries is managed by the API consumer. The entries shall be applied by the VNFM according to the rules of JSON Merge Patch (see IETF RFC 7396). Within one VNF instance, all VNFC instances created from a particular VDU have the same external connectivity. Thus, given a particular value of the “cpdId” attribute, there shall be one “cpConfig” entry for each VNFC instance that has been or can be created from a VDU which includes a CPD identified by the “cpdId” attribute. If the cpConfig represents a subport in a trunk, all “cpConfig” entries in this list shall have the same segmentationId, which means they are connected to the same set of external VLs via the trunk. The map entry value shall be set to \"null\" in order to delete a \"VnfExtCpConfig\" entry identified by a particular key value from the map, i.e. for the disconnection of an existing external CP instance addressed by cpInstanceId in the deleted map entry from a particular external virtual link, and deletion of that instance in case it represents a subport. Deleting the last key from the map removes the affected instance of the \"VnfExtCpData\" structure from its parent data structure. 
   * @return cpConfig
   **/
  @Schema(description = "Map of instance data that need to be configured on the CP instances created from the respective CPD. The key of the map which identifies the individual VnfExtCpConfig entries is managed by the API consumer. The entries shall be applied by the VNFM according to the rules of JSON Merge Patch (see IETF RFC 7396). Within one VNF instance, all VNFC instances created from a particular VDU have the same external connectivity. Thus, given a particular value of the “cpdId” attribute, there shall be one “cpConfig” entry for each VNFC instance that has been or can be created from a VDU which includes a CPD identified by the “cpdId” attribute. If the cpConfig represents a subport in a trunk, all “cpConfig” entries in this list shall have the same segmentationId, which means they are connected to the same set of external VLs via the trunk. The map entry value shall be set to \"null\" in order to delete a \"VnfExtCpConfig\" entry identified by a particular key value from the map, i.e. for the disconnection of an existing external CP instance addressed by cpInstanceId in the deleted map entry from a particular external virtual link, and deletion of that instance in case it represents a subport. Deleting the last key from the map removes the affected instance of the \"VnfExtCpData\" structure from its parent data structure. ")
      @Valid
    public Map<String, VnfExtCpConfig> getCpConfig() {
    return cpConfig;
  }

  public void setCpConfig(Map<String, VnfExtCpConfig> cpConfig) {
    this.cpConfig = cpConfig;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfExtCpData vnfExtCpData = (VnfExtCpData) o;
    return Objects.equals(this.cpdId, vnfExtCpData.cpdId) &&
        Objects.equals(this.cpConfig, vnfExtCpData.cpConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpdId, cpConfig);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfExtCpData {\n");
    
    sb.append("    cpdId: ").append(toIndentedString(cpdId)).append("\n");
    sb.append("    cpConfig: ").append(toIndentedString(cpConfig)).append("\n");
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
