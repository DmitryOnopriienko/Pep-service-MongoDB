package com.example.parsezippep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PepResponseDto {

  private String id;

  @JsonProperty("first_name_en")
  private String firstName = "";

  @JsonProperty("patronymic_en")
  private String patronymic = "";

  @JsonProperty("last_name_en")
  private String lastName = "";

  @JsonProperty("type_of_official_en")
  private String typeOfOfficial;

  @JsonProperty("full_name")
  private String fullName;

  private boolean died;

  @JsonProperty("related_companies")
  private List<RelatedCompany> relatedCompanies;

}
