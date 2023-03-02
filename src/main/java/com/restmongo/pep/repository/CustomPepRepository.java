package com.restmongo.pep.repository;

import com.restmongo.pep.dto.NameStatisticsDto;
import com.restmongo.pep.dto.PepRequestDto;
import com.restmongo.pep.dto.PepResponseDto;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomPepRepository {

  void insert(List<Document> documents);

  Page<PepResponseDto> findByFullName(PepRequestDto requestDto, Pageable pageable);

  List<NameStatisticsDto> getFirstNameStatistics();
}
