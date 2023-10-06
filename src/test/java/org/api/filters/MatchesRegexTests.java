package org.api.filters;

import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.models.filters.MatchesRegex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatchesRegexTests {

    @Test
    @DisplayName("Returns true when the regex matches the string")
    public void returnsTrueWhenTheRegexMatchesTheString() throws FilterException {
        Operation regexOperation = new MatchesRegex("propKey", ".*");
        Map<String, String> resource = Map.of("propKey", "value");
        assertTrue(regexOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the regex does not match the string")
    public void returnsFalseWhenTheRegexDoesNotMatchTheString() throws FilterException {
        Operation regexOperation = new MatchesRegex("propKey", "no-match");
        Map<String, String> resource = Map.of("propKey", "value");
        assertFalse(regexOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws an FilterException when the resource does not contain the property key")
    public void throwsAnOperationExceptionWhenTheResourceDoesNotContainThePropertyKey() throws FilterException {
        Operation regexOperation = new MatchesRegex("propKey", ".*");
        Map<String, String> resource = Map.of("no-propKey", "value");
        assertThrows(FilterException.class, () -> regexOperation.matches(resource));
    }

    @Test
    @DisplayName("Throws an FilterException when the resource value is not a string")
    public void throwsAnOperationExceptionWhenTheResourceValueIsNotAString() throws FilterException {
        Operation regexOperation = new MatchesRegex("propKey", ".*");
        Map<String, Integer> resource = Map.of("propKey", 1);
        assertThrows(FilterException.class, () -> regexOperation.matches(resource));
    }
}
