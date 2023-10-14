package utils;

import com.github.javafaker.Faker;

public class TestDataGenerator {
    private static final Faker faker = new Faker();

    public static String generateRandomString(int min, int max) {
        return faker.regexify(String.format("[A-Z0-9]{%s,%d}", min, max));
    }

    public static String generateRandomNumericString(int min, int max) {
        return faker.regexify(String.format("[0-9]{%s,%d}", min, max));
    }

    public static String generateRandomAlphabeticString(int min, int max) {
        return faker.regexify(String.format("[A-Z]{%s,%d}", min, max));
    }
}
