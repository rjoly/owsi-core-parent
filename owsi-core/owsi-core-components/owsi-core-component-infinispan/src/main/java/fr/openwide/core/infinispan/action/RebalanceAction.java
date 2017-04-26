package fr.openwide.core.infinispan.action;

import org.infinispan.remoting.transport.Address;

import fr.openwide.core.infinispan.model.impl.SimpleAction;

public class RebalanceAction extends SimpleAction<Boolean> {

	public RebalanceAction(Address target) {
		super(target);
	}

	@Override
	public Boolean call() throws Exception {
		infinispanClusterService.doRebalanceRoles();
		return true;
	}

	public static final RebalanceAction rebalance(Address target) {
		return new RebalanceAction(target);
	}

}
