package com.house.house.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
//添加缓存处理机制

@Configuration
public class redisConfig {
   @Bean
   public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
       //创建redisTemplate对象
       RedisTemplate<String,Object> template =new RedisTemplate<>();
       //设置连接工厂
       template.setConnectionFactory(connectionFactory);
       //创建JSON序列化工具
       GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
       //设置key的序列化
       template.setKeySerializer(RedisSerializer.string());
       template.setHashValueSerializer(jsonRedisSerializer);
       //设置Value的序列化
       template.setValueSerializer(jsonRedisSerializer);
       template.setHashValueSerializer(jsonRedisSerializer);

       return template;
   }
}
