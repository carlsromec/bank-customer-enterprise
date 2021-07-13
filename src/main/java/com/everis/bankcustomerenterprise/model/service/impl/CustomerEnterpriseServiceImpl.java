package com.everis.bankcustomerenterprise.model.service.impl;

import com.everis.bankcustomerenterprise.model.dao.CategoryDao;
import com.everis.bankcustomerenterprise.model.dao.CustomerEnterpriseDao;
import com.everis.bankcustomerenterprise.model.document.CategoryDoc;
import com.everis.bankcustomerenterprise.model.document.CustomerEnterpriseDoc;
import com.everis.bankcustomerenterprise.model.service.CategoryService;
import com.everis.bankcustomerenterprise.model.service.CustomerEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerEnterpriseServiceImpl implements CustomerEnterpriseService, CategoryService {

    @Autowired
    private CustomerEnterpriseDao customerEnterpriseDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Flux<CustomerEnterpriseDoc> findAll() {
        return customerEnterpriseDao.findAll();
    }

    @Override
    public Mono<CustomerEnterpriseDoc> findById(String id) {
        return customerEnterpriseDao.findById(id);
    }

    @Override
    public Mono<CustomerEnterpriseDoc> save(CustomerEnterpriseDoc customerEnterpriseDoc) {
        return customerEnterpriseDao.save(customerEnterpriseDoc);
    }

    @Override
    public Mono<Void> delete(CustomerEnterpriseDoc customerEnterpriseDoc) {
        return customerEnterpriseDao.delete(customerEnterpriseDoc);
    }

    @Override
    public Flux<CategoryDoc> findAllCategory() {
        return categoryDao.findAll();
    }

    @Override
    public Mono<CategoryDoc> findByIdCategory(String id) {
        return categoryDao.findById(id);
    }

    @Override
    public Mono<CategoryDoc> saveCategory(CategoryDoc categoryDoc) {
        return categoryDao.save(categoryDoc);
    }
}
