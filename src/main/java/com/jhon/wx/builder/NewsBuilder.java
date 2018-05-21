package com.jhon.wx.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>功能描述</br>图文消息处理</p>
 *
 * @author jiangy19
 * @version v1.0
 * @projectName rainbow-wechat-open
 * @date 2018/5/21 11:19
 */
public class NewsBuilder extends AbstractBuilder {

  @Override
  public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
    List<WxMpXmlOutNewsMessage.Item> items = new ArrayList<WxMpXmlOutNewsMessage.Item>();
    WxMpXmlOutNewsMessage.Item item1 = new WxMpXmlOutNewsMessage.Item();
    item1.setPicUrl("http://odrarykf5.bkt.clouddn.com/yasuko.jpg");
    item1.setDescription("开启新的博客之旅");
    item1.setTitle("开启新的博客之旅");
    item1.setUrl("https://www.jhonrain.org/index/");
    items.add(item1);

    WxMpXmlOutNewsMessage.Item item2 = new WxMpXmlOutNewsMessage.Item();
    item2.setPicUrl("http://www.jiang-yu.cn/uploads/blog/Jdk/java8/java_7_runnable.png");
    item2.setDescription("Java 8 Lambda表达式应用");
    item2.setTitle("Java 8 Lambda表达式应用");
    item2.setUrl("http://www.jiang-yu.cn/2016/11/12/Jdk/Java8--Lambda%E4%BB%8B%E7%BB%8D%E5%92%8C%E4%BD%BF%E7%94%A8/");
    items.add(item2);

    WxMpXmlOutNewsMessage.Item item3 = new WxMpXmlOutNewsMessage.Item();
    item3.setPicUrl("http://jhonrain.oss-cn-shanghai.aliyuncs.com/travel/IMG_0246.JPG");
    item3.setDescription("工作和生活之间的平衡");
    item3.setTitle("工作和生活之间的平衡");
    item3.setUrl("https://www.jhonrain.org/2016/09/21/transfer2blog/");
    items.add(item3);

    me.chanjar.weixin.mp.builder.outxml.NewsBuilder newsBuilder = WxMpXmlOutMessage.NEWS();
    items.forEach(tmp -> {
      newsBuilder.addArticle(tmp);
    });
    /** 构建图文消息 **/
    WxMpXmlOutMessage newsMessage = newsBuilder
            .fromUser(wxMessage.getToUser())
            .toUser(wxMessage.getFromUser())
            .build();
    return newsMessage;
  }
}
