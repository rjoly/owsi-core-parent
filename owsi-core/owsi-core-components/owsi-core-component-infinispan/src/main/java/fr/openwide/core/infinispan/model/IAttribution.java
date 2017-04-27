package fr.openwide.core.infinispan.model;

import java.io.Serializable;
import java.util.Date;

import org.jgroups.Address;

public interface IAttribution extends Serializable {

	Address getOwner();
	Date getAttributionDate();
	boolean match(Address address);
	boolean match(IAttribution attribution);

}
