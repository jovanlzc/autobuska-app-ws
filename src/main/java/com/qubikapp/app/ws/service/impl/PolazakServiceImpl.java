package com.qubikapp.app.ws.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.annotations.Where;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.qubikapp.app.ws.entity.PolazakEntity;
import com.qubikapp.app.ws.entity.TipPolaska;
import com.qubikapp.app.ws.repository.PolazakRepository;
import com.qubikapp.app.ws.repository.specifications.PolazakExpressions;
import com.qubikapp.app.ws.repository.specifications.PolazakSpecifications1;
import com.qubikapp.app.ws.service.PolazakService;
import com.qubikapp.app.ws.shared.Utils;
import com.qubikapp.app.ws.shared.dto.PolazakDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

@Service
public class PolazakServiceImpl implements PolazakService {

	@Autowired
	private PolazakRepository polazakRepository;
	@Autowired
	private Utils utils;

	@Override
	public List<PolazakDto> vratiSvePolaske(String mestoPolaskaId, String mestoDolaskaId, String prevoznikId,
			String datum) {
		// TODO Auto-generated method stub
		List<PolazakDto> returnValue = new ArrayList<>();
		BooleanExpression be = null;
		if (mestoPolaskaId != null) {
			be = PolazakExpressions.mestoPolaska(mestoPolaskaId);
		}
		if (mestoDolaskaId != null) {
			if (be == null) {
				be = PolazakExpressions.mestoDolaska(mestoDolaskaId);
			} else {
				be.and(PolazakExpressions.mestoDolaska(mestoDolaskaId));
			}
		}
		if (prevoznikId != null) {
			if (be == null) {
				be = PolazakExpressions.prevoznik(prevoznikId);
			} else {
				be.and(PolazakExpressions.prevoznik(prevoznikId));
			}
		}
//		if(datum!=null) {
//			if(be==null) {
//				be=PolazakExpressions.datum(datum);
//			}else {
//				be.and(PolazakExpressions.datum(datum));
//			}
//		}
		if (mestoPolaskaId == null && mestoDolaskaId == null && prevoznikId == null && datum == null)
			be = PolazakExpressions.mestoPolaska(mestoPolaskaId).and(PolazakExpressions.mestoDolaska(mestoDolaskaId))
					.and(PolazakExpressions.prevoznik(prevoznikId));
//		Iterable<PolazakEntity> polasci = polazakRepository.findAll(PolazakSpecifications1.mestoDolaska(mestoDolaskaId).and(PolazakSpecifications1.mestoPolaska(mestoPolaskaId).and(PolazakSpecifications1.prevoznik(prevoznikId).and(PolazakSpecifications1.datum(datum)))));
//		Predicate pred = PolazakExpressions.mestoPolaska(mestoPolaskaId).and(PolazakExpressions.mestoDolaska(mestoDolaskaId));
//		Iterable<PolazakEntity> polasci = polazakRepository.findAll(
//				PolazakExpressions.mestoPolaska(mestoPolaskaId).and(PolazakExpressions.mestoDolaska(mestoDolaskaId))
//						.and(PolazakExpressions.prevoznik(prevoznikId).and(PolazakExpressions.datum(datum))));
//		Iterable<PolazakEntity> polasci = polazakRepository.getPolasciByFilters(new Long(mestoPolaskaId),new Long(mestoDolaskaId),new Long(prevoznikId), datum);
		Iterable<PolazakEntity> polasci = polazakRepository.findAll(be);
		List<PolazakEntity> sviPolasci = new ArrayList<>();
		polasci.forEach(sviPolasci::add);
		for (PolazakEntity pe : sviPolasci) {
			PolazakDto pd = new PolazakDto();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date selektovaniDatumDate;
			Calendar selDatum = pe.getDatum();
			TipPolaska tipPolaska = pe.getTipPolaska();
			try {
				selektovaniDatumDate = sdf.parse(datum);
				Calendar selektovaniDatum = Calendar.getInstance();
//				pocCalendar.setTime(datum);
				selektovaniDatum.setTime(selektovaniDatumDate);
				if ((tipPolaska == TipPolaska.CEL || (tipPolaska == TipPolaska.RADNI_DAN
						&& (selektovaniDatum.get(Calendar.DAY_OF_WEEK) > 1 || selektovaniDatum.get(Calendar.DAY_OF_WEEK) < 7))
						|| (tipPolaska == TipPolaska.VIKEND && (selektovaniDatum.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
								|| selektovaniDatum.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY))
						|| (tipPolaska == TipPolaska.SPEC
								&& selektovaniDatum.get(Calendar.DAY_OF_YEAR) == selDatum.get(Calendar.DAY_OF_YEAR)))
						&& ( ( (selektovaniDatum.get(Calendar.DAY_OF_YEAR)> Calendar.getInstance().get(Calendar.DAY_OF_YEAR)) && (selektovaniDatum.get(Calendar.YEAR)>=Calendar.getInstance().get(Calendar.YEAR))) || ((selektovaniDatum.get(Calendar.DAY_OF_YEAR)==Calendar.getInstance().get(Calendar.DAY_OF_YEAR) && selektovaniDatum.get(Calendar.YEAR)>=Calendar.getInstance().get(Calendar.YEAR)) && ((selDatum.get(Calendar.HOUR_OF_DAY) > Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
								|| (selDatum.get(Calendar.HOUR_OF_DAY) == Calendar.getInstance()
										.get(Calendar.HOUR_OF_DAY)
										&& selDatum.get(Calendar.MINUTE) > Calendar.getInstance().get(Calendar.MINUTE)))
										))) {
					selektovaniDatum.set(Calendar.HOUR_OF_DAY, selDatum.get(Calendar.HOUR_OF_DAY));
					selektovaniDatum.set(Calendar.MINUTE,selDatum.get(Calendar.MINUTE));
					BeanUtils.copyProperties(pe, pd);
					pd.setDatum(selektovaniDatum);
					returnValue.add(pd);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		}
		java.util.Collections.sort(returnValue, new Comparator<PolazakDto>() {

			@Override
			public int compare(PolazakDto p1, PolazakDto p2) {
				// TODO Auto-generated method stub
				return p1.getDatum().getTime().compareTo(p2.getDatum().getTime());

			}
		});
		return returnValue;
	}

	@Override
	public PolazakDto kreirajPolazak(PolazakDto polazak) {

		ModelMapper modelMapper = new ModelMapper();
		PolazakEntity polazakEntity = modelMapper.map(polazak, PolazakEntity.class);
		String polazakId = utils.generateUserId(30);
		polazakEntity.setPolazakId(polazakId);
		PolazakEntity kreiraniPolazak = polazakRepository.save(polazakEntity);
		PolazakDto returnValue = modelMapper.map(kreiraniPolazak, PolazakDto.class);
		return returnValue;
	}

	@Override
	public PolazakDto updatePolazak(PolazakDto polazak) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePolazak(PolazakDto polazak) {
		// TODO Auto-generated method stub

	}

}
