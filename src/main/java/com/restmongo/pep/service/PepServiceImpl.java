package com.restmongo.pep.service;

import com.restmongo.pep.dto.NameStatisticsDto;
import com.restmongo.pep.dto.PepRequestDto;
import com.restmongo.pep.dto.PepResponseDto;
import com.restmongo.pep.repository.PepRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class PepServiceImpl implements PepService {

  private final PepRepository pepRepository;

  @Autowired
  public PepServiceImpl(PepRepository pepRepository) {
    this.pepRepository = pepRepository;
  }

  @Override
  public void saveFromZip(MultipartFile file) {
    try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream())) {
      ZipEntry zipEntry = zipInputStream.getNextEntry();

      while (zipEntry != null && zipEntry.getName().endsWith(".json")) {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(zipInputStream));
        StringBuilder builder = new StringBuilder();

        String data;
        while ((data = bufferedReader.readLine()) != null) {
          builder.append(data);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(builder.toString());
        List<Document> documents = new ArrayList<>();
        for (JsonNode node : jsonNode) {
          Document document = Document.parse(node.toString());
          documents.add(document);
        }

        pepRepository.insert(documents);

        zipEntry = zipInputStream.getNextEntry();
      }
      zipInputStream.closeEntry();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Page<PepResponseDto> findByFullName(PepRequestDto requestDto, Pageable pageable) {
    return pepRepository.findByFullName(requestDto, pageable);
  }

  @Override
  public Page<PepResponseDto> findAll(Pageable pageable) {
    return pepRepository.findAllBy(pageable);
  }

  @Override
  public List<NameStatisticsDto> getFirstNameStatistics() {
    return pepRepository.getFirstNameStatistics();
  }
}
