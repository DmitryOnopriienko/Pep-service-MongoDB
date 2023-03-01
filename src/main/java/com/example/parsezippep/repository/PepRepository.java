package com.example.parsezippep.repository;

import com.example.parsezippep.entity.Pep;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PepRepository extends MongoRepository<Pep, String>, CustomPepRepository {
}