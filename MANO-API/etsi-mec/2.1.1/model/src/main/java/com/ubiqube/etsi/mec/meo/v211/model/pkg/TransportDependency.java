package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.TransportDescriptor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TransportDependency
 */
@Validated
public class TransportDependency   {
  @JsonProperty("labels")
  @Valid
  private List<String> labels = new ArrayList<>();

  @JsonProperty("serializers")
  @Valid
  private List<String> serializers = new ArrayList<>();

  @JsonProperty("transport")
  private TransportDescriptor transport = null;

  public TransportDependency labels(List<String> labels) {
    this.labels = labels;
    return this;
  }

  public TransportDependency addLabelsItem(String labelsItem) {
    this.labels.add(labelsItem);
    return this;
  }

  /**
   * Set of labels that allow to define groups of transport bindings. The mechanism of the grouping is defined below this table.
   * @return labels
  **/
  @ApiModelProperty(required = true, value = "Set of labels that allow to define groups of transport bindings. The mechanism of the grouping is defined below this table.")
      @NotNull

  @Size(min=1)   public List<String> getLabels() {
    return labels;
  }

  public void setLabels(List<String> labels) {
    this.labels = labels;
  }

  public TransportDependency serializers(List<String> serializers) {
    this.serializers = serializers;
    return this;
  }

  public TransportDependency addSerializersItem(String serializersItem) {
    this.serializers.add(serializersItem);
    return this;
  }

  /**
   * Information about the serializers in this transport binding, as defined in the SerializerTypes type in ETSI GS MEC 011 [i.4]. Support for at least one of the entries is required in conjunction with the transport.
   * @return serializers
  **/
  @ApiModelProperty(required = true, value = "Information about the serializers in this transport binding, as defined in the SerializerTypes type in ETSI GS MEC 011 [i.4]. Support for at least one of the entries is required in conjunction with the transport.")
      @NotNull

  @Size(min=1)   public List<String> getSerializers() {
    return serializers;
  }

  public void setSerializers(List<String> serializers) {
    this.serializers = serializers;
  }

  public TransportDependency transport(TransportDescriptor transport) {
    this.transport = transport;
    return this;
  }

  /**
   * Get transport
   * @return transport
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public TransportDescriptor getTransport() {
    return transport;
  }

  public void setTransport(TransportDescriptor transport) {
    this.transport = transport;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransportDependency transportDependency = (TransportDependency) o;
    return Objects.equals(this.labels, transportDependency.labels) &&
        Objects.equals(this.serializers, transportDependency.serializers) &&
        Objects.equals(this.transport, transportDependency.transport);
  }

  @Override
  public int hashCode() {
    return Objects.hash(labels, serializers, transport);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TransportDependency {\n");
    
    sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
    sb.append("    serializers: ").append(toIndentedString(serializers)).append("\n");
    sb.append("    transport: ").append(toIndentedString(transport)).append("\n");
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
