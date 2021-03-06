package fr.openwide.core.wicket.more.notification.service;

import org.apache.wicket.markup.ComponentTag;
import org.jsoup.nodes.Node;

import fr.openwide.core.jpa.exception.ServiceException;
import fr.openwide.core.wicket.more.css.lesscss.LessCssResourceReference;

public interface IHtmlNotificationCssService {
	
	void registerStyles(String componentVariation, LessCssResourceReference cssResourceReference) throws ServiceException;
	
	boolean hasRegistry(String componentVariation);
	
	IHtmlNotificationCssRegistry getRegistry(String componentVariation) throws ServiceException;
	
	interface IHtmlNotificationCssRegistry {
		
		String getStyle(ComponentTag tag);
		
		String getStyle(Node node);
	
	}
}
