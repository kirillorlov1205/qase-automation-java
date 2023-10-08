package model;

public class Constants {

    public static final String INVALID_CREDENTIALS_VALIDATION_MESSAGE = "These credentials do not match our records.";
    public static final String INVALID_EMAIL_FORMAT_VALIDATION_MESSAGE = "Value '%s' does not match format email of " +
            "type string";
    public static final String SUCCESSFUL_PASSWORD_RESET_VALIDATION_MESSAGE = "We have e-mailed your password reset " +
            "link!";
    public static final String USER_NOT_FOUND_VALIDATION_MESSAGE = "User not found.";
    public static final User USER_WITH_VALID_CREDENTIALS = new User("test12051@mail.ru", "TestingPass1!");
    public static final User USER_WITH_WRONG_EMAIL = new User("wrongEmail@gmail.com", "TestingPass1!");
    public static final User USER_WITH_WRONG_PASSWORD = new User("test12051@mail.ru", "WrongTestingPass1!");
    public static final User USER_WITH_EMPTY_EMAIL = new User("", "TestingPass1!");
    public static final User USER_WITH_EMPTY_PASSWORD = new User("test12051@mail.ru", "");
}
