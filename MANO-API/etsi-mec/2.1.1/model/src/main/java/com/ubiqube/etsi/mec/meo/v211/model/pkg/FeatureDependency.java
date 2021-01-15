package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * FeatureDependency
 */
@Validated
public class FeatureDependency   {
  @JsonProperty("featureName")
  private String featureName = null;

  @JsonProperty("version")
  private String version = null;

  public FeatureDependency featureName(String featureName) {
    this.featureName = featureName;
    return this;
  }

  /**
   * The name of the feature, for example, UserApps, UEIdentity, etc.
   * @return featureName
  **/
  @ApiModelProperty(required = true, value = "The name of the feature, for example, UserApps, UEIdentity, etc.")
      @NotNull

    public String getFeatureName() {
    return featureName;
  }

  public void setFeatureName(String featureName) {
    this.featureName = featureName;
  }

  public FeatureDependency version(String version) {
    this.version = version;
    return this;
  }

  /**
   * The version of the feature.
   * @return version
  **/
  @ApiModelProperty(required = true, value = "The version of the feature.")
      @NotNull

    public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeatureDependency featureDependency = (FeatureDependency) o;
    return Objects.equals(this.featureName, featureDependency.featureName) &&
        Objects.equals(this.version, featureDependency.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(featureName, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeatureDependency {\n");
    
    sb.append("    featureName: ").append(toIndentedString(featureName)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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
