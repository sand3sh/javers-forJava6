package org.javers.core.metamodel.annotation;

import org.javers.repository.api.JaversRepository;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Use TypeName annotation to give a distinctive type name for your Entities and ValueObjects.
 * This name will be used <b>everywhere</b> by JaVers, instead of fully-qualified class name.
 * <br/><br/>
 *
 * TypeName is <b>recommended</b> if you are
 * using {@link JaversRepository},
 * because it gives you freedom of refactoring your package and class names.
 * <br/><br/>
 *
 * TypeName is an equivalent for org.springframework.data.annotation.TypeAlias
 * from Spring Data.
 *
 * @author bartosz.walacik
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface TypeName {
    /**
     * The type name to be used when comparing and persisting
     *
     * @return
     */
    String value();
}
