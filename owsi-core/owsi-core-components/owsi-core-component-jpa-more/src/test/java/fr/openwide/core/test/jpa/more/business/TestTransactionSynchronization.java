package fr.openwide.core.test.jpa.more.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import fr.openwide.core.jpa.business.generic.model.GenericEntityReference;
import fr.openwide.core.jpa.exception.SecurityServiceException;
import fr.openwide.core.jpa.exception.ServiceException;
import fr.openwide.core.jpa.more.util.transaction.service.ITransactionSynchronizationTaskManagerService;
import fr.openwide.core.jpa.more.util.transaction.service.TransactionSynchronizationTaskManagerServiceImpl;
import fr.openwide.core.test.jpa.more.business.entity.model.TestEntity;
import fr.openwide.core.test.jpa.more.business.entity.service.ITestEntityService;
import fr.openwide.core.test.jpa.more.business.util.transaction.model.TestCreateAfterCommitTask;
import fr.openwide.core.test.jpa.more.business.util.transaction.model.TestDeleteOnRollbackTask;

public class TestTransactionSynchronization extends AbstractJpaMoreTestCase {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Autowired
	private ITransactionSynchronizationTaskManagerService transactionSynchronizationTaskManagerService;

	@Autowired
	private ITestEntityService testEntityService;

	private TransactionTemplate writeTransactionTemplate;

	private TransactionTemplate readOnlyTransactionTemplate;
	
	@Autowired
	private void setTransactionTemplate(PlatformTransactionManager transactionManager) {
		DefaultTransactionAttribute writeTransactionAttribute =
				new DefaultTransactionAttribute(TransactionAttribute.PROPAGATION_REQUIRED);
		writeTransactionAttribute.setReadOnly(false);
		writeTransactionTemplate = new TransactionTemplate(transactionManager, writeTransactionAttribute);
		DefaultTransactionAttribute readOnlyTransactionAttribute =
				new DefaultTransactionAttribute(TransactionAttribute.PROPAGATION_REQUIRED);
		readOnlyTransactionAttribute.setReadOnly(true);
		readOnlyTransactionTemplate = new TransactionTemplate(transactionManager, writeTransactionAttribute);
	}

	@Test
	public void testTaskNoTransaction() {
		exception.expect(IllegalStateException.class);
		exception.expectMessage(TransactionSynchronizationTaskManagerServiceImpl.EXCEPTION_MESSAGE_NO_ACTUAL_TRANSACTION_ACTIVE);
		
		// Cannot push tasks if there's no transaction
		transactionSynchronizationTaskManagerService.push(new TestCreateAfterCommitTask());
	}

	@Test
	public void testAfterCommitTask() {
		final TestCreateAfterCommitTask createAfterCommitTask = new TestCreateAfterCommitTask();
		readOnlyTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				transactionSynchronizationTaskManagerService.push(createAfterCommitTask);
			}
		});
		
		entityManagerClear();
		
		Collection<GenericEntityReference<Long, TestEntity>> createdEntities = createAfterCommitTask.getCreatedEntities();
		assertEquals(1, createdEntities.size());
		TestEntity createdEntity = testEntityService.getById(createdEntities.iterator().next());
		assertNotNull(createdEntity);
	}

	@Test
	public void testRollbackTask() throws ServiceException, SecurityServiceException {
		TestEntity entity = new TestEntity("entity");
		testEntityService.create(entity);
		
		final Long testEntityId = entity.getId();
		
		writeTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				transactionSynchronizationTaskManagerService.push(new TestDeleteOnRollbackTask(testEntityId));
				status.setRollbackOnly();
			}
		});
		
		entityManagerClear();
		
		assertNull(testEntityService.getById(testEntityId));
	}
}
