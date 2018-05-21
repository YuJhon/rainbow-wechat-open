package com.jhon.wx.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>功能描述</br> 微信账号配置信息 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WechatAccountConfig
 * @date 2017/10/12 10:34
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

  /**
   * <pre>开放平台AppId</pre>
   */
  private String openAppId;
  /**
   * <pre>开放平台AppSecret</pre>
   */
  private String openAppSecret;

  private String openToken;

  private String openAesKey;

}
