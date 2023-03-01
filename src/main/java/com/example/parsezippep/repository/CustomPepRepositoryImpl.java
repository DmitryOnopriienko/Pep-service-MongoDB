package com.example.parsezippep.repository;

import com.example.parsezippep.dto.PepRequestDto;
import com.example.parsezippep.dto.PepResponseDto;
import com.example.parsezippep.entity.Pep;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomPepRepositoryImpl implements CustomPepRepository {

  private final MongoTemplate mongoTemplate;

  @Autowired
  public CustomPepRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public void insert(List<Document> documents) {
    mongoTemplate.insert(documents, "person");
  }

  @Override
  public Page<PepResponseDto> findByFullName(PepRequestDto requestDto, Pageable pageable) {
    Query query = new Query();
    query.addCriteria(Criteria.where("first_name_en").regex(requestDto.getFirstName(), "i"));
    query.addCriteria(Criteria.where("last_name_en").regex(requestDto.getLastName(), "i"));
    query.addCriteria(Criteria.where("patronymic_en").regex(requestDto.getPatronymic(), "i"));
    query.addCriteria(Criteria.where("is_pep").is(true));
    query.with(pageable);
    List<Pep> pepEntityList = mongoTemplate.find(query, Pep.class, "person");

    List<PepResponseDto> responseDtoList = pepEntityList.stream()
            .map(CustomPepRepositoryImpl::mapPepEntityToResponseDto)
            .toList();

    long count = mongoTemplate.count(query, Pep.class);
    return new PageImpl<>(responseDtoList, pageable, count);
  }

  private static PepResponseDto mapPepEntityToResponseDto(Pep pepEntity) {
    PepResponseDto responseDto = new PepResponseDto();

    responseDto.setId(pepEntity.getId());
    responseDto.setFirstName(pepEntity.getFirstNameEn());
    responseDto.setLastName(pepEntity.getLastNameEn());
    responseDto.setPatronymic(pepEntity.getPatronymicEn());
    responseDto.setFullName(pepEntity.getFullName());
    responseDto.setDied(pepEntity.isDied());
    responseDto.setTypeOfOfficial(pepEntity.getTypeOfOfficialEn());
    responseDto.setRelatedCompanies(pepEntity.getRelatedCompanies());

    return responseDto;
  }
}
