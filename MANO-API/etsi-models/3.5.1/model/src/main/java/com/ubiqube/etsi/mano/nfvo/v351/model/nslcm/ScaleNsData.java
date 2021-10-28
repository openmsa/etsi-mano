package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.NestedNsLocationConstraint;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ParamsForVnf;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ScaleNsByStepsData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.ScaleNsToLevelData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.VnfInstanceData;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.VnfLocationConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the information to scale a NS. NOTE 1: No more than two attributes between vnfInstanceToBeAdded, vnfInstanceToBeRemoved, scaleNsByStepsData and scaleNsToLevelData shall be present. In case of two, the attributes shall be vnfInstanceToBeAdded and vnfInstanceToBeRemoved. NOTE 2: The DF of the VNF instance shall match the VNF DF present in the associated VNF Profile of the new NS flavour. NOTE 3: This functionality is the same as the one provided by the Update NS operation when the AddVnf update type is selected (see clause 7.3.5). NOTE 4: This functionality is the same as the one provided by the Update NS operation when the RemoveVnf update type is selected (see clause 7.3.5). 
 */
@Schema(description = "This type represents the information to scale a NS. NOTE 1: No more than two attributes between vnfInstanceToBeAdded, vnfInstanceToBeRemoved, scaleNsByStepsData and scaleNsToLevelData shall be present. In case of two, the attributes shall be vnfInstanceToBeAdded and vnfInstanceToBeRemoved. NOTE 2: The DF of the VNF instance shall match the VNF DF present in the associated VNF Profile of the new NS flavour. NOTE 3: This functionality is the same as the one provided by the Update NS operation when the AddVnf update type is selected (see clause 7.3.5). NOTE 4: This functionality is the same as the one provided by the Update NS operation when the RemoveVnf update type is selected (see clause 7.3.5). ")
@Validated


public class ScaleNsData   {
  @JsonProperty("vnfInstanceToBeAdded")
  @Valid
  private List<VnfInstanceData> vnfInstanceToBeAdded = null;

  @JsonProperty("vnfInstanceToBeRemoved")
  @Valid
  private List<String> vnfInstanceToBeRemoved = null;

  @JsonProperty("scaleNsByStepsData")
  private ScaleNsByStepsData scaleNsByStepsData = null;

  @JsonProperty("scaleNsToLevelData")
  private ScaleNsToLevelData scaleNsToLevelData = null;

  @JsonProperty("additionalParamsForNs")
  private ParamsForVnf additionalParamsForNs = null;

  @JsonProperty("additionalParamsForVnf")
  @Valid
  private List<ParamsForVnf> additionalParamsForVnf = null;

  @JsonProperty("locationConstraints")
  @Valid
  private List<VnfLocationConstraint> locationConstraints = null;

  @JsonProperty("nestedNslocationConstraints")
  @Valid
  private List<NestedNsLocationConstraint> nestedNslocationConstraints = null;

  public ScaleNsData vnfInstanceToBeAdded(List<VnfInstanceData> vnfInstanceToBeAdded) {
    this.vnfInstanceToBeAdded = vnfInstanceToBeAdded;
    return this;
  }

  public ScaleNsData addVnfInstanceToBeAddedItem(VnfInstanceData vnfInstanceToBeAddedItem) {
    if (this.vnfInstanceToBeAdded == null) {
      this.vnfInstanceToBeAdded = new ArrayList<>();
    }
    this.vnfInstanceToBeAdded.add(vnfInstanceToBeAddedItem);
    return this;
  }

  /**
   * An existing VNF instance to be added to the NS instance as part of the scaling operation. If needed, the VNF Profile to be used for this VNF instance may also be provided. See note 1, note 2 and note 3. 
   * @return vnfInstanceToBeAdded
   **/
  @Schema(description = "An existing VNF instance to be added to the NS instance as part of the scaling operation. If needed, the VNF Profile to be used for this VNF instance may also be provided. See note 1, note 2 and note 3. ")
      @Valid
    public List<VnfInstanceData> getVnfInstanceToBeAdded() {
    return vnfInstanceToBeAdded;
  }

  public void setVnfInstanceToBeAdded(List<VnfInstanceData> vnfInstanceToBeAdded) {
    this.vnfInstanceToBeAdded = vnfInstanceToBeAdded;
  }

