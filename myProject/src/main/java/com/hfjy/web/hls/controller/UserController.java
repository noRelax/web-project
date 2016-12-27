package com.hfjy.web.hls.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfjy.base.dao.SchoolDao;
import com.hfjy.base.dao.impl.SchoolDaoImpl;
import com.hfjy.base.entity.School;
import com.hfjy.base.entity.UserInfo;
import com.hfjy.base.service.hls.UserService;
import com.hfjy.base.service.hls.impl.UserServiceImpl;
import com.hfjy.framework.beans.Bean;
import com.hfjy.framework.net.transport.ResponseJson;
import com.hfjy.web.hls.base.CommonController;

@Controller
@RequestMapping("/hfjy")
public class UserController extends CommonController {
	@Bean(thisClass = UserServiceImpl.class)
	private UserService userService;
	@Bean(thisClass = SchoolDaoImpl.class)
	private SchoolDao schoolDao;
	@RequestMapping("/getTestInfo")
	@ResponseBody
	public Object getTestInfo() throws Exception {
		UserInfo userInfo = userService.selectUser("18516266683");
		return ResponseJson.createJson(1, "获取用户信息成功！", userInfo);
	}
	@RequestMapping("/loaderSchool")
	@ResponseBody
	public Object loaderSchool(String schoolName,Integer type) throws Exception{
		List<School> schools = schoolDao.findSchoolByConditions(schoolName, type);
		return ResponseJson.createJson(1, "获得高校成功！", schools);
	}
	
}
