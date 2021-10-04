package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Location and address information of the compiled log file. The consumer can use this information to obtain the compiled log file.
 */
@Schema(description = "Location and address information of the compiled log file. The consumer can use this information to obtain the compiled log file.")
@Validated


public class LogReportFileLocationInfo   {
  /**
   * Protocol over which the compiled log file can be retrieved. Permitted values: - HTTPS: transmission over HTTP Secure (HTTPS). - SFTP: transmission over SSH file transfer protocol (SFTP). - SCP: transmission over secure copy protocol (SCP). - FTPS: transmission over file transfer protocol secure (FTPS), as specified in IETF RFC 2228 [i.11],      using explicit mode as specified in IETF RFC 4217 [i.12]. If FTPS is supported, \"private\" protection level shall be used.  HTTPS shall be supported, and other protocols may be supported.
   */
  public enum ProtocolEnum {
    HTTPS("HTTPS"),
    
    SFTP("SFTP"),
    
    SCP("SCP"),
    
    FTPS("FTPS");

    private String value;

    ProtocolEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ProtocolEnum fromValue(String text) {
      for (ProtocolEnum b : ProtocolEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("protocol")
  private ProtocolEnum protocol = null;

  @JsonProperty("fileEndpoint")
  private String fileEndpoint = null;

  public LogReportFileLocationInfo protocol(ProtocolEnum protocol) {
    this.protocol = protocol;
    return this;
  }

  /**
   * Protocol over which the compiled log file can be retrieved. Permitted values: - HTTPS: transmission over HTTP Secure (HTTPS). - SFTP: transmission over SSH file transfer protocol (SFTP). - SCP: transmission over secure copy protocol (SCP). - FTPS: transmission over file transfer protocol secure (FTPS), as specified in IETF RFC 2228 [i.11],      using explicit mode as specified in IETF RFC 4217 [i.12]. If FTPS is supported, \"private\" protection level shall be used.  HTTPS shall be supported, and other protocols may be supported.
   * @return protocol
   **/
  @Schema(required = true, description = "Protocol over which the compiled log file can be retrieved. Permitted values: - HTTPS: transmission over HTTP Secure (HTTPS). - SFTP: transmission over SSH file transfer protocol (SFTP). - SCP: transmission over secure copy protocol (SCP). - FTPS: transmission over file transfer protocol secure (FTPS), as specified in IETF RFC 2228 [i.11],      using explicit mode as specified in IETF RFC 4217 [i.12]. If FTPS is supported, \"private\" protection level shall be used.  HTTPS shall be supported, and other protocols may be supported.")
      @NotNull

    public ProtocolEnum getProtocol() {
    return protocol;
  }

  public void setProtocol(ProtocolEnum protocol) {
    this.protocol = protocol;
  }

  public LogReportFileLocationInfo fileEndpoint(String fileEndpoint) {
    this.fileEndpoint = fileEndpoint;
    return this;
  }

  /**
   * The host name (or IP address), optionally a port number (if the host with the compile log file uses a non-standard port number as per the supported transmission protocol), a valid file directory path, and the file name of the compiled log file, or a valid URL.
   * @return fileEndpoint
   **/
  @Schema(required = true, description = "The host name (or IP address), optionally a port number (if the host with the compile log file uses a non-standard port number as per the supported transmission protocol), a valid file directory path, and the file name of the compiled log file, or a valid URL.")
      @NotNull

    public String getFileEndpoint() {
    return fileEndpoint;
  }

  public void setFileEndpoint(String fileEndpoint) {
    this.fileEndpoint = fileEndpoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogReportFileLocationInfo logReportFileLocationInfo = (LogReportFileLocationInfo) o;
    return Objects.equals(this.protocol, logReportFileLocationInfo.protocol) &&
        Objects.equals(this.fileEndpoint, logReportFileLocationInfo.fileEndpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(protocol, fileEndpoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogReportFileLocationInfo {\n");
    
    sb.append("    protocol: ").append(toIndentedString(protocol)).append("\n");
    sb.append("    fileEndpoint: ").append(toIndentedString(fileEndpoint)).append("\n");
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
