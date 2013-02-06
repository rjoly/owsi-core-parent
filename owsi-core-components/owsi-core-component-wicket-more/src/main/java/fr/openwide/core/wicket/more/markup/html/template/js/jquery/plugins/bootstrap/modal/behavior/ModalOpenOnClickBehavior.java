package fr.openwide.core.wicket.more.markup.html.template.js.jquery.plugins.bootstrap.modal.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.odlabs.wiquery.core.events.Event;
import org.odlabs.wiquery.core.events.MouseEvent;
import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsStatement;

import fr.openwide.core.wicket.more.markup.html.template.js.jquery.plugins.bootstrap.modal.BootstrapModalJavaScriptResourceReference;
import fr.openwide.core.wicket.more.markup.html.template.js.jquery.plugins.bootstrap.modal.statement.BootstrapModal;
import fr.openwide.core.wicket.more.markup.html.template.js.jquery.plugins.bootstrap.modal.statement.BootstrapModalManagerStatement;
import fr.openwide.core.wicket.more.markup.html.template.js.jquery.plugins.util.JQueryAbstractBehavior;

public class ModalOpenOnClickBehavior extends JQueryAbstractBehavior {

	private static final long serialVersionUID = 8188257386595829052L;

	private final Component modal;

	private final BootstrapModal options;

	/**
	 * @param modal - le composant qui contient la popup
	 */
	public ModalOpenOnClickBehavior(Component modal) {
		this(modal, null);
	}

	/**
	 * @param modal - le composant qui contient la popup
	 * @param options - peut être null (options par défaut)
	 */
	public ModalOpenOnClickBehavior(Component modal, BootstrapModal options) {
		super();
		this.modal = modal;
		this.options = options;
	}

	public JsStatement statement() {
		Event event = new Event(MouseEvent.CLICK) {
			private static final long serialVersionUID = 1410592312776274815L;
			
			@Override
			public JsScope callback() {
				return JsScope.quickScope(BootstrapModalManagerStatement.show(modal, options));
			}
		};
		return new JsStatement().$(getComponent()).chain(event);
	}

	@Override
	public void renderHead(Component component, IHeaderResponse response) {
		super.renderHead(component, response);
		
		response.render(JavaScriptHeaderItem.forReference(BootstrapModalJavaScriptResourceReference.get()));
		response.render(OnDomReadyHeaderItem.forScript(statement().render()));
	}

}
