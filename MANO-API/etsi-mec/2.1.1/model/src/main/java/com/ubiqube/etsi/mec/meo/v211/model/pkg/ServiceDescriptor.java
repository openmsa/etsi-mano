package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.CategoryRef;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.TransportsSupported;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The ServiceDescriptor data type describes a MEC service produced by a service-providing MEC application.&#x27;
 */
@ApiModel(description = "'The ServiceDescriptor data type describes a MEC service produced by a service-providing MEC application.'")
@Validated
public class ServiceDescriptor   {
  @JsonProperty("serName")
  private String serName = null;

  @JsonProperty("serCategory")
  private CategoryRef serCategory = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("transportsSupported")
  private TransportsSupported transportsSupported = null;

  public ServiceDescriptor serName(String serName) {
    this.serName = serName;
    return this;
  }

  /**
   * Get serName
   * @return serName
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getSerName() {
    return serName;
  }

  public void setSerName(String serName) {
    this.serName = serName;
  }

  public ServiceDescriptor serCategory(CategoryRef serCategory) {
    this.serCategory = serCategory;
    return this;
  }

  /**
   * Get serCategory
   * @return serCategory
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public CategoryRef getSerCategory() {
    return serCategory;
  }

  public void setSerCategory(CategoryRef serCategory) {
    this.serCategory = serCategory;
  }

  public ServiceDescriptor version(String version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public ServiceDescriptor transportsSupported(TransportsSupported transportsSupported) {
    this.transportsSupported = transportsSupported;
    return this;
  }

  /**
   * Get transportsSupported
   * @return transportsSupported
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public TransportsSupported getTransportsSupported() {
    return transportsSupported;
  }

  public void setTransportsSupported(TransportsSupported transportsSupported) {
    this.transportsSupported = transportsSupported;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ServiceDescriptor serviceDescriptor = (ServiceDescriptor) o;
    return Objects.equals(this.serName, serviceDescriptor.serName) &&
        Objects.equals(this.serCategory, serviceDescriptor.serCategory) &&
        Objects.equals(this.version, serviceDescriptor.version) &&
        Objects.equals(this.transportsSupported, serviceDescriptor.transportsSupported);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serName, serCategory, version, transportsSupported);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ServiceDescriptor {\n");
    
    sb.append("    serName: ").append(toIndentedString(serName)).append("\n");
    sb.append("    serCategory: ").append(toIndentedString(serCategory)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    transportsSupported: ").append(toIndentedString(transportsSupported)).append("\n");
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
