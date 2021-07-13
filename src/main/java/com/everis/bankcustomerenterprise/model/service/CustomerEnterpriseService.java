package com.everis.bankcustomerenterprise.model.service;

import com.everis.bankcustomerenterprise.model.document.CustomerEnterpriseDoc;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerEnterpriseService {
	
	public Flux<CustomerEnterpriseDoc> findAll();
	public Mono<CustomerEnterpriseDoc> findById(String id);
	public Mono<CustomerEnterpriseDoc> save(CustomerEnterpriseDoc customerEnterpriseDoc);
	public Mono<Void> delete(CustomerEnterpriseDoc customerEnterpriseDoc);

}
