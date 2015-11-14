package org.javers.core.metamodel.type;

import org.javers.common.collections.Optional;
import org.javers.common.collections.Predicate;
import org.javers.common.string.PrettyPrintBuilder;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.core.metamodel.property.Property;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author bartosz walacik
 */
public abstract class ManagedType extends JaversType {
    private final ManagedClass managedClass;

    ManagedType(ManagedClass managedClass) {
        this(managedClass, Optional.<String>empty());
    }

    ManagedType(ManagedClass managedClass, Optional<String> typeName) {
        super(managedClass.getBaseJavaClass(), typeName);
        this.managedClass = managedClass;
    }

    abstract ManagedType spawn(ManagedClass managedClass);

    @Override
    protected Type getRawDehydratedType() {
        return GlobalId.class;
    }

    @Override
    protected PrettyPrintBuilder prettyPrintBuilder() {
        return super.prettyPrintBuilder().addMultiField("managedProperties", managedClass.getProperties());
    }

    public Property getProperty(String propertyName) {
        return managedClass.getProperty(propertyName);
    }

    public List<Property> getProperties(Predicate<Property> query) {
        return managedClass.getProperties(query);
    }

    public List<Property> getProperties() {
        return managedClass.getProperties();
    }
}
