package fr.openwide.core.wicket.markup.html.form;

import java.util.Collection;

import org.apache.wicket.model.IModel;

import com.google.common.base.Supplier;

import fr.openwide.core.wicket.markup.html.form.CheckGroup;

/**
 * A {@link CheckGroup} that allows the use of its constructor to instantiate anonymous classes, on contrary to {@link CheckGroup}
 * with its generic {@link CheckGroup#CheckGroup(String, IModel, Supplier)} constructor.
 * <p>This class is a technical workaround for what seems to be a bug (or at least a design flaw) in
 * the javac compilator, which doesn't allow instantiation of anonymous classes using a generic constructor in
 * a generic class.
 * 
 * @see CheckGroup#CheckGroup(String, IModel, Supplier)
 */
public abstract class InlineOverridableCheckGroup<T, C extends Collection<T>> extends CheckGroup<T> {

	private static final long serialVersionUID = -4650865960701373800L;

	public InlineOverridableCheckGroup(String id, IModel<C> model, Supplier<? extends C> collectionSupplier) {
		super(id, model, collectionSupplier);
	}

}
