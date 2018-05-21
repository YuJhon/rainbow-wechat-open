package com.jhon.wx.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.bean.result.WxOpenAuthorizerInfoResult;
import me.chanjar.weixin.open.bean.result.WxOpenQueryAuthResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>功能描述</br> 微信控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName WechatController
 * @date 2018/04/03 17:38
 */
@Slf4j
@RestController
@RequestMapping("api")
public class WechatApiController {

  @Autowired
  private WxOpenService wxOpenService;

  @GetMapping("auth/goto_auth_url_show")
  public String gotoPreAuthUrlShow() {
    return "<a href='goto_auth_url'>go</a>";
  }

  @GetMapping("auth/goto_auth_url")
  public void gotoPreAuthUrl(HttpServletRequest request, HttpServletResponse response) {
    String host = request.getHeader("host");
    String url = "http://" + host + "/api/auth/jump";
    try {
      url = wxOpenService.getWxOpenComponentService().getPreAuthUrl(url);
      response.sendRedirect(url);
    } catch (WxErrorException | IOException e) {
      log.error("gotoPreAuthUrl", e);
      throw new RuntimeException(e);
    }
  }

  @GetMapping("auth/jump")
  public WxOpenQueryAuthResult jump(@RequestParam("auth_code") String authorizationCode) {
    try {
      WxOpenQueryAuthResult queryAuthResult = wxOpenService.getWxOpenComponentService().getQueryAuth(authorizationCode);
      log.info("getQueryAuth", queryAuthResult);
      return queryAuthResult;
    } catch (WxErrorException e) {
      log.error("gotoPreAuthUrl", e);
      throw new RuntimeException(e);
    }
  }

  @GetMapping("get_authorizer_info")
  public WxOpenAuthorizerInfoResult getAuthorizerInfo(@RequestParam String appId) {
    try {
      return wxOpenService.getWxOpenComponentService().getAuthorizerInfo(appId);
    } catch (WxErrorException e) {
      log.error("getAuthorizerInfo", e);
      throw new RuntimeException(e);
    }
  }
}
