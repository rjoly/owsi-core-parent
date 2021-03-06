package fr.openwide.core.basicapp.web.application.common.util;

import java.io.Serializable;

import fr.openwide.core.commons.util.functional.Joiners;


public final class ResourceKeyGenerator implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final CharSequence base;
	
	public static ResourceKeyGenerator of(CharSequence base) {
		return new ResourceKeyGenerator(base);
	}

	private ResourceKeyGenerator(CharSequence base) {
		this.base = base;
	}
	
	protected String join(CharSequence ... operands) {
		return Joiners.onDot().skipNulls().join(operands);
	}

	public String resourceKey() {
		return base.toString();
	}

	public String resourceKey(CharSequence suffix) {
		return join(base, suffix);
	}
	
	public ResourceKeyGenerator append(CharSequence suffix) {
		return new ResourceKeyGenerator(join(base, suffix));
	}
	
	public ResourceKeyGenerator withPrefix(CharSequence prefix) {
		return new ResourceKeyGenerator(join(prefix, base));
	}
}
