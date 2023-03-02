package com.example.parsezippep.controller;

import com.example.parsezippep.dto.NameStatisticsDto;
import com.example.parsezippep.dto.PepRequestDto;
import com.example.parsezippep.dto.PepResponseDto;
import com.example.parsezippep.service.PepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

  private final PepService pepService;

  @Autowired
  public Controller(PepService pepService) {
    this.pepService = pepService;
  }

  @PostMapping("/upload")
  public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
    pepService.saveFromZip(file);
    return ResponseEntity.ok("Entities added successfully");
  }

  @GetMapping("/all")
  public Page<PepResponseDto> findAll(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "5") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return pepService.findAll(pageable);
  }

  @PostMapping("/_search")
  public Page<PepResponseDto> findByFullName(@RequestBody PepRequestDto requestDto,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return pepService.findByFullName(requestDto, pageable);
  }

  @GetMapping("/first_name_statistics")
  public List<NameStatisticsDto> getFirstNameStatistics() {
    return pepService.getFirstNameStatistics();
  }
}
