package org.javers.core.json.typeadapter.change;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.javers.core.commit.CommitMetadata;
import org.javers.core.diff.changetype.ValueChange;

class ValueChangeTypeAdapter extends ChangeTypeAdapter<ValueChange> {
    private static final String LEFT_VALUE_FIELD = "left";
    private static final String RIGHT_VALUE_FIELD = "right";

    @Override
    public ValueChange fromJson(JsonElement json, JsonDeserializationContext context) {
        JsonObject jsonObject = (JsonObject) json;
        PropertyChangeStub stub = deserializeStub(jsonObject, context);

        Object leftValue  = context.deserialize(jsonObject.get(LEFT_VALUE_FIELD),  stub.property.getType());
        Object rightValue = context.deserialize(jsonObject.get(RIGHT_VALUE_FIELD), stub.property.getType());

        return appendCommitMetadata(jsonObject, context, new ValueChange(stub.id, stub.property, leftValue, rightValue));
    }

    @Override
    public JsonElement toJson(ValueChange change, JsonSerializationContext context) {
        JsonObject jsonObject = createJsonObject(change, context);

        jsonObject.add(LEFT_VALUE_FIELD, context.serialize(change.getLeft()));
        jsonObject.add(RIGHT_VALUE_FIELD, context.serialize(change.getRight()));

        return jsonObject;
    }

    @Override
    public Class getValueType() {
        return ValueChange.class;
    }
}
