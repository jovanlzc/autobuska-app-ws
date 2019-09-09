package com.qubikapp.app.ws.repository.specifications;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.qubikapp.app.ws.entity.AutobuskaLinijaEntity;
import com.qubikapp.app.ws.entity.MestoEntity;
import com.qubikapp.app.ws.entity.PolazakEntity;
import com.qubikapp.app.ws.entity.PrevoznikEntity;

public class PolazakSpecifications1 {

	public static Specification<PolazakEntity> mestoPolaska(String mestoId) {
		return new Specification<PolazakEntity>() {

			@Override
			public Predicate toPredicate(Root<PolazakEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				Join<PolazakEntity,MestoEntity> mestoPolaska= root.join("linija").join("mestoPolaska");
				Predicate equalPredicate = criteriaBuilder.equal(mestoPolaska.get("mestoPolaska").<String>get("mestoId"), mestoId);
				query.distinct(true);
				return equalPredicate;
			}
		};

	}

	public static Specification<PolazakEntity> mestoDolaska(String mestoId) {
		return new Specification<PolazakEntity>() {

			@Override
			public Predicate toPredicate(Root<PolazakEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				Join<PolazakEntity, AutobuskaLinijaEntity> linijaPolazak = root.join("linija");
				Join<PolazakEntity, MestoEntity> mestoLinija = linijaPolazak.join("mestoDolaska");
				Predicate equalPredicate = criteriaBuilder.equal(mestoLinija.get("mestoId"), mestoId);
				query.distinct(true);
				return equalPredicate;

			}
		};
	}

	public static Specification<PolazakEntity> prevoznik(String prevoznikId) {
		return new Specification<PolazakEntity>() {

			@Override
			public Predicate toPredicate(Root<PolazakEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
				try {
					Join<PolazakEntity, AutobuskaLinijaEntity> linijaPolazak = root.join("linija");
					Join<PolazakEntity, PrevoznikEntity> prevoznikLinija = linijaPolazak.join("prevoznik");
					return criteriaBuilder.equal(prevoznikLinija.<String>get("id"), prevoznikId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}

	public static Specification<PolazakEntity> datum(String datumPolaska) {
		return new Specification<PolazakEntity>() {

			@Override
			public Predicate toPredicate(Root<PolazakEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				// TODO Auto-generated method stub
//				Timestamp datumTmp =(Timestamp) root.<Timestamp>get("datum");
//				Calendar datum = Calendar.getInstance();
//				datum.setTimeInMillis(datumTmp.getTime());
//				SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
//				String datumP= sdf.format(datum);
//				return criteriaBuilder.equal(criteriaBuilder.literal(datumP), criteriaBuilder.literal(datumPolaska));
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				Date pocetniDatum = new Date();
				Date krajnjiDatum = new Date();
				try {
					pocetniDatum = sdf.parse(datumPolaska);
					krajnjiDatum = sdf.parse(datumPolaska);

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Calendar pocDatum = Calendar.getInstance();
				Calendar krajDatum = Calendar.getInstance();
				pocDatum.setTime(pocetniDatum);
				pocDatum.set(Calendar.HOUR_OF_DAY, 0);
				pocDatum.set(Calendar.MINUTE, 0);
				krajDatum.setTime(krajnjiDatum);
				krajDatum.set(Calendar.DATE, krajDatum.get(Calendar.DATE) + 1);
				krajDatum.set(Calendar.HOUR_OF_DAY, 0);
				krajDatum.set(Calendar.MINUTE, 0);

				return criteriaBuilder.between(root.<Calendar>get("datum").as(java.util.Calendar.class),
						criteriaBuilder.literal(pocDatum), criteriaBuilder.literal(krajDatum));

			}
		};
	}
}
