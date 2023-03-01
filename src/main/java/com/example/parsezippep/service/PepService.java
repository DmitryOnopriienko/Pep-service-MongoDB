package com.example.parsezippep.service;

import com.example.parsezippep.dto.PepRequestDto;
import com.example.parsezippep.dto.PepResponseDto;
import com.example.parsezippep.entity.Pep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PepService {
  void saveFromZip(MultipartFile file);

  Page<PepResponseDto> findByFullName(PepRequestDto requestDto, Pageable pageable);

  Page<Pep> findAll(Pageable pageable);
}
