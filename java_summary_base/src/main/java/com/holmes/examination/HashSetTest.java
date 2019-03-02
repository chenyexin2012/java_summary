package com.holmes.examination;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author Administrator
 */
public class HashSetTest {

    public static void main(String[] args) {

        /**
         *  只通过id和name两个字段进行去重
         */
        Set<User> set = new HashSet<>();
        set.add(new User(1L, "aaa", 12));
        set.add(new User(1L, "aaa", 13));
        set.add(new User(2L, "aaa", 14));
        set.add(new User(1L, "aaa", 15));
        set.add(new User(1L, "bbb", 15));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            User user = (User) iterator.next();
            System.out.println(user);
        }
    }

    static class User {

        private Long id;

        private String name;

        private Integer age;

        public User(Long id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return Objects.equals(id, user.id) &&
                    Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("User{");
            sb.append("id=").append(id);
            sb.append(", name='").append(name).append('\'');
            sb.append(", age=").append(age);
            sb.append('}');
            return sb.toString();
        }
    }
}
