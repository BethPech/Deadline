package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getAnotherAuthInfo(){
        return new AuthInfo("petya","qwerty125");
    }
    @Value
    public static class VerificationInfo {
        private String login;
        private String code;
    }

    public static VerificationInfo getVerificationInfoFor(AuthInfo authInfo, String code) {
        return new VerificationInfo(authInfo.getLogin(), code);
    }


}
