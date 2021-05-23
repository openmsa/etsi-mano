/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.em.v281.model.vnflcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.AffectedExtLinkPort;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.AffectedVirtualLink;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.AffectedVirtualStorage;
import com.ubiqube.etsi.mano.em.v281.model.vnflcm.AffectedVnfc;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable. 
 */
@ApiModel(description = "This attribute contains information about the cumulative changes to virtualised resources that were performed so far by the LCM operation since its start, if applicable. ")
@Validated

public class VnfLcmOpOccResourceChanges   {
  @JsonProperty("affectedVnfcs")
  @Valid
  private List<AffectedVnfc> affectedVnfcs = null;

  @JsonProperty("affectedVirtualLinks")
  @Valid
  private List<AffectedVirtualLink> affectedVirtualLinks = null;

  @JsonProperty("affectedExtLinkPorts")
  @Valid
  private List<AffectedExtLinkPort> affectedExtLinkPorts = null;

  @JsonProperty("affectedVirtualStorages")
  @Valid
  private List<AffectedVirtualStorage> affectedVirtualStorages = null;

  public VnfLcmOpOccResourceChanges affectedVnfcs(List<AffectedVnfc> affectedVnfcs) {
    this.affectedVnfcs = affectedVnfcs;
    return this;
  }

  public VnfLcmOpOccResourceChanges addAffectedVnfcsItem(AffectedVnfc affectedVnfcsItem) {
    if (this.affectedVnfcs == null) {
      this.affectedVnfcs = new ArrayList<>();
    }
    this.affectedVnfcs.add(affectedVnfcsItem);
    return this;
  }

  /**
   * Information about VNFC instances that were affected during the lifecycle operation. NOTE 1: This allows the NFVO/API consumer to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. 
   * @return affectedVnfcs
  **/
  @ApiModelProperty(value = "Information about VNFC instances that were affected during the lifecycle operation. NOTE 1: This allows the NFVO/API consumer to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")

  @Valid

  public List<AffectedVnfc> getAffectedVnfcs() {
    return affectedVnfcs;
  }

  public void setAffectedVnfcs(List<AffectedVnfc> affectedVnfcs) {
    this.affectedVnfcs = affectedVnfcs;
  }

  public VnfLcmOpOccResourceChanges affectedVirtualLinks(List<AffectedVirtualLink> affectedVirtualLinks) {
    this.affectedVirtualLinks = affectedVirtualLinks;
    return this;
  }

  public VnfLcmOpOccResourceChanges addAffectedVirtualLinksItem(AffectedVirtualLink affectedVirtualLinksItem) {
    if (this.affectedVirtualLinks == null) {
      this.affectedVirtualLinks = new ArrayList<>();
    }
    this.affectedVirtualLinks.add(affectedVirtualLinksItem);
    return this;
  }

  /**
   * Information about VL instances that were affected during the lifecycle operation. See note 1 and note 2. NOTE 1: This allows the NFVO/API consumer to obtain the information contained in the latest \"result\" notification if it has not received it due to an  error or a wrongly configured subscription filter. NOTE 2: For a particular affected VL, there shall be as many  \"AffectedVirtualLink\" entries as needed for signalling the different  types of changes, i.e., one per virtual link and change type. For instance,  in the case of signaling affected VL instances involving the addition of a  particular VL instance with links ports, one \"AffectedVirtualLink\" entry  signals the addition of the VL by using the \"changeType\" attribute of  \"AffectedVirtualLink\" structure equal to \"ADDED\", and another \"AffectedVirtualLink\"  entry signals the addition of externally visible VNF link ports of the VL by using  the \"changeType\" equal to \"LINK_PORT_ADDED\". 
   * @return affectedVirtualLinks
  **/
  @ApiModelProperty(value = "Information about VL instances that were affected during the lifecycle operation. See note 1 and note 2. NOTE 1: This allows the NFVO/API consumer to obtain the information contained in the latest \"result\" notification if it has not received it due to an  error or a wrongly configured subscription filter. NOTE 2: For a particular affected VL, there shall be as many  \"AffectedVirtualLink\" entries as needed for signalling the different  types of changes, i.e., one per virtual link and change type. For instance,  in the case of signaling affected VL instances involving the addition of a  particular VL instance with links ports, one \"AffectedVirtualLink\" entry  signals the addition of the VL by using the \"changeType\" attribute of  \"AffectedVirtualLink\" structure equal to \"ADDED\", and another \"AffectedVirtualLink\"  entry signals the addition of externally visible VNF link ports of the VL by using  the \"changeType\" equal to \"LINK_PORT_ADDED\". ")

