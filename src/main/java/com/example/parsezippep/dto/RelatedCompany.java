package com.example.parsezippep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatedCompany {

  @Field("relationship_type_en")
  @JsonProperty("relationship_type_en")
  private String relationshipTypeEn;

  @Field("to_company_short_en")
  @JsonProperty("to_company_short_en")
  private String toCompanyShortEn;

  @Field("date_established")
  @JsonProperty("date_established")
  private String dateEstablished;

  @Field("to_company_edrpou")
  @JsonProperty("to_company_edrpou")
  private String toCompanyEdrpou;

  @Field("to_company_founded")
  @JsonProperty("to_company_founded")
  private String toCompanyFounded;

  @Field("date_finished")
  @JsonProperty("date_finished")
  private String dateFinished;

  @Field("to_company_is_state")
  @JsonProperty("to_company_is_state")
  private boolean toCompanyIsState;

  @Field("date_confirmed")
  @JsonProperty("date_confirmed")
  private String dateConfirmed;

  @Field("to_company_uk")
  @JsonProperty("to_company_uk")
  private String toCompanyUk;

  @Field("to_company_short_uk")
  @JsonProperty("to_company_short_uk")
  private String toCompanyShortUk;

  @Field("to_company_en")
  @JsonProperty("to_company_en")
  private String toCompanyEn;

  @Field("relationship_type_uk")
  @JsonProperty("relationship_type_uk")
  private String relationshipTypeUk;
}
