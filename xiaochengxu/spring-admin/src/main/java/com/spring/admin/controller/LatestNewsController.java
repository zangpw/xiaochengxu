package com.spring.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.spring.model.LatestNews;
import com.spring.service.LatestNewsService;

@Controller
@RequestMapping("latestNews")
public class LatestNewsController {
	@Resource
	private LatestNewsService latestNewsService;
	
	@RequestMapping(value="getPage",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getList(Integer page,Integer rows) {
		JSONObject jsonObj = new JSONObject();
		try{
			LatestNews latestNews=new LatestNews();
			latestNews.setDataFlag((short) 1);
			PageInfo<LatestNews> latestNewsPage = latestNewsService.queryPageListByWhere(page, rows, latestNews);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",latestNewsPage);
		}catch (Exception e) {
			System.err.println("出错了" + e);
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
			LatestNews latestNews = latestNewsService.queryById(id);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",latestNews);
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
	public JSONObject add(@RequestBody LatestNews latestNews){
		JSONObject jsonObj = new JSONObject();
		try{
			latestNews.setDataFlag((short) 1);
			Integer num = latestNewsService.saveSelective(latestNews);
			if(num>0){
				jsonObj.put("result", 0);
				jsonObj.put("msg", "添加成功");
			}
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject update(@RequestBody LatestNews latestNews){
		JSONObject jsonObj = new JSONObject();
		try{
			latestNews.setDataFlag((short) 1);
			Integer num = latestNewsService.updateSelective(latestNews);
			if(num>0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "修改成功");
				jsonObj.put("data",num);
			}else {
				jsonObj.put("result", -1);
				jsonObj.put("msg", "修改失败");
				jsonObj.put("data",num);
			}
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject delete(@RequestBody LatestNews latestNews){
		JSONObject jsonObj = new JSONObject();
		try{
			latestNews.setDataFlag((short) -1);
			Integer num = latestNewsService.updateSelective(latestNews);
			if(num>0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "删除成功");
				jsonObj.put("data",num);
			}else {
				jsonObj.put("result", -1);
				jsonObj.put("msg", "删除失败");
				jsonObj.put("data",num);
			}
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
}
