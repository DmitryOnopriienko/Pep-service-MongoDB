package com.restmongo.pep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameStatisticsDto {

  private String name;

  private int count;

}
