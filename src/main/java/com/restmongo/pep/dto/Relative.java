package com.restmongo.pep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class Relative {

  @JsonProperty("relationship_type_en")
  private String relationshipTypeEn;

  @JsonProperty("date_established")
  private String dateEstablished;

  @JsonProperty("person_en")
  private String personEn;

  @JsonProperty("date_confirmed")
  private String dateConfirmed;

  @JsonProperty("is_pep")
  private boolean isPep;

  @JsonProperty("date_finished")
  private String dateFinished;

  @JsonProperty("person_uk")
  private String personUk;

  @JsonProperty("relationship_type")
  private String relationshipType;
}
