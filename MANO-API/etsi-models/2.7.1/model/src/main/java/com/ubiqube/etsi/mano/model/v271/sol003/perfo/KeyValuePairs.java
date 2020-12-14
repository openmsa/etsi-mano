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
package com.ubiqube.etsi.mano.model.v271.sol003.perfo;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of keyvalue pairs is represented as an object. It shall comply with the provisions defined in clause 4 of IETF RFC 8259. In the following example, a list of key-value pairs with four keys (\&quot;aString\&quot;, \&quot;aNumber\&quot;, \&quot;anArray\&quot; and \&quot;anObject\&quot;) is provided to illustrate that the values associated with different keys can be of different type. 
 */
@ApiModel(description = "This type represents a list of key-value pairs. The order of the pairs in the list is not significant. In JSON, a set of keyvalue pairs is represented as an object. It shall comply with the provisions defined in clause 4 of IETF RFC 8259. In the following example, a list of key-value pairs with four keys (\"aString\", \"aNumber\", \"anArray\" and \"anObject\") is provided to illustrate that the values associated with different keys can be of different type. ")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-11-09T10:40:34.645+01:00")

public class KeyValuePairs   {

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class KeyValuePairs {\n");
    
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

