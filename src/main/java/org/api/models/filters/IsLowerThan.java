package org.api.models.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.utils.OperationUtils;

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
        if (resourceValue instanceof Iterable && operationValue instanceof Number){
            return  ((Iterable) resourceValue).spliterator().getExactSizeIfKnown() < ((Number) operationValue).longValue();
        }
        if (resourceValue instanceof Map && operationValue instanceof Number){
            return  ((Map) resourceValue).size() < ((Number) operationValue).longValue();
        }
        if (resourceValue instanceof Iterable && operationValue instanceof Iterable){
            return ((Iterable) resourceValue).spliterator().getExactSizeIfKnown() < ((Iterable) operationValue).spliterator().getExactSizeIfKnown();
        }
        if (resourceValue instanceof Map && operationValue instanceof Map){
            return ((Map) resourceValue).size() < ((Map) operationValue).size();
        }
        if (!OperationUtils.valuesAreComparable(resourceValue, operationValue)) {
            throw new FilterException("Operation value and resource value must be both a compatible comparable object: operationValue " + resourceValue + ", resourceValue " + resourceValue);
        }
        return ((Comparable) resourceValue).compareTo(operationValue) < 0;
    }
}
