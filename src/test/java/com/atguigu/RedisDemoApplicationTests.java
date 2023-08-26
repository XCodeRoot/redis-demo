package com.atguigu;

import com.atguigu.redis.pojo.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    void testString() {
        redisTemplate.opsForValue().set("name","虎哥");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void saveUser(){
        redisTemplate.opsForValue().set("user:100",new User("虎哥",18));
        Object user = redisTemplate.opsForValue().get("user:100");
        System.out.println(user);

    }

}
