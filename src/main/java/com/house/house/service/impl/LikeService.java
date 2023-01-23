package com.house.house.service.impl;

import com.house.house.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LikeService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 点赞
     * @param userId 点赞人id
     * @param entityType 房屋点赞
     * @param entityId  房屋id
     */
    public int like(int userId, String entityType, int entityId) {
        // like:entity:entityType:entityId -> set(userId)
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        boolean isMember = redisTemplate.opsForSet().isMember(entityLikeKey, userId);
        if (isMember) {
            redisTemplate.opsForSet().remove(entityLikeKey, userId);
            return 0;
        } else {
            redisTemplate.opsForSet().add(entityLikeKey, userId);
            return 1;
        }

    }

    /**
     * 查询实体点赞的数量
     * @param entityType
     * @param entityId
     * @return
     */
    public long findEntityLikeCount(String entityType,int entityId){
        //生成key
        String entityLikeKey =RedisKeyUtil.getEntityLikeKey(entityType,entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);

    }

    /**
     * 查询某人对某实体的点赞状态
     * @param userId
     * @param entityType
     * @param entityId
     * @return    1 点赞        0 没有点赞
     */
    public int findEntityLikeStatus(int userId,String entityType,int entityId){
        String entityLikeKey =RedisKeyUtil.getEntityLikeKey(entityType,entityId);
        return  redisTemplate.opsForSet().isMember(entityLikeKey,userId)?1:0;
    }
}
