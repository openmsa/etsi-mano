package com.ubiqube.etsi.mano.nfvo.v331.model.nsd;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvo.v331.model.nsd.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents attribute modifications for an individual PNF descriptor resource based on the \&quot;PnfdInfo\&quot; data type. The attributes of \&quot;PnfdInfo\&quot; that can be modified are included in the \&quot;PnfdInfoModifications\&quot; data type. 
 */
@Schema(description = "This type represents attribute modifications for an individual PNF descriptor resource based on the \"PnfdInfo\" data type. The attributes of \"PnfdInfo\" that can be modified are included in the \"PnfdInfoModifications\" data type. ")
@Validated


public class PnfdInfoModifications   {
  @JsonProperty("userDefinedData")
  private KeyValuePairs userDefinedData = null;

  public PnfdInfoModifications userDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
    return this;
  }

  /**
   * Get userDefinedData
   * @return userDefinedData
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public KeyValuePairs getUserDefinedData() {
    return userDefinedData;
  }

  public void setUserDefinedData(KeyValuePairs userDefinedData) {
    this.userDefinedData = userDefinedData;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PnfdInfoModifications pnfdInfoModifications = (PnfdInfoModifications) o;
    return Objects.equals(this.userDefinedData, pnfdInfoModifications.userDefinedData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userDefinedData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PnfdInfoModifications {\n");
    
    sb.append("    userDefinedData: ").append(toIndentedString(userDefinedData)).append("\n");
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
