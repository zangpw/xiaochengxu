package com.spring.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.spring.model.QuestionAnswer;
import com.spring.service.QuestionAnswerService;

@Controller
@RequestMapping("questionAnswer")
public class QuestionAnswerController {
	@Resource
	private QuestionAnswerService questionAnswerService;
	
	@RequestMapping("getPage")
	@ResponseBody
	public JSONObject getPage(Integer page,Integer rows) {
		JSONObject jsonObj = new JSONObject();
		try {
			QuestionAnswer questionAnswer=new QuestionAnswer();
			questionAnswer.setDataFlag((short) 1);
			PageInfo<QuestionAnswer> questionAnswerPage = questionAnswerService.queryPageListByWhere(page, rows, questionAnswer);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", questionAnswerPage);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
}
