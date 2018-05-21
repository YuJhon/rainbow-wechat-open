package com.jhon.wx.handler;

import com.jhon.wx.builder.TextBuilder;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>功能描述</br> 扫描事件处理 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ScanHandler
 * @date 2017/10/14 8:36
 */
@Component
@Slf4j
public class ScanHandler extends AbstractHandler {

	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
                                  WxMpService wxMpService, WxSessionManager sessionManager)
					throws WxErrorException {

		return new TextBuilder().build("我就知道你是扫码进来的，哈哈！", wxMessage, wxMpService);
	}
}
