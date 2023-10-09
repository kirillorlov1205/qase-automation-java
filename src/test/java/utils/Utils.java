package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Utils {

    public static String getRandomString(int limit) {
        return RandomStringUtils.randomAlphabetic(limit);
    }
}
