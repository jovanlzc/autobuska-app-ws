package com.qubikapp.app.ws.repository.specifications;

import java.util.Calendar;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.qubikapp.app.ws.entity.PolazakEntity;

public class PolazakSpecifications implements Specification<PolazakEntity>{
	private SearchCriteria searchCriteria;
	@Override
	public Predicate toPredicate(Root<PolazakEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		if(searchCriteria.getOperation().equalsIgnoreCase(">")) {
			return criteriaBuilder.greaterThanOrEqualTo(root.<String>get(searchCriteria.getKey()),searchCriteria.getValue().toString() );
		}else if(searchCriteria.getOperation().equalsIgnoreCase("<")) {
			return criteriaBuilder.lessThan(root.<String>get(searchCriteria.getKey()), searchCriteria.getValue().toString());
		}else if(searchCriteria.getOperation().equals(":")) {
			if(root.get(searchCriteria.getKey()).getJavaType()==String.class) {
				return criteriaBuilder.like(root.<String>get(searchCriteria.getKey()), "%"+searchCriteria.getValue()+"%");
			}else if(root.get(searchCriteria.getKey()).getJavaType()==Calendar.class){
				//provera da li je istog dana,  jer je timestamp tip atributa u bazi i gleda i po datumu i vremenu a treba samo po datumu
				return criteriaBuilder.between(root.<Calendar>get(searchCriteria.getKey()).as(java.util.Calendar.class), criteriaBuilder.literal((Calendar)searchCriteria.getValue()),criteriaBuilder.literal((Calendar)searchCriteria.getValue()));
			}else {
				return criteriaBuilder.equal(root.get(searchCriteria.getKey()),searchCriteria.getValue());
			}
		}

		return null;
	}
	
}
