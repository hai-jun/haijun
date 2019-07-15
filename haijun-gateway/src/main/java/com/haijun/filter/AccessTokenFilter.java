package com.haijun.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haijun.config.Audience;
import com.haijun.model.SkipApi;
import com.haijun.model.Usertoken;
import com.haijun.service.ISkipApiService;
import com.haijun.service.IUsertokenService;
import com.haijun.util.JwtHelper;
import com.haijun.util.ResultMsg;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import io.jsonwebtoken.Claims;

/**
 * 网关分发过滤器 功能： 校验token、跨域访问、路由参数
 * 
 * @author funton
 *
 */
public class AccessTokenFilter extends ZuulFilter {
	//private Logger log = LoggerFactory.getLogger(AccessParamsFilter.class);

	@Autowired
	private Audience audience;
	
	@Autowired
	private IUsertokenService userTokenService;
	
	@Autowired
	private ISkipApiService skipApiService;
	
	/**
	 * 返回pre表示请求到达网关，还没有路由的时候
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		ResultMsg msg = null;
		
		//设置跨域访问
		String remote = request.getHeader("Origin");
		ctx.getResponse().setHeader("Access-Control-Allow-Headers", "authorization, content-type");
		ctx.getResponse().setHeader("Access-Control-Allow-Origin", remote);
		
		StringBuffer urlPath = request.getRequestURL();
		SkipApi skipApi = skipApiService.verification(urlPath.toString());
		if(skipApi != null) {
			ctx.setSendZuulResponse(true);
			Map<String, List<String>> qp = new HashMap<String, List<String>>();
			List<String> tempList = new ArrayList<String>();
			String encData = request.getParameter("encData");
			tempList = new ArrayList<String>();
			tempList.add(encData);
			qp.put("dataJson", tempList);
			ctx.setRequestQueryParams(qp);
			return null;
		}
		
		String auth = request.getHeader("Authorization");
		
		if(auth!=null&&auth.length()>7) {
			String headstr = auth.substring(0, 6).toLowerCase();
			if(headstr.compareTo("bearer")==0) {
				String token = auth.substring(7,auth.length());
				msg = validate(ctx,token);
			}else {
				msg = new ResultMsg(10005, "token错误");
			}
		}else {
			msg = new ResultMsg(10005, "token验证失败");
		}
		if(msg.getCode()!=200) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseBody(msg.toString());
	        ctx.getResponse().setCharacterEncoding("UTF-8");
	        ctx.getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
		}else {
			ctx.setSendZuulResponse(true);
			Map<String, List<String>> qp = new HashMap<String, List<String>>();
			List<String> tempList = new ArrayList<String>();
			String encData = request.getParameter("encData");
			tempList = new ArrayList<String>();
			tempList.add(encData);
			qp.put("dataJson", tempList);
			ctx.setRequestQueryParams(qp);
		}
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public ResultMsg validate(RequestContext ctx,String token) {
		ResultMsg msg = null;
		Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
		if(claims==null) {
			msg = new ResultMsg(10005, "token验证失败");
			return msg;
		}
		Map<String, Object> userMap = (Map<String, Object>) claims.get("user");
		//使用useraMap 去查询数据库中的token
		String userName = (String) userMap.get("userName");
		int userId = (Integer) userMap.get("userId");
		Usertoken user = userTokenService.getOne(new QueryWrapper<Usertoken>().eq(true, "userId", userId).eq(true, "userName", userName));
		if(user == null||!token.equals(user.getToken())) {
			msg = new ResultMsg(10005, "token验证失败");
		}else {
			msg = new ResultMsg(200, "token验证通过");
		}
		
		return msg;
	}

}