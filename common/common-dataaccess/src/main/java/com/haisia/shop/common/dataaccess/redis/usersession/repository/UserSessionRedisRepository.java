package com.haisia.shop.common.dataaccess.redis.usersession.repository;

import com.haisia.shop.common.dataaccess.redis.usersession.entity.UserSessionRedisEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRedisRepository extends CrudRepository<UserSessionRedisEntity, String> {
}
