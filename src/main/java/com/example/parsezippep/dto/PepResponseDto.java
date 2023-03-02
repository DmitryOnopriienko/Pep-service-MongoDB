package com.example.parsezippep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PepResponseDto {

  private String id;

  @JsonProperty("first_name_en")
  @Field("first_name_en")
  private String firstName = "";

  @JsonProperty("patronymic_en")
  @Field("patronymic_en")
  private String patronymic = "";

  @JsonProperty("last_name_en")
  @Field("last_name_en")
  private String lastName = "";

  @JsonProperty("type_of_official_en")
  @Field("type_of_official_en")
  private String typeOfOfficial;

  @JsonProperty("full_name")
  @Field("full_name")
  private String fullName;

  private boolean died;

  @JsonProperty("related_companies")
  @Field("related_companies")
  private List<RelatedCompany> relatedCompanies;

}
