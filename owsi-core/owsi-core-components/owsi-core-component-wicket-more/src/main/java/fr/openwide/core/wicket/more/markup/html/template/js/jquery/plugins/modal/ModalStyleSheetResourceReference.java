package fr.openwide.core.wicket.more.markup.html.template.js.jquery.plugins.modal;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

public final class ModalStyleSheetResourceReference extends
		JavaScriptResourceReference {
	private static final long serialVersionUID = -9209473494619975852L;
	
	private static final ModalStyleSheetResourceReference INSTANCE = new ModalStyleSheetResourceReference();

	private ModalStyleSheetResourceReference() {
		super(ModalStyleSheetResourceReference.class, "jquery.fancybox-1.3.5-ow.css");
	}
	
	public static ModalStyleSheetResourceReference get() {
		return INSTANCE;
	}

}
