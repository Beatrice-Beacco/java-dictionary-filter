package org.api.models.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.utils.OperationUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
@ToString
public class IsGreaterThan implements Operation {
    private String[] valuePath;
    private Object operationValue;

    public IsGreaterThan(String valuePath, Object operationValue) {
        this.valuePath = new String[]{valuePath};
        this.operationValue = operationValue;
    }

    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        Optional<Object> resourceValueOptional = OperationUtils.getNestedValue(resource, valuePath);
        if (!resourceValueOptional.isPresent()) throw new FilterException("Resource does not contain key: " + valuePath);
        Object resourceValue = resourceValueOptional.get();
        if (resourceValue instanceof String && operationValue instanceof String){
            return ((String) resourceValue).length() > ((String) operationValue).length();
        }
        if (resourceValue instanceof Number && operationValue instanceof Number) {
            return ((Number) resourceValue).doubleValue() > ((Number) operationValue).doubleValue();
        }
        if (resourceValue instanceof Collection && operationValue instanceof Number){
            return  ((Collection) resourceValue).size() > ((Number) operationValue).intValue();
        }
        if (resourceValue instanceof Map && operationValue instanceof Number){
            return  ((Map) resourceValue).size() > ((Number) operationValue).longValue();
        }
        if (resourceValue instanceof Collection && operationValue instanceof Collection){
            return ((Collection) resourceValue).size() > ((Collection) operationValue).size();
        }
        if (resourceValue instanceof Map && operationValue instanceof Map){
            return ((Map) resourceValue).size() > ((Map) operationValue).size();
        }
        throw new FilterException("Operation value and resource value must be both a compatible comparable object: operationValue " + resourceValue + ", resourceValue " + resourceValue);
    }
}
