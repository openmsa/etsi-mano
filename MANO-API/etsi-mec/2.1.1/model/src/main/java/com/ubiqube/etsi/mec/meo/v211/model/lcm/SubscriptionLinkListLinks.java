package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.LinkType;
import com.ubiqube.etsi.mec.meo.v211.model.lcm.SubscriptionLinkListLinksSubscriptions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Links to resources related to this resource.
 */
@ApiModel(description = "Links to resources related to this resource.")
@Validated
public class SubscriptionLinkListLinks   {
  @JsonProperty("self")
  private LinkType self = null;

  @JsonProperty("subscriptions")
  @Valid
  private List<SubscriptionLinkListLinksSubscriptions> subscriptions = null;

  public SubscriptionLinkListLinks self(LinkType self) {
    this.self = self;
    return this;
  }

  /**
   * Get self
   * @return self
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public LinkType getSelf() {
    return self;
  }

  public void setSelf(LinkType self) {
    this.self = self;
  }

  public SubscriptionLinkListLinks subscriptions(List<SubscriptionLinkListLinksSubscriptions> subscriptions) {
    this.subscriptions = subscriptions;
    return this;
  }

  public SubscriptionLinkListLinks addSubscriptionsItem(SubscriptionLinkListLinksSubscriptions subscriptionsItem) {
    if (this.subscriptions == null) {
      this.subscriptions = new ArrayList<>();
    }
    this.subscriptions.add(subscriptionsItem);
    return this;
  }

  /**
   * Get subscriptions
   * @return subscriptions
  **/
  @ApiModelProperty(value = "")
      @Valid
    public List<SubscriptionLinkListLinksSubscriptions> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(List<SubscriptionLinkListLinksSubscriptions> subscriptions) {
    this.subscriptions = subscriptions;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionLinkListLinks subscriptionLinkListLinks = (SubscriptionLinkListLinks) o;
    return Objects.equals(this.self, subscriptionLinkListLinks.self) &&
        Objects.equals(this.subscriptions, subscriptionLinkListLinks.subscriptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, subscriptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionLinkListLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    subscriptions: ").append(toIndentedString(subscriptions)).append("\n");
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
