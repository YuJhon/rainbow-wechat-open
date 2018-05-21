package com.jhon.wx.handler;

import com.jhon.wx.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>功能描述</br> 地理位置处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName LocationHandler
 * @date 2017/10/14 8:54
 */
@Component
@Slf4j
public class LocationHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
                                  WxMpService wxMpService, WxSessionManager sessionManager)
					throws WxErrorException {

		if (WxConsts.XmlMsgType.LOCATION.equals(wxMessage.getMsgType())) {
			// TODO 接受处理用户发送地理位置消息
			try{
				String content = "感谢反馈，您的地理位置已收到！";
				return new TextBuilder().build(content,wxMessage,null);
			}catch(Exception e){
					log.error("位置消息接收处理失败", e);
			    e.printStackTrace();
			}
		}

		// 上报地理位置信息
		log.info("\n上报地理位置");
		log.info("\n纬度："+wxMessage.getLatitude());
		log.info("\n精度："+wxMessage.getLongitude());
		log.info("\n精度："+String.valueOf(wxMessage.getPrecision()));

		// 可以将用户地理位置信息保存到本地数据库，方便以后绘制用户轨迹图

		return null;
	}
}

