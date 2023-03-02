package com.example.parsezippep.service;

import com.example.parsezippep.dto.NameStatisticsDto;
import com.example.parsezippep.dto.PepRequestDto;
import com.example.parsezippep.dto.PepResponseDto;
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
