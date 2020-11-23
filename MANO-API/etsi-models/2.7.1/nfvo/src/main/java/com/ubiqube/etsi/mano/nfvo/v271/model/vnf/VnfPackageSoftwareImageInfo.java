/**
 *     Copyright (C) 2019-2020 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.ubiqube.etsi.mano.nfvo.v271.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.nfvo.v271.model.vnf.Checksum;
import com.ubiqube.etsi.mano.nfvo.v271.model.vnf.DateTime2;
import com.ubiqube.etsi.mano.nfvo.v271.model.vnf.KeyValuePairs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an artifact contained in a VNF package which represents a software image.   
 */
@ApiModel(description = "This type represents an artifact contained in a VNF package which represents a software image.   ")
@Validated
public class VnfPackageSoftwareImageInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("provider")
  private String provider = null;

  @JsonProperty("version")
  private String version = null;

  @JsonProperty("checksum")
  private Checksum checksum = null;

  @JsonProperty("isEncrypted")
  private Boolean isEncrypted = null;

  /**
   * Container format indicates whether the software image is in a file format that also contains meta-data about the actual software. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ram disk image format - BARE: the image does not have a container or meta-data envelope - DOCKER: docker container format - OVA: OVF package in a tar file - OVF: OVF container format 
   */
  public enum ContainerFormatEnum {
    AKI("AKI"),
    
    AMI("AMI"),
    
    ARI("ARI"),
    
    BARE("BARE"),
    
    DOCKER("DOCKER"),
    
    OVA("OVA"),
    
    OVF("OVF");

    private String value;

    ContainerFormatEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ContainerFormatEnum fromValue(String text) {
      for (ContainerFormatEnum b : ContainerFormatEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("containerFormat")
  private ContainerFormatEnum containerFormat = null;

  /**
   * Disk format of a software image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc, such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format 
   */
  public enum DiskFormatEnum {
    AKI("AKI"),
    
    AMI("AMI"),
    
    ARI("ARI"),
    
    ISO("ISO"),
    
    QCOW2("QCOW2"),
    
    RAW("RAW"),
    
    VDI("VDI"),
    
    VHD("VHD"),
    
    VHDX("VHDX"),
    
    VMDK("VMDK");

    private String value;

    DiskFormatEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DiskFormatEnum fromValue(String text) {
      for (DiskFormatEnum b : DiskFormatEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("diskFormat")
  private DiskFormatEnum diskFormat = null;

  @JsonProperty("createdAt")
  private DateTime2 createdAt = null;

  @JsonProperty("minDisk")
  private Integer minDisk = null;

  @JsonProperty("minRam")
  private Integer minRam = null;

  @JsonProperty("size")
  private Integer size = null;

  @JsonProperty("userMetadata")
  private KeyValuePairs userMetadata = null;

  @JsonProperty("imagePath")
  private String imagePath = null;

  public VnfPackageSoftwareImageInfo id(String id) {
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

  public VnfPackageSoftwareImageInfo name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the software image. 
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Name of the software image. ")
      @NotNull

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public VnfPackageSoftwareImageInfo provider(String provider) {
    this.provider = provider;
    return this;
  }

  /**
   * Provider of the software image. 
   * @return provider
  **/
  @ApiModelProperty(required = true, value = "Provider of the software image. ")
      @NotNull

    public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public VnfPackageSoftwareImageInfo version(String version) {
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

  public VnfPackageSoftwareImageInfo checksum(Checksum checksum) {
    this.checksum = checksum;
    return this;
  }

  /**
   * Get checksum
   * @return checksum
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public Checksum getChecksum() {
    return checksum;
  }

  public void setChecksum(Checksum checksum) {
    this.checksum = checksum;
  }

  public VnfPackageSoftwareImageInfo isEncrypted(Boolean isEncrypted) {
    this.isEncrypted = isEncrypted;
    return this;
  }

  /**
   * Reflects whether the image is encrypted (true) or not (false). 
   * @return isEncrypted
  **/
  @ApiModelProperty(required = true, value = "Reflects whether the image is encrypted (true) or not (false). ")
      @NotNull

    public Boolean isIsEncrypted() {
    return isEncrypted;
  }

  public void setIsEncrypted(Boolean isEncrypted) {
    this.isEncrypted = isEncrypted;
  }

  public VnfPackageSoftwareImageInfo containerFormat(ContainerFormatEnum containerFormat) {
    this.containerFormat = containerFormat;
    return this;
  }

  /**
   * Container format indicates whether the software image is in a file format that also contains meta-data about the actual software. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ram disk image format - BARE: the image does not have a container or meta-data envelope - DOCKER: docker container format - OVA: OVF package in a tar file - OVF: OVF container format 
   * @return containerFormat
  **/
  @ApiModelProperty(required = true, value = "Container format indicates whether the software image is in a file format that also contains meta-data about the actual software. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ram disk image format - BARE: the image does not have a container or meta-data envelope - DOCKER: docker container format - OVA: OVF package in a tar file - OVF: OVF container format ")
      @NotNull

    public ContainerFormatEnum getContainerFormat() {
    return containerFormat;
  }

  public void setContainerFormat(ContainerFormatEnum containerFormat) {
    this.containerFormat = containerFormat;
  }

  public VnfPackageSoftwareImageInfo diskFormat(DiskFormatEnum diskFormat) {
    this.diskFormat = diskFormat;
    return this;
  }

  /**
   * Disk format of a software image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc, such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format 
   * @return diskFormat
  **/
  @ApiModelProperty(required = true, value = "Disk format of a software image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc, such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format ")
      @NotNull

    public DiskFormatEnum getDiskFormat() {
    return diskFormat;
  }

  public void setDiskFormat(DiskFormatEnum diskFormat) {
    this.diskFormat = diskFormat;
  }

  public VnfPackageSoftwareImageInfo createdAt(DateTime2 createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(required = true, value = "")
      @NotNull

    @Valid
    public DateTime2 getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(DateTime2 createdAt) {
    this.createdAt = createdAt;
  }

  public VnfPackageSoftwareImageInfo minDisk(Integer minDisk) {
    this.minDisk = minDisk;
    return this;
  }

  /**
   * The minimal disk for this software image in bytes. 
   * minimum: 0
   * @return minDisk
  **/
  @ApiModelProperty(required = true, value = "The minimal disk for this software image in bytes. ")
      @NotNull

  @Min(0)  public Integer getMinDisk() {
    return minDisk;
  }

  public void setMinDisk(Integer minDisk) {
    this.minDisk = minDisk;
  }

  public VnfPackageSoftwareImageInfo minRam(Integer minRam) {
    this.minRam = minRam;
    return this;
  }

  /**
   * The minimal RAM for this software image in bytes. 
   * minimum: 0
   * @return minRam
  **/
  @ApiModelProperty(required = true, value = "The minimal RAM for this software image in bytes. ")
      @NotNull

  @Min(0)  public Integer getMinRam() {
    return minRam;
  }

  public void setMinRam(Integer minRam) {
    this.minRam = minRam;
  }

  public VnfPackageSoftwareImageInfo size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Size of this software image in bytes. 
   * minimum: 0
   * @return size
  **/
  @ApiModelProperty(required = true, value = "Size of this software image in bytes. ")
      @NotNull

  @Min(0)  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public VnfPackageSoftwareImageInfo userMetadata(KeyValuePairs userMetadata) {
    this.userMetadata = userMetadata;
    return this;
  }

  /**
   * Get userMetadata
   * @return userMetadata
  **/
  @ApiModelProperty(value = "")
  
    @Valid
    public KeyValuePairs getUserMetadata() {
    return userMetadata;
  }

  public void setUserMetadata(KeyValuePairs userMetadata) {
    this.userMetadata = userMetadata;
  }

  public VnfPackageSoftwareImageInfo imagePath(String imagePath) {
    this.imagePath = imagePath;
    return this;
  }

  /**
   * Path in the VNF package, which identifies the image artifact and also allows to access a copy of the image artifact. 
   * @return imagePath
  **/
  @ApiModelProperty(required = true, value = "Path in the VNF package, which identifies the image artifact and also allows to access a copy of the image artifact. ")
      @NotNull

    public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VnfPackageSoftwareImageInfo vnfPackageSoftwareImageInfo = (VnfPackageSoftwareImageInfo) o;
    return Objects.equals(this.id, vnfPackageSoftwareImageInfo.id) &&
        Objects.equals(this.name, vnfPackageSoftwareImageInfo.name) &&
        Objects.equals(this.provider, vnfPackageSoftwareImageInfo.provider) &&
        Objects.equals(this.version, vnfPackageSoftwareImageInfo.version) &&
        Objects.equals(this.checksum, vnfPackageSoftwareImageInfo.checksum) &&
        Objects.equals(this.isEncrypted, vnfPackageSoftwareImageInfo.isEncrypted) &&
        Objects.equals(this.containerFormat, vnfPackageSoftwareImageInfo.containerFormat) &&
        Objects.equals(this.diskFormat, vnfPackageSoftwareImageInfo.diskFormat) &&
        Objects.equals(this.createdAt, vnfPackageSoftwareImageInfo.createdAt) &&
        Objects.equals(this.minDisk, vnfPackageSoftwareImageInfo.minDisk) &&
        Objects.equals(this.minRam, vnfPackageSoftwareImageInfo.minRam) &&
        Objects.equals(this.size, vnfPackageSoftwareImageInfo.size) &&
        Objects.equals(this.userMetadata, vnfPackageSoftwareImageInfo.userMetadata) &&
        Objects.equals(this.imagePath, vnfPackageSoftwareImageInfo.imagePath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, provider, version, checksum, isEncrypted, containerFormat, diskFormat, createdAt, minDisk, minRam, size, userMetadata, imagePath);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackageSoftwareImageInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    isEncrypted: ").append(toIndentedString(isEncrypted)).append("\n");
    sb.append("    containerFormat: ").append(toIndentedString(containerFormat)).append("\n");
    sb.append("    diskFormat: ").append(toIndentedString(diskFormat)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    minDisk: ").append(toIndentedString(minDisk)).append("\n");
    sb.append("    minRam: ").append(toIndentedString(minRam)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    userMetadata: ").append(toIndentedString(userMetadata)).append("\n");
    sb.append("    imagePath: ").append(toIndentedString(imagePath)).append("\n");
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
