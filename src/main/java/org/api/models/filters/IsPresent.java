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
public class IsPresent implements Operation {
    private String propKey;

    @Override
    public boolean matches(Map<String, ?> resource) throws FilterException {
        return resource.containsKey(propKey);
    }
}
