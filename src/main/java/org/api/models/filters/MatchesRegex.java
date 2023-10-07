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
public class MatchesRegex implements Operation {
    private String[] valuePath;
    private String operationValue;

    public MatchesRegex(String valuePath, String operationValue) {
        this.valuePath = new String[]{valuePath};
        this.operationValue = operationValue;
    }

    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        Optional<Object> resourceValueOptional = OperationUtils.getNestedValue(resource, valuePath);
        if (!resourceValueOptional.isPresent()) throw new FilterException("Resource does not contain key: " + valuePath);
        Object resourceValue = resourceValueOptional.get();
        if (!(resourceValue instanceof String)) throw new FilterException("Resource value must be a string: " + resourceValue);
        return ((String) resourceValue).matches(operationValue);
    }
}
