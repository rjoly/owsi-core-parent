package fr.openwide.core.wicket.more.link.descriptor.builder.state.parameter.chosen;

import fr.openwide.core.wicket.more.link.descriptor.builder.state.parameter.chosen.common.ITwoChosenParameterState;

public interface ITwoMappableParameterTwoChosenParameterState
		<
		InitialState,
		TParam1, TParam2,
		TChosenParam1, TChosenParam2,
		TLateTargetDefinitionPageResult,
		TLateTargetDefinitionResourceResult,
		TLateTargetDefinitionImageResourceResult
		>
		extends ITwoChosenParameterState
						<
						InitialState,
						TChosenParam1, TChosenParam2,
						TLateTargetDefinitionPageResult,
						TLateTargetDefinitionResourceResult,
						TLateTargetDefinitionImageResourceResult
						> {

}