  public ScaleNsData vnfInstanceToBeRemoved(List<String> vnfInstanceToBeRemoved) {
    this.vnfInstanceToBeRemoved = vnfInstanceToBeRemoved;
    return this;
  }

  public ScaleNsData addVnfInstanceToBeRemovedItem(String vnfInstanceToBeRemovedItem) {
    if (this.vnfInstanceToBeRemoved == null) {
      this.vnfInstanceToBeRemoved = new ArrayList<>();
    }
    this.vnfInstanceToBeRemoved.add(vnfInstanceToBeRemovedItem);
    return this;
  }

  /**
   * The VNF instance to be removed from the NS instance as part of the scaling operation. See note 1 and note 4. 
   * @return vnfInstanceToBeRemoved
   **/
  @Schema(description = "The VNF instance to be removed from the NS instance as part of the scaling operation. See note 1 and note 4. ")
  
    public List<String> getVnfInstanceToBeRemoved() {
    return vnfInstanceToBeRemoved;
  }

  public void setVnfInstanceToBeRemoved(List<String> vnfInstanceToBeRemoved) {
    this.vnfInstanceToBeRemoved = vnfInstanceToBeRemoved;
  }

  public ScaleNsData scaleNsByStepsData(ScaleNsByStepsData scaleNsByStepsData) {
    this.scaleNsByStepsData = scaleNsByStepsData;
    return this;
  }

  /**
   * Get scaleNsByStepsData
   * @return scaleNsByStepsData
   **/
  @Schema(description = "")
  
    @Valid
    public ScaleNsByStepsData getScaleNsByStepsData() {
    return scaleNsByStepsData;
  }

  public void setScaleNsByStepsData(ScaleNsByStepsData scaleNsByStepsData) {
    this.scaleNsByStepsData = scaleNsByStepsData;
  }

  public ScaleNsData scaleNsToLevelData(ScaleNsToLevelData scaleNsToLevelData) {
    this.scaleNsToLevelData = scaleNsToLevelData;
    return this;
  }

  /**
   * Get scaleNsToLevelData
   * @return scaleNsToLevelData
   **/
  @Schema(description = "")
  
    @Valid
    public ScaleNsToLevelData getScaleNsToLevelData() {
    return scaleNsToLevelData;
  }

  public void setScaleNsToLevelData(ScaleNsToLevelData scaleNsToLevelData) {
    this.scaleNsToLevelData = scaleNsToLevelData;
  }

  public ScaleNsData additionalParamsForNs(ParamsForVnf additionalParamsForNs) {
    this.additionalParamsForNs = additionalParamsForNs;
    return this;
  }

  /**
   * Get additionalParamsForNs
   * @return additionalParamsForNs
   **/
  @Schema(description = "")
  
    @Valid
    public ParamsForVnf getAdditionalParamsForNs() {
    return additionalParamsForNs;
  }

  public void setAdditionalParamsForNs(ParamsForVnf additionalParamsForNs) {
    this.additionalParamsForNs = additionalParamsForNs;
  }

  public ScaleNsData additionalParamsForVnf(List<ParamsForVnf> additionalParamsForVnf) {
    this.additionalParamsForVnf = additionalParamsForVnf;
    return this;
  }

  public ScaleNsData addAdditionalParamsForVnfItem(ParamsForVnf additionalParamsForVnfItem) {
    if (this.additionalParamsForVnf == null) {
      this.additionalParamsForVnf = new ArrayList<>();
    }
    this.additionalParamsForVnf.add(additionalParamsForVnfItem);
    return this;
  }

  /**
   * Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the NS level, which is covered in additionalParamsforNs). This is for VNFs that are to be created by the NFVO as part of the NS scaling and not for existing VNF that are covered by the scaleVnfData. 
   * @return additionalParamsForVnf
   **/
  @Schema(description = "Allows the OSS/BSS to provide additional parameter(s) per VNF instance (as opposed to the NS level, which is covered in additionalParamsforNs). This is for VNFs that are to be created by the NFVO as part of the NS scaling and not for existing VNF that are covered by the scaleVnfData. ")
      @Valid
    public List<ParamsForVnf> getAdditionalParamsForVnf() {
    return additionalParamsForVnf;
  }

  public void setAdditionalParamsForVnf(List<ParamsForVnf> additionalParamsForVnf) {
    this.additionalParamsForVnf = additionalParamsForVnf;
  }

  public ScaleNsData locationConstraints(List<VnfLocationConstraint> locationConstraints) {
    this.locationConstraints = locationConstraints;
    return this;
  }

