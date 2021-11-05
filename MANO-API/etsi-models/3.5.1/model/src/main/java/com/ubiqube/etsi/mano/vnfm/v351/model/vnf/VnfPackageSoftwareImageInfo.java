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
package com.ubiqube.etsi.mano.vnfm.v351.model.vnf;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnf.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an artifact contained in or external to a VNF package which represents a software image.  It shall comply with the provisions defined in table 10.5.3.2-1. NOTE 1: The list of permitted values was taken from \&quot;Container formats\&quot; in OpenStack® documentation: \&quot;Disk and container formats for images\&quot;         (Available at https://docs.openstack.org/glance/pike/user/formats.html). NOTE 2: The list of permitted values was adapted from \&quot;Disk formats\&quot; in OpenStack® documentation: \&quot;Disk and container formats for images\&quot;         (Available at https://docs.openstack.org/glance/pike/user/formats.html). 
 */
@Schema(description = "This type represents an artifact contained in or external to a VNF package which represents a software image.  It shall comply with the provisions defined in table 10.5.3.2-1. NOTE 1: The list of permitted values was taken from \"Container formats\" in OpenStack® documentation: \"Disk and container formats for images\"         (Available at https://docs.openstack.org/glance/pike/user/formats.html). NOTE 2: The list of permitted values was adapted from \"Disk formats\" in OpenStack® documentation: \"Disk and container formats for images\"         (Available at https://docs.openstack.org/glance/pike/user/formats.html). ")
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
  private String checksum = null;

  @JsonProperty("isEncrypted")
  private Boolean isEncrypted = null;

  /**
   * Container format indicates whether the software image is in a file format that also contains metadata about the actual software. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - BARE: the image does not have a container or metadata envelope - DOCKER: docker container format - OVA: OVF package in a tarfile - OVF: OVF container format See note 1. 
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
   * Disk format of a software image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc,   such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically   and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format See note 2. 
   */
  public enum DiskFormatEnum {
    AKI("AKI"),
    
    AMI("AMI"),
    
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
  private OffsetDateTime createdAt = null;

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

  @JsonProperty("imageUri")
  private String imageUri = null;

  public VnfPackageSoftwareImageInfo id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
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
   * Get name
   * @return name
   **/
  @Schema(required = true, description = "")
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
   * Get provider
   * @return provider
   **/
  @Schema(required = true, description = "")
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
  @Schema(required = true, description = "")
      @NotNull

    public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public VnfPackageSoftwareImageInfo checksum(String checksum) {
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

  public VnfPackageSoftwareImageInfo isEncrypted(Boolean isEncrypted) {
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

  public VnfPackageSoftwareImageInfo containerFormat(ContainerFormatEnum containerFormat) {
    this.containerFormat = containerFormat;
    return this;
  }

  /**
   * Container format indicates whether the software image is in a file format that also contains metadata about the actual software. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - BARE: the image does not have a container or metadata envelope - DOCKER: docker container format - OVA: OVF package in a tarfile - OVF: OVF container format See note 1. 
   * @return containerFormat
   **/
  @Schema(required = true, description = "Container format indicates whether the software image is in a file format that also contains metadata about the actual software. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - BARE: the image does not have a container or metadata envelope - DOCKER: docker container format - OVA: OVF package in a tarfile - OVF: OVF container format See note 1. ")
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
   * Disk format of a software image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc,   such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically   and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format See note 2. 
   * @return diskFormat
   **/
  @Schema(required = true, description = "Disk format of a software image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc,   such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically   and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format See note 2. ")
      @NotNull

    public DiskFormatEnum getDiskFormat() {
    return diskFormat;
  }

  public void setDiskFormat(DiskFormatEnum diskFormat) {
    this.diskFormat = diskFormat;
  }

  public VnfPackageSoftwareImageInfo createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public VnfPackageSoftwareImageInfo minDisk(Integer minDisk) {
    this.minDisk = minDisk;
    return this;
  }

  /**
   * The minimal disk for this software image in bytes. 
   * @return minDisk
   **/
  @Schema(required = true, description = "The minimal disk for this software image in bytes. ")
      @NotNull

    public Integer getMinDisk() {
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
   * @return minRam
   **/
  @Schema(required = true, description = "The minimal RAM for this software image in bytes. ")
      @NotNull

    public Integer getMinRam() {
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
   * @return size
   **/
  @Schema(required = true, description = "Size of this software image in bytes. ")
      @NotNull

    public Integer getSize() {
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
  @Schema(description = "")
  
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
   * Path which identifies the image artifact and also allows to access a copy of the image artifact. For a software image contained as a file in the VNF package, this attribute shall be present, and the value of this attribute shall start with the name of the first segment in the path in the package, i.e., it shall not be prefixed by path separator characters such as \".\" and \"/\". EXAMPLE: foo/bar/m%40ster.vhd For an external software image represented as a URI in the VNF descriptor, this attribute shall be present if the  image artifact has been downloaded by the NFVO and shall be absent otherwise. If present, it shall contain the artifactPath under which the image artifact can be obtained using the \"Individual artifact in a VNF package\" resource defined in clause 9.4.7. It is the responsibility of the NFVO to synthesize this path in a manner that avoids any collision of the synthesized artifact path with the paths and names of image artifacts included in the package. 
   * @return imagePath
   **/
  @Schema(description = "Path which identifies the image artifact and also allows to access a copy of the image artifact. For a software image contained as a file in the VNF package, this attribute shall be present, and the value of this attribute shall start with the name of the first segment in the path in the package, i.e., it shall not be prefixed by path separator characters such as \".\" and \"/\". EXAMPLE: foo/bar/m%40ster.vhd For an external software image represented as a URI in the VNF descriptor, this attribute shall be present if the  image artifact has been downloaded by the NFVO and shall be absent otherwise. If present, it shall contain the artifactPath under which the image artifact can be obtained using the \"Individual artifact in a VNF package\" resource defined in clause 9.4.7. It is the responsibility of the NFVO to synthesize this path in a manner that avoids any collision of the synthesized artifact path with the paths and names of image artifacts included in the package. ")
  
    public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public VnfPackageSoftwareImageInfo imageUri(String imageUri) {
    this.imageUri = imageUri;
    return this;
  }

  /**
   * Get imageUri
   * @return imageUri
   **/
  @Schema(description = "")
  
    public String getImageUri() {
    return imageUri;
  }

  public void setImageUri(String imageUri) {
    this.imageUri = imageUri;
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
        Objects.equals(this.imagePath, vnfPackageSoftwareImageInfo.imagePath) &&
        Objects.equals(this.imageUri, vnfPackageSoftwareImageInfo.imageUri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, provider, version, checksum, isEncrypted, containerFormat, diskFormat, createdAt, minDisk, minRam, size, userMetadata, imagePath, imageUri);
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
    sb.append("    imageUri: ").append(toIndentedString(imageUri)).append("\n");
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
