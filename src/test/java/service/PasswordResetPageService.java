package service;

import page.PasswordResetPage;

public class PasswordResetPageService {

    private PasswordResetPage passwordResetPage;

    public PasswordResetPageService fillEmail(String email) {
        passwordResetPage.fillEmail(email);
        return this;
    }
}
