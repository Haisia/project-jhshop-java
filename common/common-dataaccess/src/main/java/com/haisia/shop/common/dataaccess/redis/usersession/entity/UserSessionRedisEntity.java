package com.haisia.shop.common.dataaccess.redis.usersession.entity;

import com.haisia.shop.common.domain.constants.RedisKeyPrefixConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Builder
@RedisHash(value= RedisKeyPrefixConstants.SESSION)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSessionRedisEntity {
  @Id
  private String key;
  private String email;
  @TimeToLive
  private Long expiration;
}
