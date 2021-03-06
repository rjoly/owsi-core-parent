package fr.openwide.core.test.wicket.more.link.descriptor;

import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.ResourceReference;

import fr.openwide.core.commons.util.functional.SerializableFunction;
import fr.openwide.core.test.wicket.more.link.descriptor.application.WicketMoreTestLinkDescriptorApplication;
import fr.openwide.core.test.wicket.more.link.descriptor.resource.TestLinkDescriptorNoParameterResource;
import fr.openwide.core.test.wicket.more.link.descriptor.resource.TestLinkDescriptorOneParameterResource;
import fr.openwide.core.wicket.more.link.descriptor.IImageResourceLinkDescriptor;
import fr.openwide.core.wicket.more.link.descriptor.ILinkDescriptor;
import fr.openwide.core.wicket.more.link.descriptor.IPageLinkDescriptor;
import fr.openwide.core.wicket.more.link.descriptor.IResourceLinkDescriptor;
import fr.openwide.core.wicket.more.link.descriptor.builder.state.parameter.chosen.common.IOneChosenParameterState;
import fr.openwide.core.wicket.more.link.descriptor.builder.state.terminal.ILateTargetDefinitionTerminalState;
import fr.openwide.core.wicket.more.link.descriptor.mapper.IOneParameterLinkDescriptorMapper;
import fr.openwide.core.wicket.more.markup.html.factory.DetachableFactories;
import fr.openwide.core.wicket.more.markup.html.factory.ModelFactories;
import fr.openwide.core.wicket.more.model.ReadOnlyModel;

public class TestImageResourceLinkDescriptorMapper extends AbstractAnyTargetTestLinkDescriptorMapper {

	@Override
	protected <T> IOneParameterLinkDescriptorMapper<? extends ILinkDescriptor, T> buildWithNullTarget(
			ILateTargetDefinitionTerminalState<
					IOneParameterLinkDescriptorMapper<IPageLinkDescriptor, T>,
					IOneParameterLinkDescriptorMapper<IResourceLinkDescriptor, T>,
					IOneParameterLinkDescriptorMapper<IImageResourceLinkDescriptor, T>
					> builder) {
		return builder.imageResource(Model.of((ResourceReference) null));
	}
	
	@Override
	protected <T> IOneParameterLinkDescriptorMapper<? extends ILinkDescriptor, T> buildWithOneParameterTarget(
			ILateTargetDefinitionTerminalState<
					IOneParameterLinkDescriptorMapper<IPageLinkDescriptor, T>,
					IOneParameterLinkDescriptorMapper<IResourceLinkDescriptor, T>,
					IOneParameterLinkDescriptorMapper<IImageResourceLinkDescriptor, T>
					> builder) {
		return builder.imageResource(TestLinkDescriptorOneParameterResource.REFERENCE);
	}

	@Override
	protected <T> IOneParameterLinkDescriptorMapper<? extends ILinkDescriptor, T>
			buildWithParameterDependentNullTarget(IOneChosenParameterState<
					?,
					T,
					IOneParameterLinkDescriptorMapper<IPageLinkDescriptor, T>,
					IOneParameterLinkDescriptorMapper<IResourceLinkDescriptor, T>,
					IOneParameterLinkDescriptorMapper<IImageResourceLinkDescriptor, T>
					> builder) {
		return builder.imageResource(ModelFactories.constant(Model.of((ResourceReference) null)));
	}
	
	@Override
	protected <T> IOneParameterLinkDescriptorMapper<? extends ILinkDescriptor, T>
			buildWithParameterDependentTarget(IOneChosenParameterState<
					?,
					T,
					IOneParameterLinkDescriptorMapper<IPageLinkDescriptor, T>,
					IOneParameterLinkDescriptorMapper<IResourceLinkDescriptor, T>,
					IOneParameterLinkDescriptorMapper<IImageResourceLinkDescriptor, T>
					> builder) {
		return builder.imageResource(DetachableFactories.forUnit(ReadOnlyModel.factory(
				new SerializableFunction<T, ResourceReference>() {
					private static final long serialVersionUID = 1L;
					@Override
					public ResourceReference apply(T input) {
						return input == null
								? TestLinkDescriptorNoParameterResource.REFERENCE
								: TestLinkDescriptorOneParameterResource.REFERENCE;
					}
				}
		)));
	}

	@Override
	protected String getNoParameterTargetPathPrefix() {
		return WicketMoreTestLinkDescriptorApplication.RESOURCE_NO_PARAMETER_PATH_PREFIX;
	}

	@Override
	protected String getOneParameterTargetPathPrefix() {
		return WicketMoreTestLinkDescriptorApplication.RESOURCE_ONE_PARAMETER_PATH_PREFIX;
	}
}
