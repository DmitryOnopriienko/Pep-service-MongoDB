package com.example.parsezippep.repository;

import com.example.parsezippep.dto.PepRequestDto;
import com.example.parsezippep.dto.PepResponseDto;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomPepRepository {

  void insert(List<Document> documents);

  Page<PepResponseDto> findByFullName(PepRequestDto requestDto, Pageable pageable);
}
