package org.api.filters;

import org.api.exceptions.FilterException;
import org.api.interfaces.Operation;
import org.api.models.filters.IsPresent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class IsPresentTests {
    @Test
    @DisplayName("Returns true when the resource has the specified property key")
    public void returnsTrueWhenTheResourceHasTheSpecifiedPropertyKey() throws FilterException {
        Operation isPresentOperation = new IsPresent("propKey");
        Map<String, Boolean> resource = Map.of("propKey", true);
        assertTrue(isPresentOperation.matches(resource));
    }

    @Test
    @DisplayName("Returns false when the resource does not have the specified property key")
    public void returnsFalseWhenTheResourceDoesNotHaveTheSpecifiedPropertyKey() throws FilterException {
        Operation isPresentOperation = new IsPresent("propKey");
        Map<String, Boolean> resource = Map.of("no-propKey", true);
        assertFalse(isPresentOperation.matches(resource));
    }
}
