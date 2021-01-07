package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.LinkType;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.SubscriptionsAppPkgSubscription;
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
public class AppPkgSubscriptionLinkListLinks   {
  @JsonProperty("self")
  private LinkType self = null;

  @JsonProperty("subscriptions")
  @Valid
  private List<SubscriptionsAppPkgSubscription> subscriptions = null;

  public AppPkgSubscriptionLinkListLinks self(LinkType self) {
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

  public AppPkgSubscriptionLinkListLinks subscriptions(List<SubscriptionsAppPkgSubscription> subscriptions) {
    this.subscriptions = subscriptions;
    return this;
  }

  public AppPkgSubscriptionLinkListLinks addSubscriptionsItem(SubscriptionsAppPkgSubscription subscriptionsItem) {
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
    public List<SubscriptionsAppPkgSubscription> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(List<SubscriptionsAppPkgSubscription> subscriptions) {
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
    AppPkgSubscriptionLinkListLinks appPkgSubscriptionLinkListLinks = (AppPkgSubscriptionLinkListLinks) o;
    return Objects.equals(this.self, appPkgSubscriptionLinkListLinks.self) &&
        Objects.equals(this.subscriptions, appPkgSubscriptionLinkListLinks.subscriptions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, subscriptions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AppPkgSubscriptionLinkListLinks {\n");
    
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
