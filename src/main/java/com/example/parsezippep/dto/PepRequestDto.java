package com.example.parsezippep.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PepRequestDto {

  @JsonProperty("first_name_en")
  private String firstName = "";

  @JsonProperty("patronymic_en")
  private String patronymic = "";

  @JsonProperty("last_name_en")
  private String lastName = "";

}
