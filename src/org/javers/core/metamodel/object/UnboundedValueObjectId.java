package org.javers.core.metamodel.object;

import org.javers.core.metamodel.type.ValueObjectType;

import static org.javers.common.validation.Validate.argumentIsNotNull;

/**
 * Unbounded ValueObject, has '/' as symbolic cdoId representation.
 * <p/>
 * This kind of Id is assigned by graph builder to ValueObject which is not embedded in any Entity instance.
 * (by design or by accident)
 * <p/>
 *
 * Its recommended to avoid Unbounded ValueObject since they don't have a real global id.
 * Prefer embedding ValueObject in Entity instances to leverage {@link ValueObjectId} global Id.
 *
 *
 * @author bartosz walacik
 */
public class UnboundedValueObjectId extends GlobalId {
    public static final String UNBOUNDED_FRAGMENT = "/";

    private transient final ValueObjectType valueObject;

    public UnboundedValueObjectId(ValueObjectType valueObject) {
        argumentIsNotNull(valueObject);
        this.valueObject = valueObject;
    }

    @Override
    public ValueObjectType getManagedType() {
        return valueObject;
    }

    @Override
    public String value() {
        return valueObject.getName()+UNBOUNDED_FRAGMENT;
    }

    public String getFragment() {
        return UNBOUNDED_FRAGMENT;
    }

    @Override
    public Object getCdoId() {
        return null;
    }
}
