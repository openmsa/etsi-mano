package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v351.model.nslcm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TerminateNsData
 */
@Validated


public class TerminateNsData   {
  @JsonProperty("additionalParamsforNs")
  private KeyValuePairs additionalParamsforNs = null;

  public TerminateNsData additionalParamsforNs(KeyValuePairs additionalParamsforNs) {
    this.additionalParamsforNs = additionalParamsforNs;
    return this;
  }

  /**
   * Get additionalParamsforNs
   * @return additionalParamsforNs
   **/
  @Schema(description = "")
  
    @Valid
    public KeyValuePairs getAdditionalParamsforNs() {
    return additionalParamsforNs;
  }

  public void setAdditionalParamsforNs(KeyValuePairs additionalParamsforNs) {
    this.additionalParamsforNs = additionalParamsforNs;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TerminateNsData terminateNsData = (TerminateNsData) o;
    return Objects.equals(this.additionalParamsforNs, terminateNsData.additionalParamsforNs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(additionalParamsforNs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TerminateNsData {\n");
    
    sb.append("    additionalParamsforNs: ").append(toIndentedString(additionalParamsforNs)).append("\n");
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
