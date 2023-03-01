package com.example.parsezippep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class Declaration {

  @JsonProperty("position_en")
  private String positionEn;

  private String url;

  private double income;

  @JsonProperty("region_uk")
  private String regionUk;

  @JsonProperty("office_en")
  private String officeEn;

  @JsonProperty("position_uk")
  private String positionUk;

  private String year;

  @JsonProperty("office_uk")
  private String officeUk;

  @JsonProperty("region_en")
  private String regionEn;

  @JsonProperty("family_income")
  private double familyIncome;
}
