package com.jhon.wx.handler;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>功能描述</br> 菜单处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName MenuHandler
 * @date 2017/10/13 22:16
 */
@Component
public class MenuHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
                                  WxMpService wxMpService, WxSessionManager sessionManager)
					throws WxErrorException {
		String msg = String.format("type:%s, event:%s, key:%s",
						wxMessage.getMsgType(),wxMessage.getEvent(),
						wxMessage.getEventKey());
		if (WxConsts.EventType.VIEW.equals(wxMessage.getEvent())){
			return null;
		}
		return WxMpXmlOutMessage.TEXT().content(msg)
						.fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
						.build();
	}
}
