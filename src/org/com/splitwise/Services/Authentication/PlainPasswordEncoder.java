package org.com.splitwise.Services.Authentication;

public class PlainPasswordEncoder implements PasswrodEncoder {

    @Override
    public String encode(String password, String userName) {
        return password;
    }
}
