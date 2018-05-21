package com.jhon.wx.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;

/**
 * <p>功能描述</br> 文字消息</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName TextBuilder
 * @date 2017/10/12 22:57
 */
public class TextBuilder extends AbstractBuilder {

	@Override
	public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
		WxMpXmlOutTextMessage msg = WxMpXmlOutMessage.TEXT().content(content)
						.fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
						.build();
		return msg;
	}
}
