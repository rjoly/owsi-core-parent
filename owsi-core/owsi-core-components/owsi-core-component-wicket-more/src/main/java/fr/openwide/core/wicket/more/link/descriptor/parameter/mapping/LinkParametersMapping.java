package fr.openwide.core.wicket.more.link.descriptor.parameter.mapping;

import java.util.Collection;

import org.apache.wicket.Component;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IComponentAssignedModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.IWrapModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.lang.Args;

import com.google.common.collect.ImmutableList;

import fr.openwide.core.wicket.more.link.descriptor.builder.LinkDescriptorBuilder;
import fr.openwide.core.wicket.more.link.descriptor.parameter.extractor.LinkParameterExtractionException;
import fr.openwide.core.wicket.more.link.descriptor.parameter.extractor.LinkParameterExtractionRuntimeException;
import fr.openwide.core.wicket.more.link.descriptor.parameter.injector.LinkParameterInjectionException;
import fr.openwide.core.wicket.more.link.descriptor.parameter.injector.LinkParameterInjectionRuntimeException;
import fr.openwide.core.wicket.more.link.service.ILinkParameterConversionService;

/**
 * Implements a mapping between {@link IModel models} and their string representations as {@link PageParameters}.
 * <p>Consequently, the parameters values can change over time, especially between two Ajax refreshes.
 * <p>This is a read <em>and</em> write model, which means it allows setting the underlying models value by {@link #setObject(PageParameters) settings its own value}.
 * This feature, though, depends on two things:
 * <ul>
 * 	<li>the ability for the underlying models to support the {@link IModel#setObject(Object)} operation.
 * If they do not, the {@link LinkParametersMapping#setObject(PageParameters)} operation will crash.</li>
 * 	<li>the ability for the {@link ILinkParameterConversionService} to convert the application-side types of the parameters.
 * This should be checked upstream, for example in the {@link LinkDescriptorBuilder}.
 * </ul>
 */
public class LinkParametersMapping implements IModel<PageParameters>, IComponentAssignedModel<PageParameters> {
	
	private static final long serialVersionUID = -9066291686294702275L;
	
	private final Collection<LinkParameterMappingEntry<?>> parameterMappingEntries;
	
	@SpringBean
	private ILinkParameterConversionService conversionService;

	public LinkParametersMapping(Collection<LinkParameterMappingEntry<?>> parameterMappingEntries) {
		super();
		Injector.get().inject(this);
		this.parameterMappingEntries = ImmutableList.copyOf(parameterMappingEntries);
	}
	
	@Override
	public PageParameters getObject() throws LinkParameterInjectionRuntimeException {
		PageParameters result = new PageParameters();

		for (LinkParameterMappingEntry<?> parameterMappingEntry : parameterMappingEntries) {
			try {
				parameterMappingEntry.inject(result, conversionService);
			} catch (LinkParameterInjectionException e) {
				throw new LinkParameterInjectionRuntimeException(e);
			}
		}
		
		return result;
	}
	
	@Override
	public void setObject(PageParameters object) throws LinkParameterExtractionRuntimeException {
		Args.notNull(object, "object");
		
		for (LinkParameterMappingEntry<?> parameterMappingEntry : parameterMappingEntries) {
			try {
				parameterMappingEntry.extract(object, conversionService);
			} catch (LinkParameterExtractionException e) {
				throw new LinkParameterExtractionRuntimeException(e);
			}
		}
	}
	
	@Override
	public IWrapModel<PageParameters> wrapOnAssignment(Component component) {
		return new WrapModel(component);
	}

	@Override
	public void detach() {
		for (LinkParameterMappingEntry<?> parameterMappingEntry : parameterMappingEntries) {
			parameterMappingEntry.detach();
		}
	}
	
	private class WrapModel extends LinkParametersMapping implements IWrapModel<PageParameters> {
		private static final long serialVersionUID = -1776808095158473219L;

		public WrapModel(Component component) {
			super(wrapParameterModelMap(LinkParametersMapping.this.parameterMappingEntries, component));
		}

		@Override
		public IModel<?> getWrappedModel() {
			return LinkParametersMapping.this;
		}
	}
	
	private static Collection<LinkParameterMappingEntry<?>> wrapParameterModelMap(Collection<LinkParameterMappingEntry<?>> parameterMappingEntries, Component component) {
		ImmutableList.Builder<LinkParameterMappingEntry<?>> builder = ImmutableList.builder();
		for (LinkParameterMappingEntry<?> parameterMappingEntry : parameterMappingEntries) {
			builder.add(parameterMappingEntry.wrap(component));
		}
		return builder.build();
	}

}