  public ScaleNsData addLocationConstraintsItem(VnfLocationConstraint locationConstraintsItem) {
    if (this.locationConstraints == null) {
      this.locationConstraints = new ArrayList<>();
    }
    this.locationConstraints.add(locationConstraintsItem);
    return this;
  }

  /**
   * The location constraints for the VNF to be instantiated as part of the NS scaling. An example can be a constraint for the VNF to be in a specific geographic location. 
   * @return locationConstraints
   **/
  @Schema(description = "The location constraints for the VNF to be instantiated as part of the NS scaling. An example can be a constraint for the VNF to be in a specific geographic location. ")
      @Valid
    public List<VnfLocationConstraint> getLocationConstraints() {
    return locationConstraints;
  }

  public void setLocationConstraints(List<VnfLocationConstraint> locationConstraints) {
    this.locationConstraints = locationConstraints;
  }

  public ScaleNsData nestedNslocationConstraints(List<NestedNsLocationConstraint> nestedNslocationConstraints) {
    this.nestedNslocationConstraints = nestedNslocationConstraints;
    return this;
  }

  public ScaleNsData addNestedNslocationConstraintsItem(NestedNsLocationConstraint nestedNslocationConstraintsItem) {
    if (this.nestedNslocationConstraints == null) {
      this.nestedNslocationConstraints = new ArrayList<>();
    }
    this.nestedNslocationConstraints.add(nestedNslocationConstraintsItem);
    return this;
  }

  /**
   * Defines the location constraints for the nested NS to be instantiated as part of the NS instantiation. An example can be a constraint for the nested NS to be in a specific geographic location. 
   * @return nestedNslocationConstraints
   **/
  @Schema(description = "Defines the location constraints for the nested NS to be instantiated as part of the NS instantiation. An example can be a constraint for the nested NS to be in a specific geographic location. ")
      @Valid
    public List<NestedNsLocationConstraint> getNestedNslocationConstraints() {
    return nestedNslocationConstraints;
  }

  public void setNestedNslocationConstraints(List<NestedNsLocationConstraint> nestedNslocationConstraints) {
    this.nestedNslocationConstraints = nestedNslocationConstraints;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScaleNsData scaleNsData = (ScaleNsData) o;
    return Objects.equals(this.vnfInstanceToBeAdded, scaleNsData.vnfInstanceToBeAdded) &&
        Objects.equals(this.vnfInstanceToBeRemoved, scaleNsData.vnfInstanceToBeRemoved) &&
        Objects.equals(this.scaleNsByStepsData, scaleNsData.scaleNsByStepsData) &&
        Objects.equals(this.scaleNsToLevelData, scaleNsData.scaleNsToLevelData) &&
        Objects.equals(this.additionalParamsForNs, scaleNsData.additionalParamsForNs) &&
        Objects.equals(this.additionalParamsForVnf, scaleNsData.additionalParamsForVnf) &&
        Objects.equals(this.locationConstraints, scaleNsData.locationConstraints) &&
        Objects.equals(this.nestedNslocationConstraints, scaleNsData.nestedNslocationConstraints);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vnfInstanceToBeAdded, vnfInstanceToBeRemoved, scaleNsByStepsData, scaleNsToLevelData, additionalParamsForNs, additionalParamsForVnf, locationConstraints, nestedNslocationConstraints);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ScaleNsData {\n");
    
    sb.append("    vnfInstanceToBeAdded: ").append(toIndentedString(vnfInstanceToBeAdded)).append("\n");
    sb.append("    vnfInstanceToBeRemoved: ").append(toIndentedString(vnfInstanceToBeRemoved)).append("\n");
    sb.append("    scaleNsByStepsData: ").append(toIndentedString(scaleNsByStepsData)).append("\n");
    sb.append("    scaleNsToLevelData: ").append(toIndentedString(scaleNsToLevelData)).append("\n");
    sb.append("    additionalParamsForNs: ").append(toIndentedString(additionalParamsForNs)).append("\n");
    sb.append("    additionalParamsForVnf: ").append(toIndentedString(additionalParamsForVnf)).append("\n");
    sb.append("    locationConstraints: ").append(toIndentedString(locationConstraints)).append("\n");
    sb.append("    nestedNslocationConstraints: ").append(toIndentedString(nestedNslocationConstraints)).append("\n");
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
