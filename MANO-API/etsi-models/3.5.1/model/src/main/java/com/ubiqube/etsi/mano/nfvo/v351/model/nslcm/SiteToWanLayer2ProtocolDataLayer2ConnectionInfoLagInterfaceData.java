package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information for setting up a LAG interface aggregating multiple connectivity service endpoints. 
 */
@Schema(description = "Information for setting up a LAG interface aggregating multiple connectivity service endpoints. ")
@Validated


public class SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData   {
  @JsonProperty("aggregatedEndpoints")
  @Valid
  private List<String> aggregatedEndpoints = new ArrayList<>();

  @JsonProperty("lacpActivation")
  private Boolean lacpActivation = null;

  @JsonProperty("lacpConfig")
  private KeyValuePairs lacpConfig = null;

  public SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData aggregatedEndpoints(List<String> aggregatedEndpoints) {
    this.aggregatedEndpoints = aggregatedEndpoints;
    return this;
  }

  public SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData addAggregatedEndpointsItem(String aggregatedEndpointsItem) {
    this.aggregatedEndpoints.add(aggregatedEndpointsItem);
    return this;
  }

  /**
   * List of the connectivity service endpoints that are to be aggregated. Shall be present if connectionType=\"AGGREGATE_CSE\". In case of aggregating connectivity service endpoints, only one SiteToWanLayer2ProtocolData shall be provided for the whole set of aggregated endpoints. 
   * @return aggregatedEndpoints
   **/
  @Schema(required = true, description = "List of the connectivity service endpoints that are to be aggregated. Shall be present if connectionType=\"AGGREGATE_CSE\". In case of aggregating connectivity service endpoints, only one SiteToWanLayer2ProtocolData shall be provided for the whole set of aggregated endpoints. ")
      @NotNull

    public List<String> getAggregatedEndpoints() {
    return aggregatedEndpoints;
  }

  public void setAggregatedEndpoints(List<String> aggregatedEndpoints) {
    this.aggregatedEndpoints = aggregatedEndpoints;
  }

  public SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData lacpActivation(Boolean lacpActivation) {
    this.lacpActivation = lacpActivation;
    return this;
  }

  /**
   * Indicates whether to activate LACP on the interface. If \"TRUE\", the LACP is to be activated, or \"FALSE\" otherwise. Default value is \"FALSE\". 
   * @return lacpActivation
   **/
  @Schema(required = true, description = "Indicates whether to activate LACP on the interface. If \"TRUE\", the LACP is to be activated, or \"FALSE\" otherwise. Default value is \"FALSE\". ")
      @NotNull

    public Boolean isLacpActivation() {
    return lacpActivation;
  }

  public void setLacpActivation(Boolean lacpActivation) {
    this.lacpActivation = lacpActivation;
  }

  public SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData lacpConfig(KeyValuePairs lacpConfig) {
    this.lacpConfig = lacpConfig;
    return this;
  }

  /**
   * Get lacpConfig
   * @return lacpConfig
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public KeyValuePairs getLacpConfig() {
    return lacpConfig;
  }

  public void setLacpConfig(KeyValuePairs lacpConfig) {
    this.lacpConfig = lacpConfig;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData siteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData = (SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData) o;
    return Objects.equals(this.aggregatedEndpoints, siteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData.aggregatedEndpoints) &&
        Objects.equals(this.lacpActivation, siteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData.lacpActivation) &&
        Objects.equals(this.lacpConfig, siteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData.lacpConfig);
  }

  @Override
  public int hashCode() {
    return Objects.hash(aggregatedEndpoints, lacpActivation, lacpConfig);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SiteToWanLayer2ProtocolDataLayer2ConnectionInfoLagInterfaceData {\n");
    
    sb.append("    aggregatedEndpoints: ").append(toIndentedString(aggregatedEndpoints)).append("\n");
    sb.append("    lacpActivation: ").append(toIndentedString(lacpActivation)).append("\n");
    sb.append("    lacpConfig: ").append(toIndentedString(lacpConfig)).append("\n");
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
