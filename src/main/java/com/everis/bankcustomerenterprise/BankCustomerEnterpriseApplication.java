package com.everis.bankcustomerenterprise;

import com.everis.bankcustomerenterprise.model.document.CategoryDoc;
import com.everis.bankcustomerenterprise.model.document.CustomerEnterpriseDoc;
import com.everis.bankcustomerenterprise.model.service.CategoryService;
import com.everis.bankcustomerenterprise.model.service.CustomerEnterpriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

@EnableEurekaClient
@SpringBootApplication
public class BankCustomerEnterpriseApplication implements CommandLineRunner {

	private static final Logger log= LoggerFactory.getLogger(BankCustomerEnterpriseApplication.class);

	@Autowired
	private CustomerEnterpriseService customerEnterpriseService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(BankCustomerEnterpriseApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		reactiveMongoTemplate.dropCollection("CustomerEnterpriseDoc").subscribe();
		reactiveMongoTemplate.dropCollection("CategoryDoc").subscribe();

		CategoryDoc PYME= new CategoryDoc("PYME");
		CategoryDoc normal = new CategoryDoc("normal");

		Flux.just(PYME,normal)
				.flatMap(categoryService::saveCategory)
				.doOnNext(t ->{
					log.info("Category created: " +t.getDescription() + " id: " +t.getId());
				}).thenMany(
						Flux.just(
							new CustomerEnterpriseDoc("203030303030","pinturas sac","lima 333","999666555",PYME),
								new CustomerEnterpriseDoc("204040404040","librod sac","loreto 333","999444222",normal),
								new CustomerEnterpriseDoc("201010101010","maderas sac","lomas 1212","999111555",PYME)
						)
				.flatMap(customerEnterprise -> {
					return customerEnterpriseService.save(customerEnterprise);
				})
		).subscribe(customerEnterprise -> log.info("insert: " + customerEnterprise.getId()));

	}
}
