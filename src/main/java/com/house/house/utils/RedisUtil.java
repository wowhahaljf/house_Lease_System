package com.house.house.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisUtil<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 当key值不存在时新增缓存返回true，否则返回false
     * @param key
     * @param value
     * @return
     */
    public boolean setNx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    /**
     * 向redis中set集合添加值，点赞
     * @param key
     * @param value
     * @return
     */
    public void sAdd(String key,String value) {
        redisTemplate.opsForSet().add(key,value);
    }

    /**
     * 向redis中set集合移除，取消点赞
     * @param key
     * @param value
     * @return
     */
    public Long srem(String key,String value) {
        return redisTemplate.opsForSet().remove(key,value);
    }

    /**
     * 判断key，value是否是集中值
     * @param key
     * @param value
     * @return
     */
    public boolean isMember(String key,String value) {
        return redisTemplate.opsForSet().isMember(key,value);
    }

    /**
     * 获取集合大小
     * @param key
     * @return
     */
    public Long size(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 获取集合里所有数据
     * @param key
     * @return
     */
    public Set<String> sMembers(String key) {
        Set<String> members = redisTemplate.opsForSet().members(key);
        return members;
    }
}
