package fr.openwide.core.wicket.more.security.authorization;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.settings.SecuritySettings;

/**
 * Default behavior on unauthroized instantiation, just like the default one in {@link SecuritySettings}: just
 * throw an {@link UnauthorizedInstantiationException}.
 */
public class StandardUnauthorizedComponentInstantiationListener implements IUnauthorizedComponentInstantiationListener {

	@Override
	public void onUnauthorizedInstantiation(Component component) {
		throw new UnauthorizedInstantiationException(component.getClass());
	}

}
