package org.api.models.operators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;

import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
public class And implements Operation {
    private Operation firstOperation;
    private Operation secondOperation;

    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        return firstOperation.matches(resource) && secondOperation.matches(resource);
    }
}
