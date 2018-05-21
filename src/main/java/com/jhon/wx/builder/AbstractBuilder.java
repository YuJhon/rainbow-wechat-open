package com.jhon.wx.builder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * <p>功能描述</br> 构造类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName AbstractBuilder
 * @date 2017/10/12 22:55
 */
public abstract class AbstractBuilder {

	/**
	 * @param content
	 * @param wxMessage
	 * @param service
	 * @return
	 */
	public abstract WxMpXmlOutMessage build(String content,
                                          WxMpXmlMessage wxMessage,
                                          WxMpService service);
}
