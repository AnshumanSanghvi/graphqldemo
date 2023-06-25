package com.anshuman.graphqldemo.data.enums;

import lombok.Getter;

public enum MPAA_Rating {
    G("G"), NC_17("NC-17"), PG("PG"), PG_13("PG-13"), R("R");

    static final MPAA_Rating[] allValues = MPAA_Rating.values();
    @Getter
    final String value;

    MPAA_Rating(String value) {
        this.value = value;
    }

    public static MPAA_Rating fromValue(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Unrecognized rating.");

        for (MPAA_Rating rating : allValues) {
            if (rating.getValue().equalsIgnoreCase(value))
                return rating;
        }

        throw new IllegalArgumentException("Unrecognized rating: " + value);
    }
}
