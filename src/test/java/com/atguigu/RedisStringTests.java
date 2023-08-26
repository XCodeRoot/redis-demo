package com.atguigu;

import com.atguigu.redis.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisStringTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //引用json序列化器
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testString() {
        // 写入一条String数据
        stringRedisTemplate.opsForValue().set("verify:phone:13600527634", "124143");
        // 获取string数据
        Object name = stringRedisTemplate.opsForValue().get("verify:phone:13600527634");
        System.out.println("name = " + name);
    }


    // 写入数据:  对象 ==> 手动序列化 ==> json , 然后写入进redis数据库
    // 读出数据:  从redis取出 json数据 ==> 反序列化 ==> 对象
    @Test
    void testSaveUser() throws JsonProcessingException {
        // 创建对象
        User user = new User("虎哥", 21);
        // 手动序列化 为json
        String json = mapper.writeValueAsString(user);
        // 写入数据
        stringRedisTemplate.opsForValue().set("user:200", json);

        // 获取数据
        String jsonUser = stringRedisTemplate.opsForValue().get("user:200");
        // 手动反序列化
        User user1 = mapper.readValue(jsonUser, User.class);
        System.out.println("user1 = " + user1);
    }


}
