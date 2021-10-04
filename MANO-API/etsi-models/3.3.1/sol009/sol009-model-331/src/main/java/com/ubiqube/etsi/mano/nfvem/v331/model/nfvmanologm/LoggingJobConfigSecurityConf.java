package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LoggingJobConfigSecurityConfLogFileEncryption;
import com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm.LoggingJobConfigSecurityConfLogTransferSecurity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Configuration about the security aspects of the logging job.
 */
@Schema(description = "Configuration about the security aspects of the logging job.")
@Validated


public class LoggingJobConfigSecurityConf   {
  @JsonProperty("logFileEncryption")
  private LoggingJobConfigSecurityConfLogFileEncryption logFileEncryption = null;

  @JsonProperty("logTransferSecurity")
  private LoggingJobConfigSecurityConfLogTransferSecurity logTransferSecurity = null;

  public LoggingJobConfigSecurityConf logFileEncryption(LoggingJobConfigSecurityConfLogFileEncryption logFileEncryption) {
    this.logFileEncryption = logFileEncryption;
    return this;
  }

  /**
   * Get logFileEncryption
   * @return logFileEncryption
   **/
  @Schema(description = "")
  
    @Valid
    public LoggingJobConfigSecurityConfLogFileEncryption getLogFileEncryption() {
    return logFileEncryption;
  }

  public void setLogFileEncryption(LoggingJobConfigSecurityConfLogFileEncryption logFileEncryption) {
    this.logFileEncryption = logFileEncryption;
  }

  public LoggingJobConfigSecurityConf logTransferSecurity(LoggingJobConfigSecurityConfLogTransferSecurity logTransferSecurity) {
    this.logTransferSecurity = logTransferSecurity;
    return this;
  }

  /**
   * Get logTransferSecurity
   * @return logTransferSecurity
   **/
  @Schema(description = "")
  
    @Valid
    public LoggingJobConfigSecurityConfLogTransferSecurity getLogTransferSecurity() {
    return logTransferSecurity;
  }

  public void setLogTransferSecurity(LoggingJobConfigSecurityConfLogTransferSecurity logTransferSecurity) {
    this.logTransferSecurity = logTransferSecurity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoggingJobConfigSecurityConf loggingJobConfigSecurityConf = (LoggingJobConfigSecurityConf) o;
    return Objects.equals(this.logFileEncryption, loggingJobConfigSecurityConf.logFileEncryption) &&
        Objects.equals(this.logTransferSecurity, loggingJobConfigSecurityConf.logTransferSecurity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logFileEncryption, logTransferSecurity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoggingJobConfigSecurityConf {\n");
    
    sb.append("    logFileEncryption: ").append(toIndentedString(logFileEncryption)).append("\n");
    sb.append("    logTransferSecurity: ").append(toIndentedString(logTransferSecurity)).append("\n");
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
