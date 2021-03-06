package fr.openwide.core.basicapp.web.application.console.notification.demo.util;

import java.io.Serializable;
import java.util.Collection;

import org.apache.wicket.Localizer;
import org.apache.wicket.Session;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Classes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import fr.openwide.core.basicapp.core.business.notification.service.IBasicApplicationNotificationContentDescriptorFactory;
import fr.openwide.core.basicapp.web.application.console.notification.demo.page.ConsoleNotificationDemoIndexPage;
import fr.openwide.core.jpa.business.generic.model.GenericEntity;
import fr.openwide.core.jpa.business.generic.service.IEntityService;
import fr.openwide.core.spring.notification.model.INotificationContentDescriptor;

public abstract class NotificationDemoEntry extends AbstractReadOnlyModel<INotificationContentDescriptor> {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationDemoEntry.class);

	private static final long serialVersionUID = -4349817230833254700L;
	
	@SpringBean
	protected IEntityService entityService;
	
	@SpringBean
	protected IBasicApplicationNotificationContentDescriptorFactory descriptorService;
	
	private final String messageKeySuffix;

	public NotificationDemoEntry(String messageKeySuffix) {
		super();
		this.messageKeySuffix = messageKeySuffix;
		Injector.get().inject(this);
	}
	
	public IModel<String> getLabelModel() {
		return new ResourceModel("console.notifications." + messageKeySuffix, messageKeySuffix /* Default value */);
	}
	
	@Override
	public final INotificationContentDescriptor getObject() {
		return getDescriptor();
	}
	
	protected abstract INotificationContentDescriptor getDescriptor();

	protected final <E extends GenericEntity<Long, ?>> E getFirstInRange(Class<E> clazz, long minId, long maxId) {
		return getFirstInRange(clazz, Range.closed(minId, maxId));
	}

	protected final <E extends GenericEntity<Long, ?>> E getFirstInRange(Class<E> clazz, Range<Long> range) {
		return getFirstInRange(clazz, range, Predicates.<E>alwaysTrue());
	}
	
	protected final <E extends GenericEntity<Long, ?>> E getFirstInRange(Class<E> clazz, Range<Long> range, Predicate<E> predicate) {
		E entity = getFirstWithId(clazz, ContiguousSet.create(range, DiscreteDomain.longs()), predicate);
		
		if (entity != null) {
			return entity;
		} else {
			LOGGER.error("A demo object is missing for demo " + Classes.simpleName(getClass()));
			Session.get().error(Localizer.get().getString("console.notifications.demo.noDataAvailable", null));
			
			throw ConsoleNotificationDemoIndexPage.linkDescriptor().newRestartResponseException();
		}
	}
	
	protected final <K extends Comparable<K> & Serializable, E extends GenericEntity<K, ?>> E getFirstWithId(Class<E> clazz, Collection<K> ids, Predicate<E> predicate) {
		for (K id : ids) {
			E entity = entityService.getEntity(clazz, id);
			if (entity != null && predicate.apply(entity)) {
				return entity;
			}
		}
		
		return null;
	}
}
