package com.ubiqube.etsi.mano.vnfm.v331.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents the VNFD which is contained in a VNF snapshot package. 
 */
@Schema(description = "This type represents the VNFD which is contained in a VNF snapshot package. ")
@Validated


public class VnfdInfo   {
  @JsonProperty("avnfdId")
  private String avnfdId = null;

  @JsonProperty("vnfdPath")
  private String vnfdPath = null;

  @JsonProperty("checksum")
  private String checksum = null;

  @JsonProperty("isEncrypted")
  private Boolean isEncrypted = null;

  public VnfdInfo avnfdId(String avnfdId) {
    this.avnfdId = avnfdId;
    return this;
  }

  /**
   * Get avnfdId
   * @return avnfdId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getAvnfdId() {
    return avnfdId;
  }

  public void setAvnfdId(String avnfdId) {
    this.avnfdId = avnfdId;
  }

  public VnfdInfo vnfdPath(String vnfdPath) {
    this.vnfdPath = vnfdPath;
    return this;
  }

  /**
   * Get vnfdPath
   * @return vnfdPath
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfdPath() {
    return vnfdPath;
  }

  public void setVnfdPath(String vnfdPath) {
    this.vnfdPath = vnfdPath;
  }

  public VnfdInfo checksum(String checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Get checksum
   * @return checksum
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getChecksum() {
    return checksum;
  }

  public void setChecksum(String checksum) {
    this.checksum = checksum;
  }

  public VnfdInfo isEncrypted(Boolean isEncrypted) {
    this.isEncrypted = isEncrypted;
    return this;
  }

  /**
   * Get isEncrypted
   * @return isEncrypted
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Boolean getIsEncrypted() {
    return isEncrypted;
  }

  public void setIsEncrypted(Boolean isEncrypted) {
    this.isEncrypted = isEncrypted;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfdInfo vnfdInfo = (VnfdInfo) o;
    return Objects.equals(this.avnfdId, vnfdInfo.avnfdId) &&
        Objects.equals(this.vnfdPath, vnfdInfo.vnfdPath) &&
        Objects.equals(this.checksum, vnfdInfo.checksum) &&
        Objects.equals(this.isEncrypted, vnfdInfo.isEncrypted);
  }

  @Override
  public int hashCode() {
    return Objects.hash(avnfdId, vnfdPath, checksum, isEncrypted);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfdInfo {\n");
    
    sb.append("    avnfdId: ").append(toIndentedString(avnfdId)).append("\n");
    sb.append("    vnfdPath: ").append(toIndentedString(vnfdPath)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    isEncrypted: ").append(toIndentedString(isEncrypted)).append("\n");
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
