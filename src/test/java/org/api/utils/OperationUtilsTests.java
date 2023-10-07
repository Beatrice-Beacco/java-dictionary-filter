package org.api.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;

public class OperationUtilsTests {

    @Nested
    @DisplayName("Get nested value tests")
    class GetNestedValueTests {

        Map<String, ?> mockResource = Map.of(
                "name", "John",
                "surname", "Doe",
                "age", 30,
                "address", Map.of(
                        "street", "Test street",
                        "number", Map.of(
                                "streetNumber", 10,
                                "apartmentComplex", "A"
                            ),
                        "city", "New York",
                        "country", "USA"
                )
        );

        @Test
        @DisplayName("Should return nested first level value")
        public void shouldReturnNestedFirstLevelValue() {
            String[] path = {"name"};
            Optional<Object> nestedValue = OperationUtils.getNestedValue(mockResource, path);
            assertEquals(Optional.of("John"), nestedValue);
        }

        @Test
        @DisplayName("Should return nested second level value")
        public void shouldReturnNestedSecondLevelValue() {
            String[] path = {"address", "street"};
            Optional<Object> nestedValue = OperationUtils.getNestedValue(mockResource, path);
            assertEquals(Optional.of("Test street"), nestedValue);
        }

        @Test
        @DisplayName("Should return nested third level value")
        public void shouldReturnNestedThirdLevelValue() {
            String[] path = {"address", "number", "streetNumber"};
            Optional<Object> nestedValue = OperationUtils.getNestedValue(mockResource, path);
            assertEquals(Optional.of(10), nestedValue);
        }

        @Test
        @DisplayName("Should return empty optional when path doesn't exist")
        public void shouldReturnEmptyOptionalWhenPathNotExixts() {
            String[] path = {"address", "incorrect"};
            Optional<Object> nestedValue = OperationUtils.getNestedValue(mockResource, path);
            assertEquals(Optional.empty(), nestedValue);
        }

        @Test
        @DisplayName("Should return empty optional when single level path is incorrect")
        public void shouldReturnEmptyOptionalWhenSingleLevelPathIsIncorrect() {
            String[] path = {"incorrect"};
            Optional<Object> nestedValue = OperationUtils.getNestedValue(mockResource, path);
            assertEquals(Optional.empty(), nestedValue);
        }

        @Test
        @DisplayName("Should return empty optional when first level path is incorrect")
        public void shouldReturnEmptyOptionalWhenFirstLevelPathIsIncorrect() {
            String[] path = {"incorrect", "city"};
            Optional<Object> nestedValue = OperationUtils.getNestedValue(mockResource, path);
            assertEquals(Optional.empty(), nestedValue);
        }

        @Test
        @DisplayName("Should return empty optional when last level path is incorrect")
        public void shouldReturnEmptyOptionalWhenLastLevelPathIsIncorrect() {
            String[] path = {"address", "number", "incorrect"};
            Optional<Object> nestedValue = OperationUtils.getNestedValue(mockResource, path);
            assertEquals(Optional.empty(), nestedValue);
        }
    }
}
