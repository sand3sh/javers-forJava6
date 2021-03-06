package org.javers.core.metamodel.property;

import org.javers.common.reflection.JaversMethod;
import org.javers.common.reflection.ReflectionUtil;
import org.javers.core.metamodel.annotation.AnnotationNamesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pawel szymczyk
 */
class BeanBasedPropertyScanner implements PropertyScanner {

    private final AnnotationNamesProvider annotationNamesProvider;

    public BeanBasedPropertyScanner(AnnotationNamesProvider annotationNamesProvider) {
        this.annotationNamesProvider = annotationNamesProvider;
    }

    @Override
    public List<Property> scan(Class<?> managedClass) {
        List<JaversMethod> getters = ReflectionUtil.findAllPersistentGetters(managedClass);
        List<Property> beanProperties = new ArrayList<Property>();

        for (JaversMethod getter : getters) {

            boolean hasTransientAnn = getter.hasAnyAnnotation(annotationNamesProvider.getTransientAliases());

            beanProperties.add(new Property(getter, hasTransientAnn));
        }
        return beanProperties;
    }
}
