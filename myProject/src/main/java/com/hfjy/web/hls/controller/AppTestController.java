/**
 * 海风app在线学习平台
 * @author: no_relax
 * @Title: AppTestController.java 
 * @Package: com.hfjy.web.hls.appController
 * @date: 2016年12月27日-上午11:34:08
 * @version: Vpad1.2.0
 * @copyright: 2016上海风创信息咨询有限公司-版权所有
 * 
 */

package com.hfjy.web.hls.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hfjy.framework.net.transport.ResponseJson;

/**
 * @ClassName: AppTestController
 * @Description: TODO(app测试控制类)
 * @author no_relax
 * @date 2016年12月27日 上午11:34:08
 * 
 */
@Controller
@RequestMapping(value = "/app", method = RequestMethod.GET)
public class AppTestController {

	@RequestMapping("/imageExport")
	@ResponseBody
	public Object imageExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String filename = "norelax.jpg";// 导出的文件名
		String filePath = "C:/Users/wusong/Pictures/my1.jpg";// 要导出的文件路径
		response.setContentType("application/octet-stream");// ".*"
		response.addHeader("Content-Disposition", "attachment;filename=" + filename);
		File file = new File(filePath);
		FileInputStream in = new FileInputStream(file);// 输入流
		ServletOutputStream out = response.getOutputStream();// 输出流
		byte[] byteBuffers = new byte[1024];
		int read = 0;
		while ((read = in.read(byteBuffers)) != -1) {
			out.write(byteBuffers, 0, read);
		}
		out.flush();
		out.close();
		in.close();
		return ResponseJson.createJson(1, "success to export!");
	}

}
