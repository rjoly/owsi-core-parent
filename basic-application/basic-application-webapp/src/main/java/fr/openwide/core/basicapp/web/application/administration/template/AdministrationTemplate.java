package fr.openwide.core.basicapp.web.application.administration.template;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import fr.openwide.core.basicapp.web.application.administration.page.AdministrationBasicUserPortfolioPage;
import fr.openwide.core.basicapp.web.application.common.template.MainTemplate;
import fr.openwide.core.jpa.security.business.authority.util.CoreAuthorityConstants;
import fr.openwide.core.wicket.more.markup.html.template.model.BreadCrumbElement;

@AuthorizeInstantiation(CoreAuthorityConstants.ROLE_ADMIN)
public abstract class AdministrationTemplate extends MainTemplate {

	private static final long serialVersionUID = -5571981353426833725L;

	public AdministrationTemplate(PageParameters parameters) {
		super(parameters);
		
		addBreadCrumbElement(new BreadCrumbElement(new ResourceModel("navigation.administration")));
	}

	@Override
	protected Class<? extends WebPage> getFirstMenuPage() {
		return AdministrationBasicUserPortfolioPage.class;
	}

	@Override
	protected abstract Class<? extends WebPage> getSecondMenuPage();
}
