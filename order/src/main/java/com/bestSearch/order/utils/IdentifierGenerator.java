package com.bestSearch.order.utils;

import static com.bestSearch.order.utils.OrderConstants.PATTERN_PREFIX;
import static com.bestSearch.order.utils.OrderConstants.PATTERN_SUFFIX;

import org.apache.commons.text.StringSubstitutor;
import org.apache.commons.text.TextStringBuilder;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class IdentifierGenerator {

    /**
     * return identifier for the pattern with args maps
     *
     * @param properties the map of key value pairs which use for replacing in
     *                   pattern
     * @param pattern    the string pattern of %{<key>} which needs to substitute
     *                   with values
     * @return the String value for according to pattern
     */
    public static String generateIdentifier(Map<String, Object> properties, String pattern) {
        TextStringBuilder strBuilder = new TextStringBuilder(pattern);
        StringSubstitutor sub = new StringSubstitutor(properties, PATTERN_PREFIX, PATTERN_SUFFIX);
        return sub.replace(strBuilder);
    }

}
