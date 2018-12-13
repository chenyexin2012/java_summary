package com.holmes.prototype;

import java.io.Serializable;

/**
 * @Description:
 * @Author:         holmes
 * @CreateDate:     2018/12/13 19:27
 * @Version:        1.0.0
*/
public class User implements Serializable {

    private String userName;
    private String userPassword;
    private String userEmail;
    private Address address;

    public User() {
    }

    public User(String userName, String userPassword, String userEmail, Address address) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("userName='").append(userName).append('\'');
        sb.append(", userPassword='").append(userPassword).append('\'');
        sb.append(", userEmail='").append(userEmail).append('\'');
        sb.append(", address=").append(address);
        sb.append('}');
        return sb.toString();
    }
}
