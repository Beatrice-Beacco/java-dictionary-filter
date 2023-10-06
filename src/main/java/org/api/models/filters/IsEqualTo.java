package org.api.models.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;

import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
public class IsEqualTo implements Operation {
    private String propKey;
    private Object operationValue;
    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        if (!resource.containsKey(propKey)) throw new FilterException("Resource does not contain key: " + propKey);
        Object resourceValue = resource.get(propKey);
        return resourceValue.equals(operationValue);
    }
}
