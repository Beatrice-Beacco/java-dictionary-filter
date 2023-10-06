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
public class IsLowerThan implements Operation {
    private String propKey;
    private Object operationValue;
    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        if (!resource.containsKey(propKey)) throw new FilterException("Resource does not contain key: " + propKey);
        Object resourceValue = resource.get(propKey);
        if (resourceValue instanceof Number && operationValue instanceof Number) {
            return ((Number) resourceValue).doubleValue() < ((Number) operationValue).doubleValue();
        }
        if (resourceValue instanceof String && operationValue instanceof String) {
            return ((String) resourceValue).length() < ((String) operationValue).length();
        }
        //TODO: compare length of lists, sets and maps
        throw new FilterException("Operation value and resource value must be both either a number or string: operationValue " + resourceValue + ", resourceValue " + resourceValue);
    }
}
