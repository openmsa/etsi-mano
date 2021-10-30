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
package com.ubiqube.etsi.mano.nfvo.v351.model.nslcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents provides input parameters to configure the forwarding behaviour.  It shall comply with the provisions defined in Table 6.5.3.73-1. NOTE 1: If applicable to the algorithm but not provided, default values determined by the VIM or NFVI are expected to be used. NOTE 2: Weight applies to the CP instances in the order they have been created. 
 */
@Schema(description = "This type represents provides input parameters to configure the forwarding behaviour.  It shall comply with the provisions defined in Table 6.5.3.73-1. NOTE 1: If applicable to the algorithm but not provided, default values determined by the VIM or NFVI are expected to be used. NOTE 2: Weight applies to the CP instances in the order they have been created. ")
@Validated


public class ForwardingBehaviourInputParameters   {
  /**
   * May be included if forwarding behaviour is equal to LB. Shall not be included otherwise. Permitted values: * ROUND_ROBIN * LEAST_CONNECTION * LEAST_TRAFFIC * LEAST_RESPONSE_TIME * CHAINED_FAILOVER * SOURCE_IP_HASH * SOURCE_MAC_HASH 
   */
  public enum AlgortihmNameEnum {
    ROUND_ROBIN("ROUND_ROBIN"),
    
    LEAST_CONNECTION("LEAST_CONNECTION"),
    
    LEAST_TRAFFIC("LEAST_TRAFFIC"),
    
    LEAST_RESPONSE_TIME("LEAST_RESPONSE_TIME"),
    
    CHAINED_FAILOVER("CHAINED_FAILOVER"),
    
    SOURCE_IP_HASH("SOURCE_IP_HASH"),
    
    SOURCE_MAC_HASH("SOURCE_MAC_HASH");

    private String value;

    AlgortihmNameEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AlgortihmNameEnum fromValue(String text) {
      for (AlgortihmNameEnum b : AlgortihmNameEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("algortihmName")
  private AlgortihmNameEnum algortihmName = null;

  @JsonProperty("algorithmWeights")
  @Valid
  private List<Integer> algorithmWeights = null;

  public ForwardingBehaviourInputParameters algortihmName(AlgortihmNameEnum algortihmName) {
    this.algortihmName = algortihmName;
    return this;
  }

  /**
   * May be included if forwarding behaviour is equal to LB. Shall not be included otherwise. Permitted values: * ROUND_ROBIN * LEAST_CONNECTION * LEAST_TRAFFIC * LEAST_RESPONSE_TIME * CHAINED_FAILOVER * SOURCE_IP_HASH * SOURCE_MAC_HASH 
   * @return algortihmName
   **/
  @Schema(description = "May be included if forwarding behaviour is equal to LB. Shall not be included otherwise. Permitted values: * ROUND_ROBIN * LEAST_CONNECTION * LEAST_TRAFFIC * LEAST_RESPONSE_TIME * CHAINED_FAILOVER * SOURCE_IP_HASH * SOURCE_MAC_HASH ")
  
    public AlgortihmNameEnum getAlgortihmName() {
    return algortihmName;
  }

  public void setAlgortihmName(AlgortihmNameEnum algortihmName) {
    this.algortihmName = algortihmName;
  }

  public ForwardingBehaviourInputParameters algorithmWeights(List<Integer> algorithmWeights) {
    this.algorithmWeights = algorithmWeights;
    return this;
  }

  public ForwardingBehaviourInputParameters addAlgorithmWeightsItem(Integer algorithmWeightsItem) {
    if (this.algorithmWeights == null) {
      this.algorithmWeights = new ArrayList<>();
    }
    this.algorithmWeights.add(algorithmWeightsItem);
    return this;
  }

  /**
   * Percentage of messages sent to a CP instance. May be included if applicable to  the algorithm. See note 1 and note 2. 
   * @return algorithmWeights
   **/
  @Schema(description = "Percentage of messages sent to a CP instance. May be included if applicable to  the algorithm. See note 1 and note 2. ")
  
    public List<Integer> getAlgorithmWeights() {
    return algorithmWeights;
  }

  public void setAlgorithmWeights(List<Integer> algorithmWeights) {
    this.algorithmWeights = algorithmWeights;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ForwardingBehaviourInputParameters forwardingBehaviourInputParameters = (ForwardingBehaviourInputParameters) o;
    return Objects.equals(this.algortihmName, forwardingBehaviourInputParameters.algortihmName) &&
        Objects.equals(this.algorithmWeights, forwardingBehaviourInputParameters.algorithmWeights);
  }

  @Override
  public int hashCode() {
    return Objects.hash(algortihmName, algorithmWeights);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ForwardingBehaviourInputParameters {\n");
    
    sb.append("    algortihmName: ").append(toIndentedString(algortihmName)).append("\n");
    sb.append("    algorithmWeights: ").append(toIndentedString(algorithmWeights)).append("\n");
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
