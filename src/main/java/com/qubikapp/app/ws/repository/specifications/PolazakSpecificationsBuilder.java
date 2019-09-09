package com.qubikapp.app.ws.repository.specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.qubikapp.app.ws.entity.PolazakEntity;

public class PolazakSpecificationsBuilder {
	private final List<SearchCriteria> params;
	
	public PolazakSpecificationsBuilder() {
		params= new ArrayList<SearchCriteria>();
	}
	
	public Specification<PolazakEntity> build(){
		return null;
 	}
}
