package com.ubiqube.etsi.mano.model.vnf.sol005;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

/**
  * This type represents an artifact contained in a VNF package which represents a software image.   
 **/
@ApiModel(description="This type represents an artifact contained in a VNF package which represents a software image.   ")
public class VnfPackagesVnfPkgInfoSoftwareImages  {
  
  @ApiModelProperty(required = true, value = "Identifier of the software image. ")
 /**
   * Identifier of the software image. 
  **/
  private String id = null;

  @ApiModelProperty(required = true, value = "Name of the software image. ")
 /**
   * Name of the software image. 
  **/
  private String name = null;

  @ApiModelProperty(required = true, value = "Provider of the software image. ")
 /**
   * Provider of the software image. 
  **/
  private String provider = null;

  @ApiModelProperty(required = true, value = "Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. ")
 /**
   * Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. 
  **/
  private String version = null;

  @ApiModelProperty(required = true, value = "")
  @Valid
  private VnfPackagesVnfPkgInfoChecksum checksum = null;


@XmlType(name="ContainerFormatEnum")
@XmlEnum(String.class)
public enum ContainerFormatEnum {

@XmlEnumValue("AKI") AKI(String.valueOf("AKI")), @XmlEnumValue("AMI") AMI(String.valueOf("AMI")), @XmlEnumValue("ARI") ARI(String.valueOf("ARI")), @XmlEnumValue("BARE") BARE(String.valueOf("BARE")), @XmlEnumValue("DOCKER") DOCKER(String.valueOf("DOCKER")), @XmlEnumValue("OVA") OVA(String.valueOf("OVA")), @XmlEnumValue("OVF") OVF(String.valueOf("OVF"));


    private String value;

    ContainerFormatEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static ContainerFormatEnum fromValue(String v) {
        for (ContainerFormatEnum b : ContainerFormatEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "Container format indicates whether the software image is in a file format that also contains meta-data about the actual software. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ram disk image format - BARE: the image does not have a container or meta-data envelope - DOCKER: docker container format - OVA: OVF package in a tar file - OVF: OVF container format ")
 /**
   * Container format indicates whether the software image is in a file format that also contains meta-data about the actual software. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ram disk image format - BARE: the image does not have a container or meta-data envelope - DOCKER: docker container format - OVA: OVF package in a tar file - OVF: OVF container format 
  **/
  private ContainerFormatEnum containerFormat = null;


@XmlType(name="DiskFormatEnum")
@XmlEnum(String.class)
public enum DiskFormatEnum {

@XmlEnumValue("AKI") AKI(String.valueOf("AKI")), @XmlEnumValue("AMI") AMI(String.valueOf("AMI")), @XmlEnumValue("ARI") ARI(String.valueOf("ARI")), @XmlEnumValue("ISO") ISO(String.valueOf("ISO")), @XmlEnumValue("QCOW2") QCOW2(String.valueOf("QCOW2")), @XmlEnumValue("RAW") RAW(String.valueOf("RAW")), @XmlEnumValue("VDI") VDI(String.valueOf("VDI")), @XmlEnumValue("VHD") VHD(String.valueOf("VHD")), @XmlEnumValue("VHDX") VHDX(String.valueOf("VHDX")), @XmlEnumValue("VMDK") VMDK(String.valueOf("VMDK"));


    private String value;

    DiskFormatEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static DiskFormatEnum fromValue(String v) {
        for (DiskFormatEnum b : DiskFormatEnum.values()) {
            if (String.valueOf(b.value).equals(v)) {
                return b;
            }
        }
        return null;
    }
}

  @ApiModelProperty(required = true, value = "Disk format of a software image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc, such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format ")
 /**
   * Disk format of a software image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc, such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format 
  **/
  private DiskFormatEnum diskFormat = null;

  @ApiModelProperty(required = true, value = "Date-time stamp.  Representation: String formatted according to IETF RFC 3339. ")
 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
  **/
  private Date createdAt = null;

  @ApiModelProperty(required = true, value = "The minimal disk for this software image in bytes. ")
 /**
   * The minimal disk for this software image in bytes. 
  **/
  private Integer minDisk = null;

  @ApiModelProperty(required = true, value = "The minimal RAM for this software image in bytes. ")
 /**
   * The minimal RAM for this software image in bytes. 
  **/
  private Integer minRam = null;

  @ApiModelProperty(required = true, value = "Size of this software image in bytes. ")
 /**
   * Size of this software image in bytes. 
  **/
  private Integer size = null;

  @ApiModelProperty(value = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  ")
 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
  **/
  private Object userMetadata = null;

  @ApiModelProperty(required = true, value = "Path in the VNF package, which identifies the image artifact and also allows to access a copy of the image artifact. ")
 /**
   * Path in the VNF package, which identifies the image artifact and also allows to access a copy of the image artifact. 
  **/
  private String imagePath = null;
 /**
   * Identifier of the software image. 
   * @return id
  **/
  @JsonProperty("id")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages id(String id) {
    this.id = id;
    return this;
  }

 /**
   * Name of the software image. 
   * @return name
  **/
  @JsonProperty("name")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Provider of the software image. 
   * @return provider
  **/
  @JsonProperty("provider")
  @NotNull
  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages provider(String provider) {
    this.provider = provider;
    return this;
  }

 /**
   * Software version of the VNF. This is changed when there is any change to the software included in the VNF package. This information is copied from the VNFD. It shall be present after the VNF package content has been on-boarded and absent otherwise. 
   * @return version
  **/
  @JsonProperty("version")
  @NotNull
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages version(String version) {
    this.version = version;
    return this;
  }

 /**
   * Get checksum
   * @return checksum
  **/
  @JsonProperty("checksum")
  @NotNull
  public VnfPackagesVnfPkgInfoChecksum getChecksum() {
    return checksum;
  }

  public void setChecksum(VnfPackagesVnfPkgInfoChecksum checksum) {
    this.checksum = checksum;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages checksum(VnfPackagesVnfPkgInfoChecksum checksum) {
    this.checksum = checksum;
    return this;
  }

 /**
   * Container format indicates whether the software image is in a file format that also contains meta-data about the actual software. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ram disk image format - BARE: the image does not have a container or meta-data envelope - DOCKER: docker container format - OVA: OVF package in a tar file - OVF: OVF container format 
   * @return containerFormat
  **/
  @JsonProperty("containerFormat")
  @NotNull
  public String getContainerFormat() {
    if (containerFormat == null) {
      return null;
    }
    return containerFormat.value();
  }

  public void setContainerFormat(ContainerFormatEnum containerFormat) {
    this.containerFormat = containerFormat;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages containerFormat(ContainerFormatEnum containerFormat) {
    this.containerFormat = containerFormat;
    return this;
  }

 /**
   * Disk format of a software image is the format of the underlying disk image. Permitted values: - AKI: a kernel image format - AMI: a machine image format - ARI: a ramdisk image format - ISO: an archive format for the data contents of an optical disc, such as CD-ROM - QCOW2: a common disk image format, which can expand dynamically and supports copy on write - RAW: an unstructured disk image format - VDI: a common disk image format - VHD: a common disk image format - VHDX: enhanced version of VHD format - VMDK: a common disk image format 
   * @return diskFormat
  **/
  @JsonProperty("diskFormat")
  @NotNull
  public String getDiskFormat() {
    if (diskFormat == null) {
      return null;
    }
    return diskFormat.value();
  }

  public void setDiskFormat(DiskFormatEnum diskFormat) {
    this.diskFormat = diskFormat;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages diskFormat(DiskFormatEnum diskFormat) {
    this.diskFormat = diskFormat;
    return this;
  }

 /**
   * Date-time stamp.  Representation: String formatted according to IETF RFC 3339. 
   * @return createdAt
  **/
  @JsonProperty("createdAt")
  @NotNull
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages createdAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

 /**
   * The minimal disk for this software image in bytes. 
   * minimum: 0
   * @return minDisk
  **/
  @JsonProperty("minDisk")
  @NotNull
 @Min(0)  public Integer getMinDisk() {
    return minDisk;
  }

  public void setMinDisk(Integer minDisk) {
    this.minDisk = minDisk;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages minDisk(Integer minDisk) {
    this.minDisk = minDisk;
    return this;
  }

 /**
   * The minimal RAM for this software image in bytes. 
   * minimum: 0
   * @return minRam
  **/
  @JsonProperty("minRam")
  @NotNull
 @Min(0)  public Integer getMinRam() {
    return minRam;
  }

  public void setMinRam(Integer minRam) {
    this.minRam = minRam;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages minRam(Integer minRam) {
    this.minRam = minRam;
    return this;
  }

 /**
   * Size of this software image in bytes. 
   * minimum: 0
   * @return size
  **/
  @JsonProperty("size")
  @NotNull
 @Min(0)  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages size(Integer size) {
    this.size = size;
    return this;
  }

 /**
   * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of key- value pairs is represented as an object. It shall comply with the provisions  defined in clause 4 of IETF RFC 7159.  
   * @return userMetadata
  **/
  @JsonProperty("userMetadata")
  public Object getUserMetadata() {
    return userMetadata;
  }

  public void setUserMetadata(Object userMetadata) {
    this.userMetadata = userMetadata;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages userMetadata(Object userMetadata) {
    this.userMetadata = userMetadata;
    return this;
  }

 /**
   * Path in the VNF package, which identifies the image artifact and also allows to access a copy of the image artifact. 
   * @return imagePath
  **/
  @JsonProperty("imagePath")
  @NotNull
  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public VnfPackagesVnfPkgInfoSoftwareImages imagePath(String imagePath) {
    this.imagePath = imagePath;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VnfPackagesVnfPkgInfoSoftwareImages {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    checksum: ").append(toIndentedString(checksum)).append("\n");
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
  private static String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

