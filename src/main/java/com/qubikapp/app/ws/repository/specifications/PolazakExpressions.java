package com.qubikapp.app.ws.repository.specifications;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.qubikapp.app.ws.entity.QPolazakEntity;
import com.qubikapp.app.ws.entity.TipPolaska;
import com.querydsl.core.types.Visitor;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.Expressions;

public class PolazakExpressions {
	public static BooleanExpression mestoPolaska(String mestoPolaskaId) {
		return QPolazakEntity.polazakEntity.linija.mestoPolaska.mestoId.eq(new Long(mestoPolaskaId));

	}

	public static BooleanExpression mestoDolaska(String mestoDolaskaId) {
		return QPolazakEntity.polazakEntity.linija.mestoDolaska.mestoId.eq(new Long(mestoDolaskaId));

	}

	public static BooleanExpression prevoznik(String prevoznikId) {
		return QPolazakEntity.polazakEntity.linija.prevoznik.id.eq(new Long(prevoznikId));
	}

	public static BooleanExpression datum(String datum1) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date selektovaniDatumDate;
		DateTimePath<Calendar> selDatumPath = QPolazakEntity.polazakEntity.datum;
		EnumPath<TipPolaska> tipPolaska = QPolazakEntity.polazakEntity.tipPolaska;
		Calendar selDatum = Calendar.getInstance();
		Date today = new Date();
		Calendar todayCal = Calendar.getInstance();
		todayCal.setTime(today);
//		selDatumPath.dayOfYear().eq(sel)
		try {
			selektovaniDatumDate = sdf.parse(datum1);
			Calendar selektovaniDatum = Calendar.getInstance();
//			pocCalendar.setTime(datum);
			selektovaniDatum.setTime(selektovaniDatumDate);
			System.out.println(todayCal.get(Calendar.HOUR_OF_DAY));
//			return selDatumPath.hour().lt(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)).or(selDatumPath.hour().eq(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)).and(selDatumPath.minute().lt(Calendar.getInstance().get(Calendar.MINUTE))));
			return Expressions.asBoolean(selDatumPath.hour().gt(todayCal.get(Calendar.HOUR_OF_DAY))
					.or(selDatumPath.hour().eq(todayCal.get(Calendar.HOUR_OF_DAY))
							.and(selDatumPath.minute().gt(todayCal.get(Calendar.MINUTE)))))
					.and(Expressions.asBoolean(tipPolaska.eq(TipPolaska.CEL)
							.or(tipPolaska.eq(TipPolaska.RADNI_DAN)
									.and(Expressions.asBoolean(selektovaniDatum.get(Calendar.DAY_OF_WEEK) > 1))
									.and(Expressions.asBoolean(selektovaniDatum.get(Calendar.DAY_OF_WEEK) < 7))))
							.or(tipPolaska.eq(TipPolaska.VIKEND).and(Expressions.asBoolean(Expressions.asBoolean(selektovaniDatum.get(Calendar.DAY_OF_WEEK) ==Calendar.SATURDAY)).or(Expressions.asBoolean(selektovaniDatum.get(Calendar.DAY_OF_WEEK) ==Calendar.SUNDAY))))
							.or(tipPolaska.eq(TipPolaska.SPEC).and(selDatumPath.dayOfYear().eq(selektovaniDatum.get(Calendar.DAY_OF_YEAR)))));

			// pocCalendar.set(Calendar.HOUR_OF_DAY,0);
//			pocCalendar.set(Calendar.MINUTE, 0);
//			krajCalendar.set(Calendar.HOUR_OF_DAY,23);
//			krajCalendar.set(Calendar.MINUTE, 59);
//			krajCalendar.set(Calendar.SECOND,59);
//			return QPolazakEntity.polazakEntity.datum.between(pocCalendar, krajCalendar);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
