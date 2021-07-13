package com.everis.bankcustomerenterprise.model.service;


import com.everis.bankcustomerenterprise.model.document.CategoryDoc;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    public Flux<CategoryDoc> findAllCategory();
    public Mono<CategoryDoc> findByIdCategory(String id);
    public Mono<CategoryDoc> saveCategory(CategoryDoc categoryDoc);

}
