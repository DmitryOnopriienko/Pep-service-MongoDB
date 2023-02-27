package com.example.parsezippep.controller;

import com.example.parsezippep.service.PepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
