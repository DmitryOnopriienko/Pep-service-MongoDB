package com.restmongo.pep.service;

import com.restmongo.pep.dto.NameStatisticsDto;
import com.restmongo.pep.dto.PepRequestDto;
import com.restmongo.pep.dto.PepResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PepService {
  void saveFromZip(MultipartFile file);

  Page<PepResponseDto> findByFullName(PepRequestDto requestDto, Pageable pageable);

  Page<PepResponseDto> findAll(Pageable pageable);

  List<NameStatisticsDto> getFirstNameStatistics();
}
