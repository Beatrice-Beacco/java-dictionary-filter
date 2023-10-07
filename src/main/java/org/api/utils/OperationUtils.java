package org.api.utils;

import java.util.Map;
import java.util.Optional;

public class OperationUtils {

    public static Optional<Object> getNestedValue(Map<String, ?> resource, String[] path) {
        Map<String, ?> nestedMap = resource;
        int lastPathIndex = path.length - 1;

        for (int i = 0; i < lastPathIndex; i++) {
           Object pathValue = nestedMap.get(path[i]);
           if (pathValue == null) return Optional.empty();
           //If we have not reached the last path index and the path value is not a map, then we cannot continue and the path is invalid
           if (!(pathValue instanceof Map<?, ?>)) return Optional.empty();
            nestedMap = (Map<String, ?>) pathValue;
        }

        Object value = nestedMap.get(path[lastPathIndex]);
        return value == null ? Optional.empty() : Optional.of(value);
    }
}
