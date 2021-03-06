package fr.openwide.core.basicapp.core.business.referencedata.dao;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;

import fr.openwide.core.basicapp.core.business.common.model.PostalCode;
import fr.openwide.core.basicapp.core.business.referencedata.model.City;
import fr.openwide.core.basicapp.core.business.referencedata.model.QCity;
import fr.openwide.core.jpa.business.generic.dao.GenericEntityDaoImpl;

@Repository
public class CityDaoImpl extends GenericEntityDaoImpl<Long, City> implements ICityDao {

	@Override
	public City getByLabelAndPostalCode(String label, PostalCode postalCode) {
		return new JPAQuery<>(getEntityManager())
				.from(QCity.city)
				.where(QCity.city.label.eq(label))
				.where(QCity.city.postalCode.eq(postalCode))
				.select(QCity.city)
				.fetchOne();
	}

}
