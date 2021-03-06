package fr.openwide.core.showcase.web.application.portfolio.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import fr.openwide.core.showcase.web.application.portfolio.component.UserPortfolioPanel;
import fr.openwide.core.showcase.web.application.portfolio.component.UserSearchPanel;
import fr.openwide.core.showcase.web.application.portfolio.model.UserDataProvider;
import fr.openwide.core.showcase.web.application.util.property.ShowcaseWebappPropertyIds;
import fr.openwide.core.showcase.web.application.util.template.MainTemplate;
import fr.openwide.core.wicket.more.link.descriptor.IPageLinkDescriptor;
import fr.openwide.core.wicket.more.link.descriptor.builder.LinkDescriptorBuilder;
import fr.openwide.core.wicket.more.markup.html.template.model.BreadCrumbElement;
import fr.openwide.core.wicket.more.model.ApplicationPropertyModel;

public class PortfolioMainPage extends MainTemplate {

	private static final long serialVersionUID = 6572019030268485555L;

	public static final IPageLinkDescriptor linkDescriptor() {
		return LinkDescriptorBuilder.start()
				.page(PortfolioMainPage.class);
	}
	
	public PortfolioMainPage(PageParameters parameters) {
		super(parameters);
		
		addBreadCrumbElement(new BreadCrumbElement(new ResourceModel("portfolio.pageTitle"), PortfolioMainPage.linkDescriptor()));
		
		UserDataProvider userDataProvider = new UserDataProvider();
		
		// Porfolio
		UserPortfolioPanel portfolioPanel = new UserPortfolioPanel("userPortfolio", userDataProvider, ApplicationPropertyModel.of(ShowcaseWebappPropertyIds.PORTFOLIO_ITEMS_PER_PAGE_USER).getObject());
		add(portfolioPanel);
		
		// Search
		add(new UserSearchPanel("userSearchPanel", portfolioPanel.getPageable(), userDataProvider));
	}
	
	@Override
	protected Class<? extends WebPage> getFirstMenuPage() {
		return PortfolioMainPage.class;
	}
	
	@Override
	protected Class<? extends WebPage> getSecondMenuPage() {
		return null;
	}
}
