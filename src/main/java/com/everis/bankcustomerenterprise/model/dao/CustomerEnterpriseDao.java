package com.everis.bankcustomerenterprise.model.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.everis.bankcustomerenterprise.model.document.CustomerEnterpriseDoc;

public interface CustomerEnterpriseDao extends ReactiveMongoRepository<CustomerEnterpriseDoc, String> {

}
