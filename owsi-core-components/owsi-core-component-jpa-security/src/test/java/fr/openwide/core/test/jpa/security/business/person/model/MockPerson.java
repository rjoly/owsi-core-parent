package fr.openwide.core.test.jpa.security.business.person.model;

import javax.persistence.Entity;

import org.hibernate.search.annotations.Indexed;

import fr.openwide.core.jpa.security.business.person.model.AbstractPerson;

@Entity
@Indexed
public class MockPerson extends AbstractPerson<MockPerson, MockPersonGroup> {
	private static final long serialVersionUID = 4396833928821998996L;

}
