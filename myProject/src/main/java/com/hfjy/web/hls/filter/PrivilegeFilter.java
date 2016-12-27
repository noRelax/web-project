/**
 * 海风在线学习平台
 * @Title: PrivilegeFilter.java 
 * @Package: com.hyphen.framework.filter
 * @author: cloud
 * @date: 2014年5月6日 上午12:17:01
 * @version: V1.0
 * @copyright: 2014上海风创信息咨询有限公司-版权所有
 * 
 */
package com.hfjy.web.hls.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import com.hfjy.base.bean.SessionUser;
import com.hfjy.base.cache.AppContext;
import com.hfjy.base.util.Constants;
import com.hfjy.framework.exception.AjaxException;
import com.hfjy.framework.logging.LoggerFactory;
import com.hfjy.framework.net.transport.ResponseJson;
import com.hfjy.web.hls.base.CommonController;


/** 
 * @ClassName: PrivilegeFilter 
 * @Description: 权限控制过滤器，过滤所有请求
 * @author cloud 
 * @date 2014年5月6日 上午12:17:01 
 *  
 */
public class PrivilegeFilter extends CommonController implements Filter {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PrivilegeFilter.class);
		
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String reqUrl = request.getServerName().substring(0);	
		// 跳转首页
		if ( reqUrl.equals("") ) {
			response.sendRedirect("html/stu_index.html");
			return;
		}
	
		request.setAttribute("RequestURI", reqUrl);
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		LOGGER.info(">>>"+reqUrl);
		
//		HttpSession session = request.getSession();
//		SessionUser user = (SessionUser) session.getAttribute(Constants.SESSION_USER);
		SessionUser user = getSessionUser(request);
		// 用户有权限访问资源
		if ( AppContext.checkReqUrl(user, reqUrl) ) {
			try {
				chain.doFilter(request, response);
			} catch (Exception ex) {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter print = response.getWriter();
				while (ex.getCause() != null) {
					ex = (Exception) ex.getCause();
				}
				if (ex.getClass() == AjaxException.class) {
					print.write( ((AjaxException) ex).getJsonData());
				}else {
					print.write(ResponseJson.createJson(0, Constants.AJAX_EXECPTION).toString());
				}
				print.flush();
				print.close();
				
				LOGGER.error("Exception: ", ex);			
			}
		} 
		// 用户没有权限访问资源， 返回公共资源首页
		else {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter print = response.getWriter();
			print.write(ResponseJson.createJson(0, Constants.PERMISSION_DENIED).toString());
			print.flush();
			print.close();
		}	
		
	}

	public void init(FilterConfig config) throws ServletException {

	}
	
	public void destroy() {
		
	}

}
