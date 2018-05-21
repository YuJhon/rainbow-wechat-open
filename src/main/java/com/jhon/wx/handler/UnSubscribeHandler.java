package com.jhon.wx.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>功能描述</br> 取消关注触发事件 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName UnSubscribeHandler
 * @date 2017/10/13 22:24
 */
@Component
@Slf4j
public class UnSubscribeHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
		String openId = wxMessage.getFromUser();
		log.info("取消关注用户OPENID："+openId);
		// TODO 更新本地数据库中微信粉丝的信息
		return null;
	}
}
