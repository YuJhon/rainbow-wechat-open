package com.jhon.wx.config;

import me.chanjar.weixin.open.api.WxOpenConfigStorage;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenInRedisConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

/**
 * <p>功能描述</br></p>
 *
 * @author jiangy19
 * @version v1.0
 * @projectName rainbow-wechat-open
 * @date 2018/3/28 20:18
 */
@Component
public class WechatOpenConfig {

  @Autowired
  private JedisPool jedisPool;

  @Autowired
  private WechatAccountConfig accountConfig;

  @Bean
  public WxOpenService wxOpenService() {
    WxOpenService wxopenService = new WxOpenServiceImpl();
    wxopenService.setWxOpenConfigStorage(wxOpenConfigStorage());
    return wxopenService;
  }

  @Bean
  public WxOpenConfigStorage wxOpenConfigStorage() {
    WxOpenInRedisConfigStorage inRedisConfigStorage = new WxOpenInRedisConfigStorage(jedisPool);
    inRedisConfigStorage.setComponentAppId(accountConfig.getOpenAppId());
    inRedisConfigStorage.setComponentAppSecret(accountConfig.getOpenAppSecret());
    inRedisConfigStorage.setComponentToken(accountConfig.getOpenToken());
    inRedisConfigStorage.setComponentAesKey(accountConfig.getOpenAesKey());
    return  inRedisConfigStorage;
  }
}
