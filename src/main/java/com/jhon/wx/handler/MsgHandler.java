package com.jhon.wx.handler;

import com.alibaba.fastjson.JSON;
import com.jhon.wx.builder.NewsBuilder;
import com.jhon.wx.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>功能描述</br> 消息处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName MsgHandler
 * @date 2017/10/14 8:42
 */
@Component
@Slf4j
public class MsgHandler extends AbstractHandler {
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
                                  WxMpService wxMpService, WxSessionManager sessionManager)
					throws WxErrorException {

		if (!WxConsts.XmlMsgType.EVENT.equals(wxMessage.getMsgType())) {
			// 可以选择保存到本地
		}

		// 当用户输入关键词，比如："你好"，"客服" 等，并且有客服在线时，把消息转发给在线客服
		try {
			if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
							&& wxMpService.getKefuService().kfOnlineList()
							.getKfOnlineList().size() > 0) {
				return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
								.fromUser(wxMessage.getToUser())
								.toUser(wxMessage.getFromUser())
								.build();
			}
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		log.info("收到微信消息：{}",JSON.toJSONString(wxMessage));
		String content = wxMessage.getContent();
		if ("1".equals(content)){
			return new TextBuilder().build("您好，你给我发消息内容为："+content, wxMessage, wxMpService);
		}else if ("2".equals(content)){
			return new NewsBuilder().build(content, wxMessage, wxMpService);
		}else{
			return new TextBuilder().build("小万服务家今天累了，求你们别骚扰了！", wxMessage, wxMpService);
		}
//		return new TextBuilder().build(content, wxMessage, wxMpService);
	}
}
