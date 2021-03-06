package fr.openwide.core.spring.notification.service;

import java.io.File;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import org.javatuples.LabelValue;
import org.springframework.util.MultiValueMap;

import fr.openwide.core.jpa.exception.ServiceException;

public interface INotificationBuilderSendState {
	
	/**
	 * @deprecated Use {@link INotificationBuilderBuildState#content(fr.openwide.core.spring.notification.model.INotificationContentDescriptor)} instead.
	 */
	@Deprecated
	INotificationBuilderSendState htmlBody(String htmlBody);
	
	/**
	 * @deprecated Use {@link INotificationBuilderBuildState#content(fr.openwide.core.spring.notification.model.INotificationContentDescriptor)} instead.
	 */
	@Deprecated
	INotificationBuilderSendState htmlBody(String htmlBody, Locale locale);

	INotificationBuilderSendState attach(String attachmentFilename, File file);
	
	INotificationBuilderSendState attach(Map<String, File> attachments);

	INotificationBuilderSendState attach(Collection<LabelValue<String, File>> attachments);

	INotificationBuilderSendState inline(String contentId, File file);

	INotificationBuilderSendState header(String name, String value);
	
	INotificationBuilderSendState headers(MultiValueMap<String, String> headers);
	
	INotificationBuilderSendState priority(int priority);
	
	void send() throws ServiceException;
	
}
