package com.spring.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.spring.model.IndexNews;
import com.spring.service.IndexNewsService;

@Controller
@RequestMapping("indexNews")
public class IndexNewsController {

	@Resource
	private IndexNewsService indexNewsService;
	
	@RequestMapping(value="getList",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getList() {
		JSONObject jsonObj = new JSONObject();
		try{
			IndexNews indexNews=new IndexNews();
			indexNews.setDataFlag((short) 1);
			List<IndexNews> indexNewsList = indexNewsService.queryListByWhere(indexNews);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",indexNewsList);
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
			IndexNews indexNews = indexNewsService.queryById(id);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",indexNews);
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
	public JSONObject add(@RequestBody IndexNews indexNews){
		JSONObject jsonObj = new JSONObject();
		try{
			indexNews.setDataFlag((short) 1);
			Integer num = indexNewsService.saveSelective(indexNews);
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
	public JSONObject update(@RequestBody IndexNews indexNews){
		JSONObject jsonObj = new JSONObject();
		try{
			indexNews.setDataFlag((short) 1);
			Integer num = indexNewsService.updateSelective(indexNews);
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
	public JSONObject delete(@RequestBody IndexNews indexNews){
		JSONObject jsonObj = new JSONObject();
		try{
			indexNews.setDataFlag((short) -1);
			Integer num = indexNewsService.updateSelective(indexNews);
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
