package com.ubiqube.etsi.mec.meo.v211.model.lcm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * A link list to the subscriptions
 */
@ApiModel(description = "A link list to the subscriptions")
@Validated
public class SubscriptionLinkListLinksSubscriptions   {
  @JsonProperty("href")
  private String href = null;

  /**
   * Gets or Sets subscriptionType
   */
  public enum SubscriptionTypeEnum {
    APPINSTANCESTATECHANGE("AppInstanceStateChange"),
    
    APPLCMOPOCCSTATECHANGE("AppLcmOpOccStateChange");

    private String value;

    SubscriptionTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SubscriptionTypeEnum fromValue(String text) {
      for (SubscriptionTypeEnum b : SubscriptionTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("subscriptionType")
  private SubscriptionTypeEnum subscriptionType = null;

  public SubscriptionLinkListLinksSubscriptions href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Get href
   * @return href
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public SubscriptionLinkListLinksSubscriptions subscriptionType(SubscriptionTypeEnum subscriptionType) {
    this.subscriptionType = subscriptionType;
    return this;
  }

  /**
   * Get subscriptionType
   * @return subscriptionType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public SubscriptionTypeEnum getSubscriptionType() {
    return subscriptionType;
  }

  public void setSubscriptionType(SubscriptionTypeEnum subscriptionType) {
    this.subscriptionType = subscriptionType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubscriptionLinkListLinksSubscriptions subscriptionLinkListLinksSubscriptions = (SubscriptionLinkListLinksSubscriptions) o;
    return Objects.equals(this.href, subscriptionLinkListLinksSubscriptions.href) &&
        Objects.equals(this.subscriptionType, subscriptionLinkListLinksSubscriptions.subscriptionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, subscriptionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubscriptionLinkListLinksSubscriptions {\n");
    
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    subscriptionType: ").append(toIndentedString(subscriptionType)).append("\n");
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
