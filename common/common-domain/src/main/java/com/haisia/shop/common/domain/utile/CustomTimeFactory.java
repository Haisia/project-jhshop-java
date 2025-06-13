package com.haisia.shop.common.domain.utile;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class CustomTimeFactory {

  public static String KOREA_ZONE_ID = "Asia/Seoul";

  public static String DEFAULT_ZONE_ID = KOREA_ZONE_ID;
  public static ZoneId DEFAULT_ZONE = ZoneId.of(DEFAULT_ZONE_ID);

  public static ZonedDateTime now() {
    return ZonedDateTime.now(DEFAULT_ZONE);
  }

  private CustomTimeFactory() {
  }
}
