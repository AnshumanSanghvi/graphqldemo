package com.anshuman.graphqldemo.util;

import java.util.Optional;
import java.util.function.Predicate;

public class StringUtil {

    private StringUtil() {
        // use statically
    }

    public static String truncate(Object object, int maxLength) {
        return Optional.ofNullable(object)
                .map(Object::toString)
                .filter(Predicate.not(String::isBlank))
                .map(str -> str.substring(0, Math.min(str.length() - 1, maxLength)))
                .map(str -> str.length() >= (maxLength - 1) ? str + "..." : str)
                .orElse("");
    }
}
