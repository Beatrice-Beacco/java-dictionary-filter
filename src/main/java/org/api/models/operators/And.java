package org.api.models.operators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;

import java.util.Map;

@Getter
@ToString
public class And implements Operation {
    private Operation[] operations;

    public And(Operation... operations) {
        this.operations = operations;
    }

    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        for (Operation operation : operations) {
            if (!operation.matches(resource)) return false;
        }
        return true;
    }
}