  @Valid

  public List<AffectedVirtualLink> getAffectedVirtualLinks() {
    return affectedVirtualLinks;
  }

  public void setAffectedVirtualLinks(List<AffectedVirtualLink> affectedVirtualLinks) {
    this.affectedVirtualLinks = affectedVirtualLinks;
  }

  public VnfLcmOpOccResourceChanges affectedExtLinkPorts(List<AffectedExtLinkPort> affectedExtLinkPorts) {
    this.affectedExtLinkPorts = affectedExtLinkPorts;
    return this;
  }

  public VnfLcmOpOccResourceChanges addAffectedExtLinkPortsItem(AffectedExtLinkPort affectedExtLinkPortsItem) {
    if (this.affectedExtLinkPorts == null) {
      this.affectedExtLinkPorts = new ArrayList<>();
    }
    this.affectedExtLinkPorts.add(affectedExtLinkPortsItem);
    return this;
  }

  /**
   * Information about external VNF link ports that were affected during the lifecycle  operation. See note 1. NOTE 1: This allows the NFVO/API consumer to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription  filter. 
   * @return affectedExtLinkPorts
  **/
  @ApiModelProperty(value = "Information about external VNF link ports that were affected during the lifecycle  operation. See note 1. NOTE 1: This allows the NFVO/API consumer to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription  filter. ")

  @Valid

  public List<AffectedExtLinkPort> getAffectedExtLinkPorts() {
    return affectedExtLinkPorts;
  }

  public void setAffectedExtLinkPorts(List<AffectedExtLinkPort> affectedExtLinkPorts) {
    this.affectedExtLinkPorts = affectedExtLinkPorts;
  }

  public VnfLcmOpOccResourceChanges affectedVirtualStorages(List<AffectedVirtualStorage> affectedVirtualStorages) {
    this.affectedVirtualStorages = affectedVirtualStorages;
    return this;
  }

  public VnfLcmOpOccResourceChanges addAffectedVirtualStoragesItem(AffectedVirtualStorage affectedVirtualStoragesItem) {
    if (this.affectedVirtualStorages == null) {
      this.affectedVirtualStorages = new ArrayList<>();
    }
    this.affectedVirtualStorages.add(affectedVirtualStoragesItem);
    return this;
  }

  /**
   * Information about virtualised storage instances that were affected during the lifecycle operation. This allows the NFVO/API consumer to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. 
   * @return affectedVirtualStorages
  **/
  @ApiModelProperty(value = "Information about virtualised storage instances that were affected during the lifecycle operation. This allows the NFVO/API consumer to obtain the information contained in the latest \"result\" notification if it has not received it due to an error or a wrongly configured subscription filter. ")

  @Valid

  public List<AffectedVirtualStorage> getAffectedVirtualStorages() {
    return affectedVirtualStorages;
  }

  public void setAffectedVirtualStorages(List<AffectedVirtualStorage> affectedVirtualStorages) {
    this.affectedVirtualStorages = affectedVirtualStorages;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfLcmOpOccResourceChanges vnfLcmOpOccResourceChanges = (VnfLcmOpOccResourceChanges) o;
    return Objects.equals(this.affectedVnfcs, vnfLcmOpOccResourceChanges.affectedVnfcs) &&
        Objects.equals(this.affectedVirtualLinks, vnfLcmOpOccResourceChanges.affectedVirtualLinks) &&
        Objects.equals(this.affectedExtLinkPorts, vnfLcmOpOccResourceChanges.affectedExtLinkPorts) &&
        Objects.equals(this.affectedVirtualStorages, vnfLcmOpOccResourceChanges.affectedVirtualStorages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(affectedVnfcs, affectedVirtualLinks, affectedExtLinkPorts, affectedVirtualStorages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfLcmOpOccResourceChanges {\n");
    
    sb.append("    affectedVnfcs: ").append(toIndentedString(affectedVnfcs)).append("\n");
    sb.append("    affectedVirtualLinks: ").append(toIndentedString(affectedVirtualLinks)).append("\n");
    sb.append("    affectedExtLinkPorts: ").append(toIndentedString(affectedExtLinkPorts)).append("\n");
    sb.append("    affectedVirtualStorages: ").append(toIndentedString(affectedVirtualStorages)).append("\n");
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

