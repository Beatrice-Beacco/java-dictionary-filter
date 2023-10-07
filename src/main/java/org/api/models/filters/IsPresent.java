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
public class IsPresent implements Operation {
    private String[] valuePath;

    public IsPresent(String valuePath) {
        this.valuePath = new String[]{valuePath};
    }

    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        Optional<Object> resourceValueOptional = OperationUtils.getNestedValue(resource, valuePath);
        return resourceValueOptional.isPresent();
    }
}
