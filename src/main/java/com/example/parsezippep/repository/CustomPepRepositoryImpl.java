package com.example.parsezippep.repository;

import com.example.parsezippep.dto.NameStatisticsDto;
import com.example.parsezippep.dto.PepRequestDto;
import com.example.parsezippep.dto.PepResponseDto;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
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
//    List<Pep> pepEntityList = mongoTemplate.find(query, Pep.class, "person");
//
//    List<PepResponseDto> responseDtoList = pepEntityList.stream()
//            .map(CustomPepRepositoryImpl::mapPepEntityToResponseDto)
//            .toList();

    List<PepResponseDto> response = mongoTemplate.find(query, PepResponseDto.class, "person");
    long c = mongoTemplate.count(query, PepResponseDto.class);
    return new PageImpl<>(response, pageable, c);

//    long count = mongoTemplate.count(query, Pep.class);
//    return new PageImpl<>(responseDtoList, pageable, count);
  }

//  private static PepResponseDto mapPepEntityToResponseDto(Pep pepEntity) {
//    PepResponseDto responseDto = new PepResponseDto();
//
//    responseDto.setId(pepEntity.getId());
//    responseDto.setFirstName(pepEntity.getFirstNameEn());
//    responseDto.setLastName(pepEntity.getLastNameEn());
//    responseDto.setPatronymic(pepEntity.getPatronymicEn());
//    responseDto.setFullName(pepEntity.getFullName());
//    responseDto.setDied(pepEntity.isDied());
//    responseDto.setTypeOfOfficial(pepEntity.getTypeOfOfficialEn());
//    responseDto.setRelatedCompanies(pepEntity.getRelatedCompanies());
//
//    return responseDto;
//  }

  @Override
  public List<NameStatisticsDto> getFirstNameStatistics() {
    MatchOperation matchOperation = Aggregation.match(new Criteria("is_pep").is(true));
    GroupOperation groupOperation = Aggregation
            .group("first_name_en")
            .count().as("count");
    SortOperation sortOperation = Aggregation
            .sort(Sort.by(Sort.Direction.DESC, "count"));
    LimitOperation limitOperation = Aggregation.limit(10);
    ProjectionOperation projectionOperation = Aggregation.project()
            .andExpression("_id").as("name")
            .andInclude("count");

    Aggregation aggregation = Aggregation
            .newAggregation(matchOperation,
                    groupOperation,
                    sortOperation,
                    limitOperation,
                    projectionOperation);

    return mongoTemplate.aggregate(aggregation, "person", NameStatisticsDto.class)
            .getMappedResults();
  }
}
