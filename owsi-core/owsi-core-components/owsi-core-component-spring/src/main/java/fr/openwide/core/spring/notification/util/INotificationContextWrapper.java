package fr.openwide.core.spring.notification.util;

import fr.openwide.core.spring.notification.model.INotificationContentDescriptor;
import fr.openwide.core.spring.notification.model.INotificationRecipient;

public interface INotificationContextWrapper<C> {
	
	/**
	 * @param recipient The notification recipient.
	 * @param contextDescriptor The notification content descriptor
	 * @return A notification content descriptor that wraps <code>contextDescriptor</code> and sets up context relative
	 * to the recipient before executing the wrapped descriptor. This content descriptor implements {@link #equals(Object)}
	 * and {@link #hashCode()} so that the rendering of a single notification for multiple recipients whose context is
	 * the same may be executed only once.   
	 */
	INotificationContentDescriptor contextualize(INotificationRecipient recipient, INotificationContentDescriptor contextDescriptor);

}
