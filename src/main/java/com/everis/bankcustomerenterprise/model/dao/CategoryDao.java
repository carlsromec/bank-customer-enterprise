package com.everis.bankcustomerenterprise.model.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.bankcustomerenterprise.model.document.CategoryDoc;

public interface CategoryDao extends ReactiveMongoRepository<CategoryDoc, String> {

}
