package com.ubiqube.etsi.mano.nfvem.v331.model.nfvmanologm;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Information about the security measures for retrieving/accessing the compiled log files.
 */
@Schema(description = "Information about the security measures for retrieving/accessing the compiled log files.")
@Validated


public class LoggingJobConfigSecurityConfLogTransferSecurity   {
  @JsonProperty("publicKey")
  private String publicKey = null;

  public LoggingJobConfigSecurityConfLogTransferSecurity publicKey(String publicKey) {
    this.publicKey = publicKey;
    return this;
  }

  /**
   * The public key of the API consumer used for the client authentication with the file server. Shall be provided if required by the type of transfer protocol. May be omitted if the key has been provided to the API producer by other means, or if it has already been provided in some previous CreateLoggingJobRequest issued by the same API consumer, whose public key has not changed.
   * @return publicKey
   **/
  @Schema(description = "The public key of the API consumer used for the client authentication with the file server. Shall be provided if required by the type of transfer protocol. May be omitted if the key has been provided to the API producer by other means, or if it has already been provided in some previous CreateLoggingJobRequest issued by the same API consumer, whose public key has not changed.")
  
    public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoggingJobConfigSecurityConfLogTransferSecurity loggingJobConfigSecurityConfLogTransferSecurity = (LoggingJobConfigSecurityConfLogTransferSecurity) o;
    return Objects.equals(this.publicKey, loggingJobConfigSecurityConfLogTransferSecurity.publicKey);
  }

  @Override
  public int hashCode() {
    return Objects.hash(publicKey);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LoggingJobConfigSecurityConfLogTransferSecurity {\n");
    
    sb.append("    publicKey: ").append(toIndentedString(publicKey)).append("\n");
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
