package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.SubscriptionLinkListLinks;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * &#x27;The data type represents a subscription link list of notification on application lifecycle management. &#x27;
 */
@ApiModel(description = "'The data type represents a subscription link list of notification on application lifecycle management. '")
@Validated
public class SubscriptionLinkList   {
  @JsonProperty("_links")
  private SubscriptionLinkListLinks _links = null;

  public SubscriptionLinkList _links(SubscriptionLinkListLinks _links) {
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
    public SubscriptionLinkListLinks getLinks() {
    return _links;
  }

  public void setLinks(SubscriptionLinkListLinks _links) {
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
    SubscriptionLinkList subscriptionLinkList = (SubscriptionLinkList) o;
    return Objects.equals(this._links, subscriptionLinkList._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionLinkList {\n");
    
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
