package org.javers.core.metamodel.type;

import org.javers.common.exception.JaversException;

import java.lang.reflect.Type;

import static org.javers.common.exception.JaversExceptionCode.GENERIC_TYPE_NOT_PARAMETRIZED;
import static org.javers.common.reflection.ReflectionUtil.extractClass;

/**
 * Collection or Array
 *
 * @author bartosz walacik
 */
public abstract class ContainerType extends EnumerableType {

    ContainerType(Type baseJavaType) {
        super(baseJavaType);
    }

    /**
     * never returns null
     *
     * @throws JaversException GENERIC_TYPE_NOT_PARAMETRIZED
     */
    public Type getItemType(){
        if (isFullyParametrized()) {
            return getActualTypeArguments().get(0);
        }
        throw new JaversException(GENERIC_TYPE_NOT_PARAMETRIZED, getBaseJavaType().toString());
    }

    /**
     * never returns null
     *
     * @throws JaversException GENERIC_TYPE_NOT_PARAMETRIZED
     */
    public Class getItemClass(){
        return extractClass(getItemType());
    }
}
