package org.api.filters;

import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.models.filters.IsLowerThan;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IsLowerThanTests {

    @Test
    @DisplayName("Returns true when the resource numeric value is lower than the specified numeric value")
    public void returnsTrueWhenTheResourceNumericValueIsLowerThanTheSpecifiedNumericValue() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", 5.20);
        Map<String, Integer> resource = Map.of("propKey", 5);
        assertTrue(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the resource numeric value is not lower than the specified numeric value")
    public void returnsFalseWhenTheResourceNumericValueIsNotLowerThanTheSpecifiedNumericValue() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", 4);
        Map<String, Double> resource = Map.of("propKey", 5.20);
        assertFalse(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns true when the resource string value is shorter than the specified string value")
    public void returnsTrueWhenTheResourceStringValueIsShorterThanTheSpecifiedStringValue() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", "value");
        Map<String, String> resource = Map.of("propKey", "val");
        assertTrue(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the resource string value is not shorter than the specified string value")
    public void returnsFalseWhenTheResourceStringValueIsNotShorterThanTheSpecifiedStringValue() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", "value");
        Map<String, String> resource = Map.of("propKey", "value");
        assertFalse(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns true when the resource list value is shorter than the specified value")
    public void returnsTrueWhenTheResourceListValueIsShorterThanTheSpecifiedValue() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", 3);
        Map<String, ?> resource = Map.of("propKey", List.of(1, 2));
        assertTrue(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the resource list value is not shorter than the specified value")
    public void returnsFalseWhenTheResourceListValueIsNotShorterThanTheSpecifiedValue() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", 3);
        Map<String, ?> resource = Map.of("propKey", List.of(1, 2, 3));
        assertFalse(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns true when the resource list size is shorter than the specified set value size")
    public void returnsTrueWhenTheResourceListSizeIsShorterThanTheSpecifiedSetValueSize() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", Set.of(1, 2 ,3));
        Map<String, ?> resource = Map.of("propKey", List.of(1, 2));
        assertTrue(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns true when the resource map size is shorter than the specified size")
    public void returnsTrueWhenTheResourceMapSizeIsShorterThanTheSpecifiedSize() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", 3);
        Map<String, ?> resource = Map.of("propKey", Map.of("key1", "value1", "key2", "value2"));
        assertTrue(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the resource map size is not shorter than the specified size")
    public void returnsFalseWhenTheResourceMapSizeIsNotShorterThanTheSpecifiedSize() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", 3);
        Map<String, ?> resource = Map.of("propKey", Map.of("key1", "value1", "key2", "value2", "key3", "value3"));
        assertFalse(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns true when the resource map size is shorter than the specified map value size")
    public void returnsTrueWhenTheResourceMapSizeIsShorterThanTheSpecifiedMapValueSize() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", Map.of("key1", "value1", "key2", "value2"));
        Map<String, ?> resource = Map.of("propKey", Map.of("key1", "value1"));
        assertTrue(isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws a FilterException when the resource does not contain the property key")
    public void throwsAFilterExceptionWhenTheResourceDoesNotContainThePropertyKey() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", 5);
        Map<String, Integer> resource = Map.of("no-propKey", 5);
        assertThrows(FilterException.class, () -> isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws a FilterException when the resource values are not comparable")
    public void throwsAFilterExceptionWhenTheResourceValueIsNotANumberOrAString() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", 5);
        Map<String, Boolean> resource = Map.of("propKey", true);
        assertThrows(FilterException.class, () -> isLowerThanOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws a FilterException when the operation values are not comparable")
    public void throwsAFilterExceptionWhenTheResourceValueIsNotANumberOrAString2() throws FilterException {
        Operation isLowerThanOperation = new IsLowerThan("propKey", true);
        Map<String, String> resource = Map.of("propKey", "value");
        assertThrows(FilterException.class, () -> isLowerThanOperation.matches(resource));
    }

}
