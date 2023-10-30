package utils;

public class StringConstant {
    //    API
    public static final String TOKEN_NAME = "Token";
    public static final String CONTENT_TYPE = "content-type";
    public static final String JSON = "application/json";

    //    Unit
    public static final String SUCCESSFUL_REGISTRATION_MESSAGE = "User has been registered successfully";
    public static final String INVALID_MAIL_FORMAT_MESSAGE = "Please provide valid email (example: Tonny@gmail.com)";
    public static final String INVALID_LOGIN_FORMAT_MESSAGE = "Login should have minimum 6 characters, maximum 12 " +
            "characters, at least one uppercase letter, one lowercase letter";
    public static final String INVALID_PASSWORD_FORMAT_MESSAGE = "Password should have minimum 8 characters, " +
            "maximum 12 characters, at least one uppercase letter, one lowercase letter and one number";
    public static final String CONFIRM_PASSWORD_DOES_NOT_MATCH_MESSAGE = "Passwords do not match. Please, try again";

    private StringConstant() {
    }
}
