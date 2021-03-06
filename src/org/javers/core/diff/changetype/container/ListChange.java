package org.javers.core.diff.changetype.container;

import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.property.Property;

import java.util.List;

/**
 * @author pawel szymczyk
 */
public class ListChange extends CollectionChange {

    public ListChange(GlobalId affectedCdoId, Property property, List<ContainerElementChange> changes) {
        super(affectedCdoId, property, changes);
    }
}
