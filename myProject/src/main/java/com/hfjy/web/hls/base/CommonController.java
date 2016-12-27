package com.hfjy.web.hls.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hfjy.base.bean.SessionUser;
import com.hfjy.base.cache.AppContext;
import com.hfjy.base.util.Constants;
import com.hfjy.framework.biz.BaseController;
import com.hfjy.framework.common.util.StringUtils;

public class CommonController extends BaseController {

	protected SessionUser getSessionUser(HttpServletRequest request) {
		String tokenCode = request.getHeader("tokenCode");
		HttpSession session = getSession(request);
		SessionUser su = null;
		// 请求头带tokenCode码时，说明是app请求，从缓存中获取用户信息
		if (StringUtils.isNotEmpty(tokenCode)) {
			su = AppContext.get(tokenCode, SessionUser.class);
		} else {
			su = (SessionUser) session.getAttribute(Constants.SESSION_USER);
		}
		return su;
	}

	protected int getUserId(HttpServletRequest request) {
		SessionUser su = getSessionUser(request);
		return (su != null ? su.getUserId() : -999);
	}

	protected int getEvrEriteInfoTempId(HttpServletRequest request) {
		HttpSession session = getSession(request);
		Integer evrEriteInfoTempId = (Integer) session.getAttribute(Constants.SESSION_EVR_VISITOR);
		return (evrEriteInfoTempId != null ? evrEriteInfoTempId : -999);
	}

	protected String getEvrStep(HttpServletRequest request) {
		HttpSession session = getSession(request);
		String evrStep = (String) session.getAttribute(Constants.SESSION_EVR_STEP);
		return evrStep;
	}

	protected String getUserName(HttpServletRequest request) {
		return getSessionUser(request).getName();
	}

	protected HttpSession getSession(HttpServletRequest request) {
		return request.getSession();
	}
}
