package org.javers.core.metamodel.annotation;

import org.javers.common.collections.Sets;

import java.util.Collections;
import java.util.Set;

/**
 * @author bartosz walacik
 */
class JPAAnnotationsNameSpace implements AnnotationsNameSpace {
    @Override
    public Set<String> getEntityAliases() {
        return Sets.asSet("Entity", "MappedSuperclass");
    }

    @Override
    public Set<String> getValueObjectAliases() {
        return Sets.asSet("Embeddable");
    }

    @Override
    public Set<String> getValueAliases() {
        return Sets.asSet("Value");
    }

    @Override
    public Set<String> getTransientPropertyAliases() {
        return Sets.asSet("Transient");
    }

    @Override
    public Set<String> getTypeNameAliases() {
        return Collections.emptySet();
    }
}
