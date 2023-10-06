package org.api.filters;

import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.models.filters.IsEqualTo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsEqualToTests {
    @Test
    @DisplayName("Returns true when the resource value is equal to the specified value")
    public void returnsTrueWhenTheResourceValueIsEqualToTheSpecifiedValue() throws FilterException {
        Operation isEqualToOperation = new IsEqualTo("propKey", "value");
        Map<String, String> resource = Map.of("propKey", "value");
        assertTrue(isEqualToOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the resource value is not equal to the specified value")
    public void returnsFalseWhenTheResourceValueIsNotEqualToTheSpecifiedValue() throws FilterException {
        Operation isEqualToOperation = new IsEqualTo("propKey", "value");
        Map<String, String> resource = Map.of("propKey", "not-value");
        assertFalse(isEqualToOperation.matches(resource));
    }
    @Test
    @DisplayName("Returns false when the resource value type is different from the specified value type")
    public void returnsFalseWhenTheResourceValueTypeIsDifferentFromTheSpecifiedValueType() throws FilterException {
        Operation isEqualToOperation = new IsEqualTo("propKey", "value");
        Map<String, Integer> resource = Map.of("propKey", 1);
        assertFalse(isEqualToOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws an FilterException when the resource does not contain the property key")
    public void throwsAnOperationExceptionWhenTheResourceDoesNotContainThePropertyKey() throws FilterException {
        Operation isEqualToOperation = new IsEqualTo("propKey", "value");
        Map<String, String> resource = Map.of("no-propKey", "value");
        assertThrows(FilterException.class, () -> isEqualToOperation.matches(resource));
    }
}
