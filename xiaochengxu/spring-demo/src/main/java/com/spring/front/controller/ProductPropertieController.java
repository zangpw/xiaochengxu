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
import com.spring.model.ProductModel;
import com.spring.model.ProductPropertie;
import com.spring.service.ProductPropertieService;

@Controller
@RequestMapping("productPropertie")
public class ProductPropertieController {
	@Resource
	private ProductPropertieService productPropertieService;

	@RequestMapping(value = "getPageByProductModelId", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getListByBrandId(String productModelId,Integer page,Integer rows, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			ProductPropertie productPropertie = new ProductPropertie();
			productPropertie.setProductModelId(Long.valueOf(productModelId));
			productPropertie.setDataFlag((short) 1);
			PageInfo<ProductPropertie> propertieList = productPropertieService
					.queryPropertieAndDetailPageListByWhere(page, rows, productPropertie);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", propertieList);
		} catch (Exception e) {
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
			ProductPropertie productPropertie = productPropertieService.queryById(id);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",productPropertie);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject add(@RequestBody String str, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			JSONObject json = JSONObject.parseObject(str);
			String productModelId = (String) json.get("productModelId");
			String name = (String) json.get("name");
			ProductPropertie productPropertie = new ProductPropertie();
			productPropertie.setProductModelId(Long.valueOf(productModelId));
			productPropertie.setName(name);
			Integer num = productPropertieService.saveSelective(productPropertie);
			if (num > 0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "添加成功");
			}
		} catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject update(@RequestBody ProductPropertie productPropertie, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			Integer num = productPropertieService.updateSelective(productPropertie);
			if (num > 0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "修改成功");
				jsonObj.put("data", "[]");
			}
		} catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject delete(@RequestBody ProductPropertie productPropertie, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			productPropertie.setDataFlag((short) -1);
			Integer num = productPropertieService.updateSelective(productPropertie);
			if (num > 0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "修改成功");
				jsonObj.put("data", "[]");
			}
		} catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
}
