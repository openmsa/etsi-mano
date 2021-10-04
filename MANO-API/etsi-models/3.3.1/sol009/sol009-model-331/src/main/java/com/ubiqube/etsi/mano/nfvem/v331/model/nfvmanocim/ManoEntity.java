package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoConfigurableParams;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoEntityComponent;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoEntityEnumType;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoEntityLinks;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoEntityManoApplicationState;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.ManoService;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.NfvoSpecificInfo;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.VimSpecificInfo;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanocim.VnfmSpecificInfo;
import com.fasterxml.jackson.annotation.JsonCreator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an NFV-MANO functional entity. 
 */
@ApiModel(description = "This type represents an NFV-MANO functional entity. ")
@Validated
public class ManoEntity   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("type")
  private ManoEntityEnumType type = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("provider")
  private String provider = null;

  @JsonProperty("softwareVersion")
  private Object softwareVersion = null;

  @JsonProperty("manoEntityComponents")
  @Valid
  private List<ManoEntityComponent> manoEntityComponents = null;

  @JsonProperty("manoServices")
  @Valid
  private List<ManoService> manoServices = null;

  @JsonProperty("manoConfigurableParams")
  private ManoConfigurableParams manoConfigurableParams = null;

  @JsonProperty("manoApplicationState")
  private ManoEntityManoApplicationState manoApplicationState = null;

  @JsonProperty("nfvoSpecificInfo")
  private NfvoSpecificInfo nfvoSpecificInfo = null;

  @JsonProperty("vnfmSpecificInfo")
  private VnfmSpecificInfo vnfmSpecificInfo = null;

  @JsonProperty("vimSpecificInfo")
  private VimSpecificInfo vimSpecificInfo = null;

  @JsonProperty("_links")
  private ManoEntityLinks _links = null;

  public ManoEntity id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ManoEntity type(ManoEntityEnumType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ManoEntityEnumType getType() {
    return type;
  }

  public void setType(ManoEntityEnumType type) {
    this.type = type;
  }

  public ManoEntity name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Human-readable name of the NFV-MANO functional entity. This attribute can be modified with the PATCH method. 
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Human-readable name of the NFV-MANO functional entity. This attribute can be modified with the PATCH method. ")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ManoEntity description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Human-readable description of the NFV-MANO functional entity. This attribute can be modified with the PATCH method. 
   * @return description
  **/
  @ApiModelProperty(required = true, value = "Human-readable description of the NFV-MANO functional entity. This attribute can be modified with the PATCH method. ")
      @NotNull

    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ManoEntity provider(String provider) {
    this.provider = provider;
    return this;
  }

  /**
   * Information about the provider of the NFV-MANO functional entity.  It typically includes the name of the provider. 
   * @return provider
  **/
  @ApiModelProperty(required = true, value = "Information about the provider of the NFV-MANO functional entity.  It typically includes the name of the provider. ")
      @NotNull

    public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public ManoEntity softwareVersion(Object softwareVersion) {
    this.softwareVersion = softwareVersion;
    return this;
  }

  /**
   * The version of the software of the NFV-MANO functional entity. $ref: \"../components/SOL009_schemas.yaml#/components/schemas/Version\" 
   * @return softwareVersion
  **/
  @ApiModelProperty(required = true, value = "The version of the software of the NFV-MANO functional entity. $ref: \"../components/SOL009_schemas.yaml#/components/schemas/Version\" ")
      @NotNull

    public Object getSoftwareVersion() {
    return softwareVersion;
  }

  public void setSoftwareVersion(Object softwareVersion) {
    this.softwareVersion = softwareVersion;
  }

  public ManoEntity manoEntityComponents(List<ManoEntityComponent> manoEntityComponents) {
    this.manoEntityComponents = manoEntityComponents;
    return this;
  }

  public ManoEntity addManoEntityComponentsItem(ManoEntityComponent manoEntityComponentsItem) {
    if (this.manoEntityComponents == null) {
      this.manoEntityComponents = new ArrayList<>();
    }
    this.manoEntityComponents.add(manoEntityComponentsItem);
    return this;
  }

  /**
   * The deployed NFV-MANO functional entity components which realize the  NFV-MANO functional entity.  NOTE: It is optional for the API producer to support the \"manoEntityComponents\"  attribute. 
   * @return manoEntityComponents
  **/
  @ApiModelProperty(value = "The deployed NFV-MANO functional entity components which realize the  NFV-MANO functional entity.  NOTE: It is optional for the API producer to support the \"manoEntityComponents\"  attribute. ")
      @Valid
    public List<ManoEntityComponent> getManoEntityComponents() {
    return manoEntityComponents;
  }

  public void setManoEntityComponents(List<ManoEntityComponent> manoEntityComponents) {
    this.manoEntityComponents = manoEntityComponents;
  }

  public ManoEntity manoServices(List<ManoService> manoServices) {
    this.manoServices = manoServices;
    return this;
  }

  public ManoEntity addManoServicesItem(ManoService manoServicesItem) {
    if (this.manoServices == null) {
      this.manoServices = new ArrayList<>();
    }
    this.manoServices.add(manoServicesItem);
    return this;
  }

  /**
   * Information about the NFV-MANO services provided by the NFV-MANO  functional entity. 
   * @return manoServices
  **/
  @ApiModelProperty(value = "Information about the NFV-MANO services provided by the NFV-MANO  functional entity. ")
      @Valid
    public List<ManoService> getManoServices() {
    return manoServices;
  }

  public void setManoServices(List<ManoService> manoServices) {
    this.manoServices = manoServices;
  }

  public ManoEntity manoConfigurableParams(ManoConfigurableParams manoConfigurableParams) {
    this.manoConfigurableParams = manoConfigurableParams;
    return this;
  }

  /**
   * Get manoConfigurableParams
   * @return manoConfigurableParams
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ManoConfigurableParams getManoConfigurableParams() {
    return manoConfigurableParams;
  }

  public void setManoConfigurableParams(ManoConfigurableParams manoConfigurableParams) {
    this.manoConfigurableParams = manoConfigurableParams;
  }

  public ManoEntity manoApplicationState(ManoEntityManoApplicationState manoApplicationState) {
    this.manoApplicationState = manoApplicationState;
    return this;
  }

  /**
   * Get manoApplicationState
   * @return manoApplicationState
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public ManoEntityManoApplicationState getManoApplicationState() {
    return manoApplicationState;
  }

  public void setManoApplicationState(ManoEntityManoApplicationState manoApplicationState) {
    this.manoApplicationState = manoApplicationState;
  }

  public ManoEntity nfvoSpecificInfo(NfvoSpecificInfo nfvoSpecificInfo) {
    this.nfvoSpecificInfo = nfvoSpecificInfo;
    return this;
  }

  /**
   * Get nfvoSpecificInfo
   * @return nfvoSpecificInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public NfvoSpecificInfo getNfvoSpecificInfo() {
    return nfvoSpecificInfo;
  }

  public void setNfvoSpecificInfo(NfvoSpecificInfo nfvoSpecificInfo) {
    this.nfvoSpecificInfo = nfvoSpecificInfo;
  }

  public ManoEntity vnfmSpecificInfo(VnfmSpecificInfo vnfmSpecificInfo) {
    this.vnfmSpecificInfo = vnfmSpecificInfo;
    return this;
  }

  /**
   * Get vnfmSpecificInfo
   * @return vnfmSpecificInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public VnfmSpecificInfo getVnfmSpecificInfo() {
    return vnfmSpecificInfo;
  }

  public void setVnfmSpecificInfo(VnfmSpecificInfo vnfmSpecificInfo) {
    this.vnfmSpecificInfo = vnfmSpecificInfo;
  }

  public ManoEntity vimSpecificInfo(VimSpecificInfo vimSpecificInfo) {
    this.vimSpecificInfo = vimSpecificInfo;
    return this;
  }

  /**
   * Get vimSpecificInfo
   * @return vimSpecificInfo
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public VimSpecificInfo getVimSpecificInfo() {
    return vimSpecificInfo;
  }

  public void setVimSpecificInfo(VimSpecificInfo vimSpecificInfo) {
    this.vimSpecificInfo = vimSpecificInfo;
  }

  public ManoEntity _links(ManoEntityLinks _links) {
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
    public ManoEntityLinks getLinks() {
    return _links;
  }

  public void setLinks(ManoEntityLinks _links) {
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
    ManoEntity manoEntity = (ManoEntity) o;
    return Objects.equals(this.id, manoEntity.id) &&
        Objects.equals(this.type, manoEntity.type) &&
        Objects.equals(this.name, manoEntity.name) &&
        Objects.equals(this.description, manoEntity.description) &&
        Objects.equals(this.provider, manoEntity.provider) &&
        Objects.equals(this.softwareVersion, manoEntity.softwareVersion) &&
        Objects.equals(this.manoEntityComponents, manoEntity.manoEntityComponents) &&
        Objects.equals(this.manoServices, manoEntity.manoServices) &&
        Objects.equals(this.manoConfigurableParams, manoEntity.manoConfigurableParams) &&
        Objects.equals(this.manoApplicationState, manoEntity.manoApplicationState) &&
        Objects.equals(this.nfvoSpecificInfo, manoEntity.nfvoSpecificInfo) &&
        Objects.equals(this.vnfmSpecificInfo, manoEntity.vnfmSpecificInfo) &&
        Objects.equals(this.vimSpecificInfo, manoEntity.vimSpecificInfo) &&
        Objects.equals(this._links, manoEntity._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, description, provider, softwareVersion, manoEntityComponents, manoServices, manoConfigurableParams, manoApplicationState, nfvoSpecificInfo, vnfmSpecificInfo, vimSpecificInfo, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManoEntity {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    softwareVersion: ").append(toIndentedString(softwareVersion)).append("\n");
    sb.append("    manoEntityComponents: ").append(toIndentedString(manoEntityComponents)).append("\n");
    sb.append("    manoServices: ").append(toIndentedString(manoServices)).append("\n");
    sb.append("    manoConfigurableParams: ").append(toIndentedString(manoConfigurableParams)).append("\n");
    sb.append("    manoApplicationState: ").append(toIndentedString(manoApplicationState)).append("\n");
    sb.append("    nfvoSpecificInfo: ").append(toIndentedString(nfvoSpecificInfo)).append("\n");
    sb.append("    vnfmSpecificInfo: ").append(toIndentedString(vnfmSpecificInfo)).append("\n");
    sb.append("    vimSpecificInfo: ").append(toIndentedString(vimSpecificInfo)).append("\n");
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
