package org.api.utils;

public class OperationUtils {

    public static boolean valuesAreComparable(Object firstValue, Object secondValue) {
        if (!(firstValue instanceof Comparable<?>) && !(secondValue instanceof Comparable<?>)) return false;
        Class<?> firstValueClass = firstValue.getClass();
        Class<?> secondValueClass = secondValue.getClass();
        return firstValueClass.isAssignableFrom(secondValueClass) || secondValueClass.isAssignableFrom(firstValueClass);
    }
}
