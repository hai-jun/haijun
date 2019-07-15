package com.haijun.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 该过滤器用于校验网关本地请求
 * @author funton
 *
 */
@WebFilter(filterName = "appTokenFilter", urlPatterns = "/usertoken/*")
public class AppTokenFilter implements Filter{
//	private Logger log = LoggerFactory.getLogger(AppTokenFilter.class);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request_ = (HttpServletRequest) arg0;
		YangRequestWrapper request = new YangRequestWrapper(request_);
		HttpServletResponse response_ = (HttpServletResponse) arg1;
		YangResponseWrapper response = new YangResponseWrapper(response_);
		String method =  request.getMethod();
		String remoteIp = request.getHeader("Origin");
		response.setHeader("Access-Control-Allow-Headers", "authorization, content-type");
		response.setHeader("Access-Control-Allow-Origin", remoteIp);
		if ("OPTIONS".equalsIgnoreCase(method)) {
			return;
		}
		

		String encData = request.getParameter("encData");
		String encKey = request.getParameter("encKey");
		System.out.println(encData+"++++"+encKey);
		
		request.addParameter("dataJson", encData);
		
		chain.doFilter(request, response);
		//拿到响应结果
		byte[] contet = response.getContent();
		String respJson = new String(contet, "UTF-8");
		//对返回数据进行加密
        System.out.println("返回值:" + respJson);
		
        //继续将响应结果输出调用方
        PrintWriter pw = arg1.getWriter();
		pw.write(respJson);
		pw.flush();
		pw.close();
	}

}