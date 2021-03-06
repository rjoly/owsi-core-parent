package fr.openwide.core.test.cache;

import org.hibernate.internal.SessionImpl;
import org.hibernate.stat.Statistics;
import org.junit.Assert;
import org.junit.Test;

import fr.openwide.core.jpa.exception.SecurityServiceException;
import fr.openwide.core.jpa.exception.ServiceException;
import fr.openwide.core.test.AbstractJpaCoreTestCase;
import fr.openwide.core.test.business.company.model.Company;
import fr.openwide.core.test.business.person.model.Person;

public class TestCache extends AbstractJpaCoreTestCase {
	
	@Test
	public void testCache() throws ServiceException, SecurityServiceException {
		getStatistics().setStatisticsEnabled(true);
		getStatistics().clear();
		Company company = new Company("Company Test Persist");
		Person person = new Person("Persist", "Numéro");
		companyService.create(company);
		personService.create(person);
		
		entityManagerReset();
		company = companyService.getById(company.getId());
		entityManagerReset();
		company = companyService.getById(company.getId());
		
		Assert.assertEquals(2, getStatistics().getSecondLevelCacheHitCount());
		Assert.assertEquals(0, getStatistics().getSecondLevelCacheMissCount());
		
		entityManagerReset();
		company = companyService.getById(company.getId());
		
		Assert.assertEquals(3, getStatistics().getSecondLevelCacheHitCount());
		Assert.assertEquals(0, getStatistics().getSecondLevelCacheMissCount());
		
		personService.getById(person.getId());
		
		// l'entité Person n'a pas l'annotation @Cache donc ne provoque pas de cache hit
		Assert.assertEquals(3, getStatistics().getSecondLevelCacheHitCount());
		Assert.assertEquals(0, getStatistics().getSecondLevelCacheMissCount());
	}
	
	protected Statistics getStatistics() {
		return ((SessionImpl) getEntityManager().getDelegate()).getSessionFactory().getStatistics();
	}
}
