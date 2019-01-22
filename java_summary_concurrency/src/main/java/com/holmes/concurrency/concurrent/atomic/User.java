package com.holmes.concurrency.concurrent.atomic;

/**
 * @Description:
 * @Author: holmes
 * @Version: 1.0.0
*/
public class User {

        private String value;

        private Integer balance;

        public User(String value, Integer balance) {
            this.value = value;
            this.balance = balance;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("User{");
            sb.append("value='").append(value).append('\'');
            sb.append(", balance=").append(balance);
            sb.append('}');
            return sb.toString();
        }
    }