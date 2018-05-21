package com.jhon.wx.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutImageMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * <p>功能描述</br> 图片处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ImageBuilder
 * @date 2017/10/14 9:05
 */
public class ImageBuilder extends AbstractBuilder {

	@Override
	public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {

		WxMpXmlOutImageMessage message = WxMpXmlOutMessage.IMAGE().mediaId(content)
						.fromUser(wxMessage.getToUser())
						.toUser(wxMessage.getFromUser())
						.build();

		return message;
	}
}
