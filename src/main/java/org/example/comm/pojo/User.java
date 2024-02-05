package org.example.comm.pojo;

import lombok.Data;

@Data
public class User<T> {
    private String name;
    private Integer age;
    private T like;

    public static <T> User<T> buildUser(String name, Integer age, T like) {
        User<T> user = new User<>();
        user.setName(name);
        user.setAge(age);
        user.setLike(like);
        return user;
    }
}
