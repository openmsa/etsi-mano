package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.AppPkgSubscriptionLinkListLinks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The data type represents a subscription link list of notification on application package management&#x27;
 */
@ApiModel(description = "'The data type represents a subscription link list of notification on application package management'")
@Validated
public class AppPkgSubscriptionLinkList   {
  @JsonProperty("_links")
  private AppPkgSubscriptionLinkListLinks _links = null;

  public AppPkgSubscriptionLinkList _links(AppPkgSubscriptionLinkListLinks _links) {
    this._links = _links;
    return this;
  }

  /**
   * Get _links
   * @return _links
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public AppPkgSubscriptionLinkListLinks getLinks() {
    return _links;
  }

  public void setLinks(AppPkgSubscriptionLinkListLinks _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppPkgSubscriptionLinkList appPkgSubscriptionLinkList = (AppPkgSubscriptionLinkList) o;
    return Objects.equals(this._links, appPkgSubscriptionLinkList._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppPkgSubscriptionLinkList {\n");
    
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
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
