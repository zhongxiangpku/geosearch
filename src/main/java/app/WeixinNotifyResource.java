//package app;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.ws.rs.Path;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import com.alibaba.fastjson.JSON;
//import com.wordnik.swagger.annotations.Api;
//import com.ymt.common.exceptions.ApiException;
//import com.ymt.enums.ErrorCodeEnum;
//import com.ymt.utils.RequestUtil;
//
//import jersey.repackaged.com.google.common.collect.Lists;
//
//@Path("/sys/notify")
//@Api(value = "微信通知管理REST API", description = "微信通知管理REST API")
//public class WeixinNotifyResource {
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//	private final static String WEIXINTOKENURL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx34b076dc463b6e2a&corpsecret=38p1h3WvSK1FT45DR-Hxba2rKoVpnNgv5O5NCCrwn0V5oRkAKucbAA3QgIr-JATo";
//
//	private final static String WEIXINSENDMESSAGEURL = "https://qyapi.weixin.qq.com/cgi-bin/message/send";
//
//	// @POST
//	// @Path("/")
//	// @Consumes(MediaType.MULTIPART_FORM_DATA)
//	// @ApiOperation(value = "发送微信通知", response = String.class, httpMethod =
//	// "POST", notes = "发送微信通知,"+
//	// "{\"touser\": \"UserID1|UserID2|UserID3\","+
//	// "\"toparty\": \" PartyID1 | PartyID2 \","+
//	// "\"totag\": \" TagID1 | TagID2 \","+
//	// "\"msgtype\": \"text\","+
//	// "\"agentid\": 1,"+
//	// "\"text\": {"+
//	// "\"content\": \"Holiday Request For Pony(http://xxxxx)\""+
//	// "},"+
//	// "\"safe\":\"0\""+
//	// "}")
//	// @ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
//	// public Response sendMessage(@HeaderParam("Authorization") String
//	// authToken) {
//	// String accessToken = getAccessToken();
//	// String re = null;
//	// Map<String, Object> param = new HashMap<String, Object>();
//	// param.put("touser", "zhongxiang");
//	// param.put("msgtype", "text");
//	// param.put("agentid", 7);
//	// Map<String, Object> text = new HashMap<String, Object>();
//	// text.put("content", "测试微信发送消息");
//	// param.put("text", text);
//	//
//	// try {
//	// re =
//	// RequestUtil.postRequest(WEIXINSENDMESSAGEURL+"?access_token="+accessToken,
//	// param);
//	// if (re != null) {
//	//
//	// }
//	// } catch (ApiException e) {
//	// logger.error(e.getMessage(), e);
//	// throw e;
//	// } catch (Exception e) {
//	// e.printStackTrace();
//	// throw new ApiException(ErrorCodeEnum.CustomerInvokeError.getCode(),
//	// ErrorCodeEnum.CustomerInvokeError.getDescription() + ":" +
//	// e.getMessage());
//	// }
//	// return ResponseBuilder.buildSuccessResponse(re);
//	// }
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
//	public void sendMessage(List<String> tousers, String msgtype, int agentid, String message) {
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
//			RequestUtil.postRequest(WEIXINSENDMESSAGEURL + "?access_token=" + accessToken, param);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ApiException(ErrorCodeEnum.CustomerInvokeError.getCode(),
//					ErrorCodeEnum.CustomerInvokeError.getDescription() + ":" + e.getMessage());
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	private String getAccessToken() {
//		Map<String, Object> param = new HashMap<String, Object>();
//		try {
//			String accessTokenJson = RequestUtil.postRequest(WEIXINTOKENURL, param);
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
//		String message = "一亩田欢迎您！www.baidu.com百度";
//		new WeixinNotifyResource().sendMessage(tousers, msgtype, agentid, message);
//	}
//}
