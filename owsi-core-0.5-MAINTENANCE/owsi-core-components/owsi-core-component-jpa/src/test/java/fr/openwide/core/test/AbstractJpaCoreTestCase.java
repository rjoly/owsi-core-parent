/*
 * Copyright (C) 2009-2010 Open Wide
 * Contact: contact@openwide.fr
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.openwide.core.test;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit38.AbstractJUnit38SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import fr.openwide.core.jpa.exception.SecurityServiceException;
import fr.openwide.core.jpa.exception.ServiceException;
import fr.openwide.core.jpa.util.EntityManagerUtils;
import fr.openwide.core.spring.config.CoreConfigurer;
import fr.openwide.core.test.jpa.example.business.label.model.Label;
import fr.openwide.core.test.jpa.example.business.label.service.LabelService;
import fr.openwide.core.test.jpa.example.business.person.model.Person;
import fr.openwide.core.test.jpa.example.business.person.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {
		"classpath*:database-initialization-context.xml",
		"classpath*:application-context.xml"
})
public abstract class AbstractJpaCoreTestCase extends AbstractJUnit38SpringContextTests {
	
	@Autowired
	protected CoreConfigurer configurer;
	
	@Autowired
	protected EntityManagerUtils entityManagerUtils;
	
	@Autowired
	protected PersonService personService;
	
	@Autowired
	protected LabelService labelService;
	
	protected DataSource hibernateDataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.hibernateDataSource = dataSource;
	}
	
	protected void cleanPersons() throws ServiceException, SecurityServiceException {
		List<Person> persons = personService.list();
		for (Person person : persons) {
			personService.delete(person);
		}
	}
	
	protected void cleanLabels() throws ServiceException, SecurityServiceException {
		List<Label> labels = labelService.list();
		for (Label label : labels) {
			labelService.delete(label);
		}
	}

	protected void cleanAll() throws ServiceException, SecurityServiceException {
		cleanPersons();
		cleanLabels();
	}
	
	@Before
	public void init() throws ServiceException, SecurityServiceException {
		entityManagerUtils.openEntityManager();
		
		cleanAll();
	}
	
	@After
	public void close() throws ServiceException, SecurityServiceException {
		cleanAll();
		
		entityManagerUtils.closeEntityManager();
	}
}
