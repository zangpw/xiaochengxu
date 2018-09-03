package com.spring.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.spring.util.CommonConstants;

@Controller
@RequestMapping("common")
public class CommonController {
	/*
	 * 采用spring提供的上传文件的方法
	 */
	@RequestMapping("springUpload")
	@ResponseBody
	public JSONObject springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
		JSONObject jsonObj = new JSONObject();
		long startTime = System.currentTimeMillis();
		try {

			List<String> fileNames = null;
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				fileNames = new ArrayList<String>();
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator iter = multiRequest.getFileNames();

				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						String path =CommonConstants.FILE_BASEPATH + file.getOriginalFilename();
						// 上传
						file.transferTo(new File(path));
						fileNames.add(CommonConstants.IMG_SERVER+file.getOriginalFilename());
					}
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
			jsonObj.put("result", 0);
			jsonObj.put("msg", "上传成功");
			jsonObj.put("data", fileNames);
		} catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
}
