package org.api.filters;

import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.models.filters.HasBooleanValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HasBooleanValueTests {
    @Test
    @DisplayName("Returns true when the resource has the specified boolean value")
    public void returnsTrueWhenTheResourceHasTheSpecifiedBooleanValue() throws FilterException {
        Operation booleanOperation = new HasBooleanValue("propKey", true);
        Map<String, Boolean> resource = Map.of("propKey", true);
        assertTrue(booleanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the resource does not have the specified boolean value")
    public void returnsFalseWhenTheResourceDoesNotHaveTheSpecifiedBooleanValue() throws FilterException {
        Operation booleanOperation = new HasBooleanValue("propKey", true);
        Map<String, Boolean> resource = Map.of("propKey", false);
        assertFalse(booleanOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws an FilterException when the resource does not contain the property key")
    public void throwsAnOperationExceptionWhenTheResourceDoesNotContainThePropertyKey() throws FilterException {
        Operation booleanOperation = new HasBooleanValue("propKey", true);
        Map<String, Boolean> resource = Map.of("no-propKey", true);
        assertThrows(FilterException.class, () -> booleanOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws an FilterException when the resource value is not a boolean")
    public void throwsAnOperationExceptionWhenTheResourceValueIsNotABoolean() throws FilterException {
        Operation booleanOperation = new HasBooleanValue("propKey", true);
        Map<String, Integer> resource = Map.of("propKey", 1);
        assertThrows(FilterException.class, () -> booleanOperation.matches(resource));
    }
}
