package com.spring.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.spring.model.OfficialNews;
import com.spring.service.OfficialNewsService;

@Controller
@RequestMapping("officialNews")
public class OfficialNewsController {
	@Resource
	private OfficialNewsService officialNewsService;
	
	@RequestMapping(value="getPage",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getListByBrandId(OfficialNews officialNews,Integer page,Integer rows,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			officialNews.setDataFlag((short) 1);
			PageInfo<OfficialNews> officialNewsList = officialNewsService.queryPageListByWhere(page, rows, officialNews);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", officialNewsList);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="get",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject get(Long id){
		JSONObject jsonObj = new JSONObject();
		try{
			OfficialNews officialNews = officialNewsService.queryById(id);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",officialNews);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject add(@RequestBody OfficialNews officialNews,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			Integer num =officialNewsService.saveSelective(officialNews);
			if(num>0){
				jsonObj.put("result", 0);
				jsonObj.put("msg", "添加成功");
			}
		}catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject update(@RequestBody OfficialNews officialNews,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			Integer num =officialNewsService.updateSelective(officialNews);
			if(num>0){
				jsonObj.put("result", 0);
				jsonObj.put("msg", "修改成功");
				jsonObj.put("data",num);
			}
		}catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject delete(@RequestBody OfficialNews officialNews,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			officialNews.setDataFlag((short) -1);
			Integer num =officialNewsService.updateSelective(officialNews);
			if(num>0){
				jsonObj.put("result", 0);
				jsonObj.put("msg", "删除成功");
				jsonObj.put("data",num);
			}
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
