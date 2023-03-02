package com.example.parsezippep.repository;

import com.example.parsezippep.dto.PepResponseDto;
import com.example.parsezippep.entity.Pep;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PepRepository extends MongoRepository<Pep, String>, CustomPepRepository {
  Page<PepResponseDto> findAllBy(Pageable pageable);
}
