package com.ubiqube.etsi.mano.vnfm.v351.model.vnfsnapshotpkgm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.vnfm.v351.model.vnfsnapshotpkgm.KeyValuePairs;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents an artifact contained in a VNF snapshot package which represents a snapshot image.  It shall comply with the provisions defined in table 12.5.3.2-1. NOTE 1: The list of permitted values was taken from \&quot;Container formats\&quot; in OpenStack® documentation: \&quot;Disk and container formats for images\&quot;         (Available at https://docs.openstack.org/glance/pike/user/formats.html). NOTE 2: The list of permitted values was adapted from \&quot;Disk formats\&quot; in OpenStack® documentation: \&quot;Disk and container formats for images\&quot;         (Available at https://docs.openstack.org/glance/pike/user/formats.html). 
 */
@Schema(description = "This type represents an artifact contained in a VNF snapshot package which represents a snapshot image.  It shall comply with the provisions defined in table 12.5.3.2-1. NOTE 1: The list of permitted values was taken from \"Container formats\" in OpenStack® documentation: \"Disk and container formats for images\"         (Available at https://docs.openstack.org/glance/pike/user/formats.html). NOTE 2: The list of permitted values was adapted from \"Disk formats\" in OpenStack® documentation: \"Disk and container formats for images\"         (Available at https://docs.openstack.org/glance/pike/user/formats.html). ")
@Validated


public class VnfcSnapshotImageInfo   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("checksum")
  private String checksum = null;

  @JsonProperty("isEncrypted")
  private Boolean isEncrypted = null;

  @JsonProperty("vnfcInstanceId")
  private String vnfcInstanceId = null;

  /**
   * Container format indicates whether the snapshot image is in a file format that  also contains metadata about the actual snapshot. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - BARE: the image does not have a container or metadata envelope - DOCKER: docker container format - OVA: OVF package in a tarfile - OVF: OVF container format See note 1. 
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
   * Disk format of a snapshot image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc, such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format See note 2. 
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

  public VnfcSnapshotImageInfo id(String id) {
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

  public VnfcSnapshotImageInfo name(String name) {
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

  public VnfcSnapshotImageInfo checksum(String checksum) {
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

  public VnfcSnapshotImageInfo isEncrypted(Boolean isEncrypted) {
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

  public VnfcSnapshotImageInfo vnfcInstanceId(String vnfcInstanceId) {
    this.vnfcInstanceId = vnfcInstanceId;
    return this;
  }

  /**
   * Get vnfcInstanceId
   * @return vnfcInstanceId
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getVnfcInstanceId() {
    return vnfcInstanceId;
  }

  public void setVnfcInstanceId(String vnfcInstanceId) {
    this.vnfcInstanceId = vnfcInstanceId;
  }

  public VnfcSnapshotImageInfo containerFormat(ContainerFormatEnum containerFormat) {
    this.containerFormat = containerFormat;
    return this;
  }

  /**
   * Container format indicates whether the snapshot image is in a file format that  also contains metadata about the actual snapshot. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - BARE: the image does not have a container or metadata envelope - DOCKER: docker container format - OVA: OVF package in a tarfile - OVF: OVF container format See note 1. 
   * @return containerFormat
   **/
  @Schema(required = true, description = "Container format indicates whether the snapshot image is in a file format that  also contains metadata about the actual snapshot. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - BARE: the image does not have a container or metadata envelope - DOCKER: docker container format - OVA: OVF package in a tarfile - OVF: OVF container format See note 1. ")
      @NotNull

    public ContainerFormatEnum getContainerFormat() {
    return containerFormat;
  }

  public void setContainerFormat(ContainerFormatEnum containerFormat) {
    this.containerFormat = containerFormat;
  }

  public VnfcSnapshotImageInfo diskFormat(DiskFormatEnum diskFormat) {
    this.diskFormat = diskFormat;
    return this;
  }

  /**
   * Disk format of a snapshot image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc, such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format See note 2. 
   * @return diskFormat
   **/
  @Schema(required = true, description = "Disk format of a snapshot image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc, such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format See note 2. ")
      @NotNull

    public DiskFormatEnum getDiskFormat() {
    return diskFormat;
  }

  public void setDiskFormat(DiskFormatEnum diskFormat) {
    this.diskFormat = diskFormat;
  }

  public VnfcSnapshotImageInfo createdAt(OffsetDateTime createdAt) {
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

  public VnfcSnapshotImageInfo minDisk(Integer minDisk) {
    this.minDisk = minDisk;
    return this;
  }

  /**
   * Get minDisk
   * @return minDisk
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getMinDisk() {
    return minDisk;
  }

  public void setMinDisk(Integer minDisk) {
    this.minDisk = minDisk;
  }

  public VnfcSnapshotImageInfo minRam(Integer minRam) {
    this.minRam = minRam;
    return this;
  }

  /**
   * Get minRam
   * @return minRam
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getMinRam() {
    return minRam;
  }

  public void setMinRam(Integer minRam) {
    this.minRam = minRam;
  }

  public VnfcSnapshotImageInfo size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
   **/
  @Schema(required = true, description = "")
      @NotNull

    public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public VnfcSnapshotImageInfo userMetadata(KeyValuePairs userMetadata) {
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

  public VnfcSnapshotImageInfo imagePath(String imagePath) {
    this.imagePath = imagePath;
    return this;
  }

  /**
   * Get imagePath
   * @return imagePath
   **/
  @Schema(description = "")
  
    public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public VnfcSnapshotImageInfo imageUri(String imageUri) {
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
    VnfcSnapshotImageInfo vnfcSnapshotImageInfo = (VnfcSnapshotImageInfo) o;
    return Objects.equals(this.id, vnfcSnapshotImageInfo.id) &&
        Objects.equals(this.name, vnfcSnapshotImageInfo.name) &&
        Objects.equals(this.checksum, vnfcSnapshotImageInfo.checksum) &&
        Objects.equals(this.isEncrypted, vnfcSnapshotImageInfo.isEncrypted) &&
        Objects.equals(this.vnfcInstanceId, vnfcSnapshotImageInfo.vnfcInstanceId) &&
        Objects.equals(this.containerFormat, vnfcSnapshotImageInfo.containerFormat) &&
        Objects.equals(this.diskFormat, vnfcSnapshotImageInfo.diskFormat) &&
        Objects.equals(this.createdAt, vnfcSnapshotImageInfo.createdAt) &&
        Objects.equals(this.minDisk, vnfcSnapshotImageInfo.minDisk) &&
        Objects.equals(this.minRam, vnfcSnapshotImageInfo.minRam) &&
        Objects.equals(this.size, vnfcSnapshotImageInfo.size) &&
        Objects.equals(this.userMetadata, vnfcSnapshotImageInfo.userMetadata) &&
        Objects.equals(this.imagePath, vnfcSnapshotImageInfo.imagePath) &&
        Objects.equals(this.imageUri, vnfcSnapshotImageInfo.imageUri);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, checksum, isEncrypted, vnfcInstanceId, containerFormat, diskFormat, createdAt, minDisk, minRam, size, userMetadata, imagePath, imageUri);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfcSnapshotImageInfo {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
    sb.append("    isEncrypted: ").append(toIndentedString(isEncrypted)).append("\n");
    sb.append("    vnfcInstanceId: ").append(toIndentedString(vnfcInstanceId)).append("\n");
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
