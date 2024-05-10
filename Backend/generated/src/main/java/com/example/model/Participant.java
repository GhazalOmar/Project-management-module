package com.example.model;

import java.net.URI;
import java.util.Objects;
import com.example.model.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Participant
 */
@lombok.AllArgsConstructor
@lombok.Builder

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-09T09:24:21.029835377+03:00[Asia/Amman]", comments = "Generator version: 7.5.0")
public class Participant {

  private String code;

  private String bic;

  private String name;

  private String shortName;

  private Type type;

  private String logo;

  private String settlementBank;

  public Participant() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Participant(String code, String bic, String name, String shortName, Type type, String logo) {
    this.code = code;
    this.bic = bic;
    this.name = name;
    this.shortName = shortName;
    this.type = type;
    this.logo = logo;
  }

  public Participant code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  */
  @NotNull @Pattern(regexp = "^\\d{6}$") 
  @Schema(name = "code", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Participant bic(String bic) {
    this.bic = bic;
    return this;
  }

  /**
   * Get bic
   * @return bic
  */
  @NotNull @Pattern(regexp = "^[A-Z0-9]{4}[A-Z]{2}[A-Z0-9]{2}([A-Z0-9]{3})?$") 
  @Schema(name = "bic", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("bic")
  public String getBic() {
    return bic;
  }

  public void setBic(String bic) {
    this.bic = bic;
  }

  public Participant name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull @Pattern(regexp = "[A-Za-z0-9]+") 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Participant shortName(String shortName) {
    this.shortName = shortName;
    return this;
  }

  /**
   * Get shortName
   * @return shortName
  */
  @NotNull @Pattern(regexp = "^[A-Z]+$") 
  @Schema(name = "shortName", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("shortName")
  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public Participant type(Type type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Participant logo(String logo) {
    this.logo = logo;
    return this;
  }

  /**
   * Get logo
   * @return logo
  */
  @NotNull 
  @Schema(name = "logo", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("logo")
  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public Participant settlementBank(String settlementBank) {
    this.settlementBank = settlementBank;
    return this;
  }

  /**
   * Get settlementBank
   * @return settlementBank
  */
  
  @Schema(name = "settlementBank", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("settlementBank")
  public String getSettlementBank() {
    return settlementBank;
  }

  public void setSettlementBank(String settlementBank) {
    this.settlementBank = settlementBank;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Participant participant = (Participant) o;
    return Objects.equals(this.code, participant.code) &&
        Objects.equals(this.bic, participant.bic) &&
        Objects.equals(this.name, participant.name) &&
        Objects.equals(this.shortName, participant.shortName) &&
        Objects.equals(this.type, participant.type) &&
        Objects.equals(this.logo, participant.logo) &&
        Objects.equals(this.settlementBank, participant.settlementBank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, bic, name, shortName, type, logo, settlementBank);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Participant {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    bic: ").append(toIndentedString(bic)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    shortName: ").append(toIndentedString(shortName)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
    sb.append("    settlementBank: ").append(toIndentedString(settlementBank)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

