package org.api.interfaces;

import org.api.exceptions.FilterException;

import java.util.Map;

public interface Operation {

    boolean matches(Map<String, ?> resource) throws FilterException;
}
