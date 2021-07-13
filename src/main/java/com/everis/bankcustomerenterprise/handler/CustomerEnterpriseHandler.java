package com.everis.bankcustomerenterprise.handler;

import com.everis.bankcustomerenterprise.model.document.CustomerEnterpriseDoc;
import com.everis.bankcustomerenterprise.model.service.CustomerEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class CustomerEnterpriseHandler {

    @Autowired
    private CustomerEnterpriseService customerEnterpriseService;

    public Mono<ServerResponse> listCustomerEnterprise(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(customerEnterpriseService.findAll(),CustomerEnterpriseDoc.class);
    }

    public Mono<ServerResponse> listCustomerEnterpriseId(ServerRequest request){
        String id = request.pathVariable("id");
        return customerEnterpriseService.findById(id).flatMap(p -> ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(p)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addCustomerEnterprise(ServerRequest request){
        Mono<CustomerEnterpriseDoc> enterprise = request.bodyToMono(CustomerEnterpriseDoc.class);

        return enterprise.flatMap(p ->{
            return customerEnterpriseService.save(p);
        }).flatMap(p->ServerResponse
                .created(URI.create("/api/v2/customerenterprise/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(p)));
    }

    public Mono<ServerResponse> editCustomerEnterprise(ServerRequest request){
        Mono<CustomerEnterpriseDoc> enterprise = request.bodyToMono(CustomerEnterpriseDoc.class);
        String id = request.pathVariable("id");

        Mono<CustomerEnterpriseDoc> enterprisedb = customerEnterpriseService.findById(id);
        return enterprisedb.zipWith(enterprise, (db, req) -> {
            db.setRuc(req.getRuc());
            db.setBusiness_name(req.getBusiness_name());
            db.setTelephone(req.getTelephone());
            db.setDirection(req.getDirection());
            db.setCategoryDoc(req.getCategoryDoc());
            return db;
        }).flatMap(p -> ServerResponse.created(URI.create("/api/v2/customerenterprise/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerEnterpriseService.save(p),CustomerEnterpriseDoc.class))
                .switchIfEmpty(ServerResponse.notFound().build());

        }




}
