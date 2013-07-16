package fr.openwide.core.wicket.more.markup.html.basic;

public class EnclosureBehavior extends AbstractHideableBehavior<EnclosureBehavior> {

	private static final long serialVersionUID = -589273014057505964L;

	public EnclosureBehavior() {
		super(Visibility.HIDE_IF_EMPTY);
	}

	@Override
	protected EnclosureBehavior thisAsT() {
		return this;
	}

}