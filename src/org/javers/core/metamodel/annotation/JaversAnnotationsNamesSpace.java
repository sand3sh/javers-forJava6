package org.javers.core.metamodel.annotation;

import org.javers.common.collections.Sets;

import java.util.Set;

/**
 * @author bartosz walacik
 */
class JaversAnnotationsNamesSpace implements AnnotationsNameSpace {

    @Override
    public Set<String> getEntityAliases() {
        return Sets.asSet(Entity.class.getSimpleName());
    }

    @Override
    public Set<String> getValueObjectAliases() {
        return Sets.asSet(ValueObject.class.getSimpleName());
    }

    @Override
    public Set<String> getValueAliases() {
        return Sets.asSet(Value.class.getSimpleName());
    }

    @Override
    public Set<String> getTransientPropertyAliases() {
        return Sets.asSet(DiffIgnore.class.getSimpleName());
    }

    @Override
    public Set<String> getTypeNameAliases() {
        return Sets.asSet(TypeName.class.getSimpleName());
    }
}
