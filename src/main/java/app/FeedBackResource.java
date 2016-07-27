//package app;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.HeaderParam;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Lists;
//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiResponse;
//import com.wordnik.swagger.annotations.ApiResponses;
//import com.ymt.common.exceptions.ApiException;
//import com.ymt.common.pager.Pager;
//import com.ymt.domain.sys.FeedBackDomain;
//import com.ymt.entities.query.QueryCondition;
//import com.ymt.service.sys.IFeedBackService;
//import com.ymt.service.sys.IWeixinNotifyService;
//import com.ymt.utils.ConditionBuilder;
//import com.ymt.utils.ResponseBuilder;
//
//
//@Path("/sys/feedBack")
//@Consumes(MediaType.APPLICATION_JSON)
//@Api(value = "系统反馈管理REST API", description = "系统反馈管理REST API")
//public class FeedBackResource {
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	private IFeedBackService feedBackService = null;
//	@Autowired
//	private IWeixinNotifyService weixinNotifyService = null;
//	
//	@POST
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON) 
// 	@ApiOperation(value = "添加系统反馈", response = FeedBackDomain.class, httpMethod = "POST", notes="添加系统反馈,数据demo：["+
//		"{ <br>"+
//		"  \"reason\": \"我的\", <br>"+
//		"  \"frompage\": \"/cust/custInfo/add\", <br>"+ 
//		"  \"errormsg\": \"原始错误信息（后端返回的错误信息）\", <br>"+
//		"  \"attachmentList\":[{ \"url\": \"xxxxxx\"},{\"url\": \"xxxxxx\"}] <br>"+
//		"} <br>"+
//		"] <br>,反馈类型为1表示客户重复反馈，添加成功后，返回对应的列表页面（买家列表，卖家列表）")
// 	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
//    public Response post(@HeaderParam("Authorization") String authToken,String json) throws IOException {
//		logger.debug("新增系统反馈，请求参数为："+json);
//		List<FeedBackDomain> dataList = null;
//		try {
//			dataList = JSON.parseArray(json,FeedBackDomain.class);
//			feedBackService.create(dataList);
//		} catch (ApiException e) {
//			logger.error(e.getMessage(), e);
//			return ResponseBuilder.buildFaildResponse(e);
//		}
//		logger.debug("新增系统反馈成功！");
//        return ResponseBuilder.buildSuccessResponse(dataList);
//    }
//
//	@GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON) 
// 	@ApiOperation(value = "根据ID查询系统反馈信息", response = FeedBackDomain.class, httpMethod = "GET", notes="根据ID查询系统反馈信息,只对管理员开放")
// 	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
//    public Response get(@HeaderParam("Authorization") String authToken,@PathParam("id") Integer feedBackId) throws IOException {
//		logger.debug("根据ID查询系统反馈，请求参数为："+feedBackId);
//		FeedBackDomain feedBack = null;
//		try {
//			feedBack = feedBackService.find(feedBackId);
//		} catch (ApiException e) {
//			logger.error(e.getMessage(), e);
//			return ResponseBuilder.buildFaildResponse(e);
//		}
//		logger.debug("根据ID查询系统反馈成功！");
//        return ResponseBuilder.buildSuccessResponse(feedBack);
//    }
//
//	@GET
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON) 
// 	@ApiOperation(value = "查询系统反馈列表", response = FeedBackDomain.class, httpMethod = "GET", notes="查询系统反馈列表,只对管理员开放")
// 	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
//    public Response queryByParentIdd(@HeaderParam("Authorization") String authToken,@Context HttpServletRequest request) throws IOException {
//		Pager<FeedBackDomain> pager = null;
//		try {
//			 //构造查询条件
//			QueryCondition queryCondition =	ConditionBuilder.buildCondition(request, FeedBackDomain.class);
//			logger.debug("根据条件查询系统反馈，请求参数为:",JSON.toJSON(queryCondition).toString());
//			pager =  feedBackService.findForPager(queryCondition);
//		} catch (ApiException e) {
//			logger.error(e.getMessage(), e);
//			return ResponseBuilder.buildFaildResponse(e);
//		}
//		
//        return ResponseBuilder.buildPager(pager);
//    } 
//	
//	@GET
//    @Path("/testweixin")
//    @Produces(MediaType.APPLICATION_JSON) 
// 	@ApiOperation(value = "查询系统反馈列表", response = FeedBackDomain.class, httpMethod = "GET", notes="查询系统反馈列表,只对管理员开放")
// 	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
//    public Response testWeixin() throws IOException {
//		
//		try {
//			List<Integer> staffIds = Lists.newArrayList();
//			staffIds.add(3819);
//			String msgtype="text";
//			int agentid = 7;
//			String message = "test weixin api";
//			boolean isSuccess = weixinNotifyService.sendMessage(staffIds, msgtype, agentid, message);
//			logger.info("send message succeed?"+isSuccess);
//		} catch (ApiException e) {
//			logger.error(e.getMessage(), e);
//			return ResponseBuilder.buildFaildResponse(e);
//		}
//		
//        return ResponseBuilder.buildPager(null);
//    } 
//}
