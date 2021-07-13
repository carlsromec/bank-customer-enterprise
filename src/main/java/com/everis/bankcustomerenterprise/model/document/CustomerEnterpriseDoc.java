package com.everis.bankcustomerenterprise.model.document;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "CustomerEnterpriseDoc")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CustomerEnterpriseDoc {
	
	@Id
	@NotEmpty
	private String id;
	@NotNull
	private String ruc;
	@NotNull
	private String business_name;
	@NotNull
	private String direction;
	@NotNull
	private String telephone;
	
	@Valid
	private CategoryDoc categoryDoc;

	public CustomerEnterpriseDoc(@NotNull String ruc, @NotNull String business_name, @NotNull String direction,
			@NotNull String telephone,
			com.everis.bankcustomerenterprise.model.document.@Valid CategoryDoc categoryDoc) {
		super();
		this.ruc = ruc;
		this.business_name = business_name;
		this.direction = direction;
		this.telephone = telephone;
		this.categoryDoc = categoryDoc;
	}

}
