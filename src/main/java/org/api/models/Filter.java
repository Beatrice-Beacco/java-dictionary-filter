package org.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;

import java.util.Map;

@Getter
@ToString
public class Filter implements Operation {
    private Operation[] filters;

    public Filter(Operation... filters) {
        this.filters = filters;
    }
    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        for (Operation operation : filters) {
            if (!operation.matches(resource)) return false;
        }
        return true;
    }
}
