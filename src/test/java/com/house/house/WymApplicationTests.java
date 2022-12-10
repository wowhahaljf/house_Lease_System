package com.house.house;

import cn.hutool.json.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.house.house.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

//让测试运行于Spring测试环境，以便在测试开始的时候自动创建Spring的应用上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//spring将初始化嵌入式Web服务器并对该Web服务器运行此测试
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WymApplicationTests {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //JSON工具
    private static final ObjectMapper objectMapper=new ObjectMapper();

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name","李四");
        Object name =redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    @Test
    void testSaveUser(){
        redisTemplate.opsForValue().set("user:100",new Users("ljf","123","123","jj"));
        Object o = redisTemplate.opsForValue().get("user:100");
        System.out.println(o);
    }
    @Test
    void test3() throws JsonProcessingException {
        Users u = new Users("ljf", "123", "123", "jj");
        //手动序列化
        String json = objectMapper.writeValueAsString(u);
        //写入数据到redis
        stringRedisTemplate.opsForValue().set("user:100", json);
        //读取数据
        String val = stringRedisTemplate.opsForValue().get("user:100");
        //反序列化
        Users u1 = objectMapper.readValue(val, Users.class);

        System.out.println(u1);

    }

}
