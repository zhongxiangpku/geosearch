//package app;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alibaba.fastjson.JSON;
//import com.ymt.common.exceptions.ApiException;
//import com.ymt.enums.ErrorCodeEnum;
//
//import jersey.repackaged.com.google.common.collect.Lists;
//
//public class WeixinNotifyUtil {
//	private final static Logger logger = LoggerFactory.getLogger("com.ymt.utils.WeixinNotifyUtil");
//	private final static String WEIXINTOKENURL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx34b076dc463b6e2a&corpsecret=38p1h3WvSK1FT45DR-Hxba2rKoVpnNgv5O5NCCrwn0V5oRkAKucbAA3QgIr-JATo";
//
//	private final static String WEIXINSENDMESSAGEURL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";
//	
//	/**
//	 * d调用微信Api推送消息
//	 * 
//	 * @param tousers
//	 *            接收消息用户列表
//	 * @param msgtype
//	 *            消息类型
//	 * @param agentid
//	 *            应用类型
//	 * @param msg
//	 *            消息内容
//	 */
//	public static void sendMessage(List<String> tousers, String msgtype, int agentid, String message) {
//		if(tousers == null || tousers.size()==0)
//			return;
//		// 1.获取access_token
//		String accessToken = getAccessToken();
//		// 2.根据access_token授权发送消息
//		Map<String, Object> param = new HashMap<String, Object>();
//		StringBuilder sb = new StringBuilder();
//		for (String user : tousers) {
//			sb.append(user);
//			sb.append("|");
//		}
//		String touser = sb.substring(0, sb.length() - 1);
//		param.put("touser", touser); // 接收用户
//		param.put("msgtype", msgtype); // 消息类型
//		param.put("agentid", agentid); // 应用ID
//		Map<String, Object> msgMap = new HashMap<String, Object>();
//		if (msgtype.equals("text"))
//			msgMap.put("content", message);
//		else if (msgtype.equals("image") || msgtype.equals("voice") || msgtype.equals("file"))
//			msgMap.put("media_id", message);
//
//		param.put(msgtype, msgMap); // 具体消息内容
//		try {
//			String result = RequestUtil.postRequest(WEIXINSENDMESSAGEURL + "?access_token=" + accessToken, param);
//			logger.debug(result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ApiException(ErrorCodeEnum.CustomerInvokeError.getCode(),
//					ErrorCodeEnum.CustomerInvokeError.getDescription() + ":" + e.getMessage());
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	private static String getAccessToken() {
//		Map<String, Object> param = new HashMap<String, Object>();
//		try {
//			String accessTokenJson = RequestUtil.postRequest(WEIXINTOKENURL, param);
//			logger.debug(accessTokenJson);
//			Map<String, Object> result = JSON.parseObject(accessTokenJson, Map.class);
//			if (result != null && result.get("access_token") != null)
//				return result.get("access_token").toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ApiException(ErrorCodeEnum.CustomerInvokeError.getCode(),
//					ErrorCodeEnum.CustomerInvokeError.getDescription() + ":" + e.getMessage());
//		}
//		return null;
//	}
//
//	public static void main(String[] args) {
//		List<String> tousers = Lists.newArrayList();
//		tousers.add("zhongxiang");
//		String msgtype = "text";
//		int agentid = 7;
//		String message = "一亩田欢迎您！";
//		sendMessage(tousers, msgtype, agentid, message);
//	}
//}
