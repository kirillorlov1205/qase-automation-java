package registrationForm;

public class RegistrationForm {
    private static final String SUCCESSFUL_REGISTRATION_MESSAGE = "User has been registered successfully";
    private static final String INVALID_MAIL_FORMAT_MESSAGE = "Please provide valid email (example: Tonny@gmail.com)";
    private static final String INVALID_LOGIN_FORMAT_MESSAGE = "Login should have minimum 6 characters, maximum 12 " +
            "characters, at least one uppercase letter, one lowercase letter";
    private static final String INVALID_PASSWORD_FORMAT_MESSAGE = "Password should have minimum 8 characters, " +
            "maximum 12 characters, at least one uppercase letter, one lowercase letter and one number";
    private static final String CONFIRM_PASSWORD_DOES_NOT_MATCH_MESSAGE = "Passwords do not match. Please, try again";

    public String register(User user) {
        if (!emailValidation(user.getEmail())) {
            return INVALID_MAIL_FORMAT_MESSAGE;
        } else if (!loginValidation(user.getLogin())) {
            return INVALID_LOGIN_FORMAT_MESSAGE;
        } else if (!passwordValidation(user.getPassword())) {
            return INVALID_PASSWORD_FORMAT_MESSAGE;
        } else if (!confirmPasswordValidation(user.getPassword(), user.getConfirmPassword())) {
            return CONFIRM_PASSWORD_DOES_NOT_MATCH_MESSAGE;
        } else {
            return SUCCESSFUL_REGISTRATION_MESSAGE;
        }
    }

    private boolean emailValidation(String email) {
        String emailRegEx = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
        return email.matches(emailRegEx);
    }

    private boolean loginValidation(String login) {
        String loginRegEx = "^(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d]{6,12}$";
        return login.matches(loginRegEx);
    }

    private boolean passwordValidation(String password) {
        String passwordRegEx = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,12}$";
        return password.matches(passwordRegEx);
    }

    private boolean confirmPasswordValidation(String password, String confirmPassword) {
        return confirmPassword.equals(password);
    }
}
