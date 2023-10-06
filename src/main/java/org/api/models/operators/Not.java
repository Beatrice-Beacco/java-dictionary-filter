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
public class Not implements Operation {
    private Operation operation;

    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        return !operation.matches(resource);
    }
}
