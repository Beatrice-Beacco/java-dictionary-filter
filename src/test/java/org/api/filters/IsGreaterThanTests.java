package org.api.filters;

import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.models.filters.IsGreaterThan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsGreaterThanTests {

    @Test
    @DisplayName("Returns true when the resource numeric value is greater than the specified numeric value")
    public void returnsTrueWhenTheResourceNumericValueIsGreaterThanTheSpecifiedNumericValue() throws FilterException {
        Operation isGreaterThanOperation = new IsGreaterThan("propKey", 5);
        Map<String, Double> resource = Map.of("propKey", 5.20);
        assertTrue(isGreaterThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the resource numeric value is not greater than the specified numeric value")
    public void returnsFalseWhenTheResourceNumericValueIsNotGreaterThanTheSpecifiedNumericValue() throws FilterException {
        Operation isGreaterThanOperation = new IsGreaterThan("propKey", 5.20);
        Map<String, Integer> resource = Map.of("propKey", 4);
        assertFalse(isGreaterThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns true when the resource string value is longer than the specified string value")
    public void returnsTrueWhenTheResourceStringValueIsShorterThanTheSpecifiedStringValue() throws FilterException {
        Operation isGreaterThanOperation = new IsGreaterThan("propKey", "val");
        Map<String, String> resource = Map.of("propKey", "value");
        assertTrue(isGreaterThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the resource string value is not longer than the specified string value")
    public void returnsFalseWhenTheResourceStringValueIsNotShorterThanTheSpecifiedStringValue() throws FilterException {
        Operation isGreaterThanOperation = new IsGreaterThan("propKey", "value");
        Map<String, String> resource = Map.of("propKey", "value");
        assertFalse(isGreaterThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws a FilterException when the resource does not contain the property key")
    public void throwsAFilterExceptionWhenTheResourceDoesNotContainThePropertyKey() throws FilterException {
        Operation isGreaterThanOperation = new IsGreaterThan("propKey", 5);
        Map<String, Integer> resource = Map.of("no-propKey", 5);
        assertThrows(FilterException.class, () -> isGreaterThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws a FilterException when the resource value is not a number or a string")
    public void throwsAFilterExceptionWhenTheResourceValueIsNotANumberOrAString() throws FilterException {
        Operation isGreaterThanOperation = new IsGreaterThan("propKey", 5);
        Map<String, Boolean> resource = Map.of("propKey", true);
        assertThrows(FilterException.class, () -> isGreaterThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws a FilterException when the operation value is not a number or a string")
    public void throwsAFilterExceptionWhenTheResourceValueIsNotANumberOrAString2() throws FilterException {
        Operation isGreaterThanOperation = new IsGreaterThan("propKey", true);
        Map<String, String> resource = Map.of("propKey", "value");
        assertThrows(FilterException.class, () -> isGreaterThanOperation.matches(resource));
    }

}
