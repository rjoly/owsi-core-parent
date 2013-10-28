package fr.openwide.core.wicket.more.markup.html.template.component;

import org.apache.wicket.markup.html.link.Link;

import fr.openwide.core.wicket.markup.html.panel.GenericPanel;
import fr.openwide.core.wicket.more.markup.html.template.model.BreadCrumbElement;

public class LinkDescriptorBreadCrumbElementPanel extends GenericPanel<String> {

	private static final long serialVersionUID = 5385792712763242343L;

	public LinkDescriptorBreadCrumbElementPanel(String id, BreadCrumbElement breadCrumbElement) {
		super(id, breadCrumbElement.getLabelModel());
		
		Link<Void> link = breadCrumbElement.getLinkDescriptor().link("breadCrumbElementLink").setAutoHideIfInvalid(true);
		link.setBody(getModel());
		
		add(link);
	}

}
