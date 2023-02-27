package com.example.parsezippep.service;

import org.springframework.web.multipart.MultipartFile;

public interface PepService {
  void saveFromZip(MultipartFile file);
}
