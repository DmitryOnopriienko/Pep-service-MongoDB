package com.example.parsezippep.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

  private final MongoTemplate mongoTemplate;

  @Autowired
  public PepServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
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

        mongoTemplate.insert(documents, "person");

        zipEntry = zipInputStream.getNextEntry();
      }
      zipInputStream.closeEntry();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
