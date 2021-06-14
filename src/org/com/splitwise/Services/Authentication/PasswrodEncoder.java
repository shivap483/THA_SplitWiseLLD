package org.com.splitwise.Services.Authentication;

public interface PasswrodEncoder {
    public String encode(String password, String userName);
}
