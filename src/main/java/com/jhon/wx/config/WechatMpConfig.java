package com.jhon.wx.config;

import com.jhon.wx.handler.*;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.api.impl.WxMpMenuServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpUserServiceImpl;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import me.chanjar.weixin.open.api.WxOpenService;
import me.chanjar.weixin.open.api.impl.WxOpenMessageRouter;
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
 * @date 2018/3/29 22:37
 */
@Component
public class WechatMpConfig {

  @Autowired
  private LogHandler logHandler;

  @Autowired
  private SubscribeHandler subscribeHandler;

  @Autowired
  private UnSubscribeHandler unSubscribeHandler;

  @Autowired
  private MenuHandler menuHandler;

  @Autowired
  private NullHandler nullHandler;

  @Autowired
  private ScanHandler scanHandler;

  @Autowired
  private LocationHandler locationHandler;

  @Autowired
  private MsgHandler msgHandler;

  @Autowired
  private StoreCheckNotifyHandler storeCheckNotifyHandler;

  @Autowired
  private KfSessionHandler kfSessionHandler;


  @Bean
  public WxOpenMessageRouter router(WxOpenService wxOpenpService) {

    final WxOpenMessageRouter newRouter = new WxOpenMessageRouter(wxOpenpService);

    // 记录所有事件的日志 （异步执行）
    newRouter.rule().handler(this.logHandler).next();

    // 文本消息
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.TEXT)
            .handler(msgHandler)
            .end();

    // 关注事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.SUBSCRIBE)
            .handler(subscribeHandler)
            .end();
    // 取消关注事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.UNSUBSCRIBE)
            .handler(unSubscribeHandler)
            .end();

    // 自定义菜单事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.CLICK)
            .handler(menuHandler)
            .end();

    // 点击菜单连接事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.VIEW)
            .handler(nullHandler)
            .end();

    // 扫码事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.SCAN)
            .handler(scanHandler)
            .end();

    // 上报地理位置事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxConsts.EventType.LOCATION)
            .handler(locationHandler)
            .end();

    // 接收地理位置消息
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.LOCATION)
            .handler(locationHandler)
            .end();


    // 接收客服会话管理事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
            .handler(kfSessionHandler)
            .end();
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
            .handler(kfSessionHandler)
            .end();
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
            .handler(kfSessionHandler)
            .end();

    // 门店审核事件
    newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.EVENT)
            .event(WxMpEventConstants.POI_CHECK_NOTIFY)
            .handler(storeCheckNotifyHandler)
            .end();

    return newRouter;
  }
}
