package com.ubiqube.etsi.mec.meo.v211.model.pkg;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mec.meo.v211.model.pkg.IpAddressType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DNSRuleDescriptor
 */
@Validated
public class DNSRuleDescriptor   {
  @JsonProperty("dnsRuleId")
  private String dnsRuleId = null;

  @JsonProperty("domainName")
  private String domainName = null;

  @JsonProperty("ipAddress")
  private String ipAddress = null;

  @JsonProperty("ipAddressType")
  private IpAddressType ipAddressType = null;

  @JsonProperty("ttl")
  private Integer ttl = null;

  public DNSRuleDescriptor dnsRuleId(String dnsRuleId) {
    this.dnsRuleId = dnsRuleId;
    return this;
  }

  /**
   * Identifies the DNS Rule
   * @return dnsRuleId
  **/
  @ApiModelProperty(required = true, value = "Identifies the DNS Rule")
      @NotNull

    public String getDnsRuleId() {
    return dnsRuleId;
  }

  public void setDnsRuleId(String dnsRuleId) {
    this.dnsRuleId = dnsRuleId;
  }

  public DNSRuleDescriptor domainName(String domainName) {
    this.domainName = domainName;
    return this;
  }

  /**
   * FQDN of the DNS rule
   * @return domainName
  **/
  @ApiModelProperty(required = true, value = "FQDN of the DNS rule")
      @NotNull

    public String getDomainName() {
    return domainName;
  }

  public void setDomainName(String domainName) {
    this.domainName = domainName;
  }

  public DNSRuleDescriptor ipAddress(String ipAddress) {
    this.ipAddress = ipAddress;
    return this;
  }

  /**
   * IP address given by the DNS rule
   * @return ipAddress
  **/
  @ApiModelProperty(required = true, value = "IP address given by the DNS rule")
      @NotNull

    public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public DNSRuleDescriptor ipAddressType(IpAddressType ipAddressType) {
    this.ipAddressType = ipAddressType;
    return this;
  }

  /**
   * Get ipAddressType
   * @return ipAddressType
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public IpAddressType getIpAddressType() {
    return ipAddressType;
  }

  public void setIpAddressType(IpAddressType ipAddressType) {
    this.ipAddressType = ipAddressType;
  }

  public DNSRuleDescriptor ttl(Integer ttl) {
    this.ttl = ttl;
    return this;
  }

  /**
   * Time-to-live value
   * @return ttl
  **/
  @ApiModelProperty(value = "Time-to-live value")
  
    public Integer getTtl() {
    return ttl;
  }

  public void setTtl(Integer ttl) {
    this.ttl = ttl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DNSRuleDescriptor dnSRuleDescriptor = (DNSRuleDescriptor) o;
    return Objects.equals(this.dnsRuleId, dnSRuleDescriptor.dnsRuleId) &&
        Objects.equals(this.domainName, dnSRuleDescriptor.domainName) &&
        Objects.equals(this.ipAddress, dnSRuleDescriptor.ipAddress) &&
        Objects.equals(this.ipAddressType, dnSRuleDescriptor.ipAddressType) &&
        Objects.equals(this.ttl, dnSRuleDescriptor.ttl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dnsRuleId, domainName, ipAddress, ipAddressType, ttl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DNSRuleDescriptor {\n");
    
    sb.append("    dnsRuleId: ").append(toIndentedString(dnsRuleId)).append("\n");
    sb.append("    domainName: ").append(toIndentedString(domainName)).append("\n");
    sb.append("    ipAddress: ").append(toIndentedString(ipAddress)).append("\n");
    sb.append("    ipAddressType: ").append(toIndentedString(ipAddressType)).append("\n");
    sb.append("    ttl: ").append(toIndentedString(ttl)).append("\n");
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
