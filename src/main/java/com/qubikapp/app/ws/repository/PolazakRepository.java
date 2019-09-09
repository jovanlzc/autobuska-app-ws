package com.qubikapp.app.ws.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qubikapp.app.ws.entity.PolazakEntity;

@Repository
public interface PolazakRepository extends CrudRepository<PolazakEntity, String>,QuerydslPredicateExecutor<PolazakEntity> {
//	@Query(value="Select * from polazak p join autobuska_linija al on p.linija_id=al.linija_id join mesto m1 on al.mesto_polaska=m1.mesto_id join mesto m2 on al.mesto_dolaska=m2.mesto_id join prevoznik pr on al.prevoznik=pr.prevoznik_id where (:mestoPolaskaId is null or m1.mesto_id=:mestoPolaskaId) and (:mestoDolaskaId is null or m2.mesto_id=:mestoDolaskaId) and (:prevoznikId is null or pr.prevoznik_id=:prevoznikId) and (:datum is null or DATE(p.datum)=:datum)",nativeQuery = true)
//	public List<PolazakEntity> getPolasciByFilters(@Param("mestoPolaskaId") Long mestoPolaskaId,@Param("mestoDolaskaId") Long mestoDolaska,@Param("prevoznikId") Long prevoznikId,@Param("datum") String datum );
}
