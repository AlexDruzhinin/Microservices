package com.example.authservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Users {
    @Id
    String userName;
    String password;
    boolean enabled;
    LocalDateTime lastLoginTime;
    LocalDateTime lastAttemptTime;
    int failedLoginAttempt;

    public Users() {
    }

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
        enabled = true;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public LocalDateTime getLastAttemptTime() {
        return lastAttemptTime;
    }

    public void setLastAttemptTime(LocalDateTime lastAttemptTime) {
        this.lastAttemptTime = lastAttemptTime;
    }

    public int getFailedLoginAttempt() {
        return failedLoginAttempt;
    }

    public void setFailedLoginAttempt(int failedLoginAttempt) {
        this.failedLoginAttempt = failedLoginAttempt;
    }
}
