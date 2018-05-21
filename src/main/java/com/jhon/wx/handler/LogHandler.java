package com.jhon.wx.handler;

import com.alibaba.fastjson.JSON;
import com.jhon.wx.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>功能描述</br> 日志处理器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName LogHandler
 * @date 2017/10/12 23:05
 */
@Slf4j
@Component
public class LogHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) {
		this.log.info("\n接收到请求消息，内容：{}", JSON.toJSONString(wxMessage));
		return new TextBuilder().build("开发组测试！", wxMessage, wxMpService);
	}
}