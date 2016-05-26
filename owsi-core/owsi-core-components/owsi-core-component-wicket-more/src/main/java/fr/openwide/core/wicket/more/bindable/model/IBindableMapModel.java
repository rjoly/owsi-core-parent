package fr.openwide.core.wicket.more.bindable.model;

import java.util.Map;

import fr.openwide.core.wicket.more.markup.repeater.map.IItemModelAwareMapModel;
import fr.openwide.core.wicket.more.markup.repeater.map.IMapModel;

/**
 * A {@link IBindableModel} that also is a {@link IMapModel}.
 * 
 * @see IBindableModel#bindMapWithCache(org.bindgen.BindingRoot, com.google.common.base.Supplier, com.google.common.base.Function, com.google.common.base.Function)
 * @see IBindableModel#bindMapAlreadyAdded(org.bindgen.BindingRoot)
 */
public interface IBindableMapModel<K, V, M extends Map<K, V>>
		extends IBindableModel<M>, IItemModelAwareMapModel<K, V, M, IBindableModel<K>, IBindableModel<V>> {

}
