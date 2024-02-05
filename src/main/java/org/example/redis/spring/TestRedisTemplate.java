package org.example.redis.spring;

import org.example.comm.pojo.Likes;
import org.example.comm.pojo.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class TestRedisTemplate {
    public static void main(String[] args) {


        RedisTemplate<String, Object> redisTemplate = RedisTemplateHelper.createRedisTemplate();
        User<Likes> likesUser = User.buildUser("colin", 10, Likes.buildLikes("basketball", "篮球"));
        redisTemplate.opsForValue().set("key", likesUser);
        System.out.println(redisTemplate.opsForValue().get("key"));
    }
}
