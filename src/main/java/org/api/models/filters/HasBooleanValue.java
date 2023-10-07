package org.api.models.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.utils.OperationUtils;

import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
@ToString
public class HasBooleanValue implements Operation {
    private String[] valuePath;
    private boolean operationValue;

    public HasBooleanValue(String valuePath, boolean operationValue) {
        this.valuePath = new String[]{valuePath};
        this.operationValue = operationValue;
    }

    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        Optional<Object> resourceValueOptional = OperationUtils.getNestedValue(resource, valuePath);
        if (!resourceValueOptional.isPresent()) throw new FilterException("Resource does not contain key: " + valuePath);
        Object resourceValue = resourceValueOptional.get();
        if (!(resourceValue instanceof Boolean)) throw new FilterException("Resource value must be a boolean: " + resourceValue);
        return resourceValue.equals(operationValue);
    }
}
