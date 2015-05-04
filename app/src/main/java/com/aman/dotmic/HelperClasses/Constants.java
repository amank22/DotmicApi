package com.aman.dotmic.HelperClasses;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aman on 04-05-2015 in Dotmic.
 * A class containing all the constants of the class.
 */
public class Constants {
    public static final String BaseUrl = "http://api-v1-dotmic.in";
    private static String search, pmin, pmax;
    private static int start = 0;
    private static final int max_result = 10;
    private static final String ua = "UA-70652673918d6793fc5ec1547e132ea2";


    private static Map<String, String> createMap() {
        Map<String, String> result = new HashMap<>();
        if (search != null)
            result.put("search", search);
        result.put("start-index", String.valueOf(start));
        result.put("max-results", String.valueOf(max_result));
        if (pmin != null)
            result.put("price-min", pmin);
        if (pmax != null)
            result.put("price-max", pmax);
        result.put("ua", ua);
        return Collections.unmodifiableMap(result);
    }

    public static Map<String, String> getQUERY(String search, int start, String price_min, String price_max) {
        Constants.search = search;
        Constants.pmax = price_max;
        Constants.pmin = price_min;
        Constants.start = start;
        return createMap();
    }

}
