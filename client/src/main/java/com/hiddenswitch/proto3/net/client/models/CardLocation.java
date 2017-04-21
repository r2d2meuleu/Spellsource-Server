/**
 * Hidden Switch Minionate API
 * The Minionate API for matchmaking, user accounts, collections management and more
 *
 * OpenAPI spec version: 1.0.1
 * Contact: benjamin.s.berman@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.hiddenswitch.proto3.net.client.models;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * CardLocation
 */

public class CardLocation  implements Serializable {
  @SerializedName("zone")
  private Integer zone = null;

  @SerializedName("position")
  private Integer position = null;

  public CardLocation zone(Integer zone) {
    this.zone = zone;
    return this;
  }

   /**
   * Get zone
   * @return zone
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getZone() {
    return zone;
  }

  public void setZone(Integer zone) {
    this.zone = zone;
  }

  public CardLocation position(Integer position) {
    this.position = position;
    return this;
  }

   /**
   * Get position
   * @return position
  **/
  @ApiModelProperty(example = "null", value = "")
  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardLocation cardLocation = (CardLocation) o;
    return Objects.equals(this.zone, cardLocation.zone) &&
        Objects.equals(this.position, cardLocation.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(zone, position);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardLocation {\n");
    
    sb.append("    zone: ").append(toIndentedString(zone)).append("\n");
    sb.append("    position: ").append(toIndentedString(position)).append("\n");
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
