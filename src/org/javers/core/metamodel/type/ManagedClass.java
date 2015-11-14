package org.javers.core.metamodel.type;

import static org.javers.common.exception.JaversExceptionCode.PROPERTY_NOT_FOUND;
import static org.javers.common.validation.Validate.argumentsAreNotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.javers.common.collections.Predicate;
import org.javers.common.exception.JaversException;
import org.javers.common.validation.Validate;
import org.javers.core.metamodel.property.Property;

/**
 * Decomposes a class into list of properties.
 * 
 * @author bartosz walacik
 */
class ManagedClass {
    private final Class<?> baseJavaClass;
    private final Map<String, Property> propertiesByName;
    private final List<Property> managedProperties;
    private final List<Property> transientAnnProperties;

    ManagedClass(Class baseJavaClass, List<Property> allProperties) {
        argumentsAreNotNull(allProperties);

        this.baseJavaClass = baseJavaClass;
        this.managedProperties = new ArrayList<Property>();
        this.transientAnnProperties = new ArrayList<Property>();
        this.propertiesByName = new HashMap<String, Property>();

        for (Property property : allProperties) {
            if (property.isHasTransientAnn()){
                this.transientAnnProperties.add(property);
            }else {
                this.managedProperties.add(property);
            }

            propertiesByName.put(property.getName(),property);
        }
    }

    /**
     * returns all managed properties
     */
    List<Property> getProperties() {
        return Collections.unmodifiableList(managedProperties);
    }

    /**
     * returns managed properties subset
     */
    List<Property> getProperties(Predicate<Property> query) {
        List<Property> retProperties = new ArrayList<Property>();

        for (Property property : managedProperties) {
            if (query.apply(property)){
                retProperties.add(property);
            }
        }

        return retProperties;
    }

    /**
     * finds property by name (managed or withTransientAnn)
     *
     * @throws JaversException PROPERTY_NOT_FOUND
     */
    Property getProperty(String withName) {
        Validate.argumentIsNotNull(withName);
        if (!propertiesByName.containsKey(withName)){
            throw new JaversException(PROPERTY_NOT_FOUND, withName, baseJavaClass.getName());
        }
        return propertiesByName.get(withName);
    }

    Class<?> getBaseJavaClass() {
        return baseJavaClass;
    }
}
