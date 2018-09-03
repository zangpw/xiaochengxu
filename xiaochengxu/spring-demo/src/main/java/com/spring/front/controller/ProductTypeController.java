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
import com.spring.model.ProductType;
import com.spring.service.ProductTypeService;

@Controller
@RequestMapping("productType")
public class ProductTypeController {
	@Resource
	private ProductTypeService productTypeService;
	
	@RequestMapping(value="getPageByBrandId",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject getListByBrandId(String brandId,Integer page,Integer rows,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			ProductType productType = new ProductType();
			productType.setBrandId(Long.valueOf(brandId));
			productType.setDataFlag((short) 1);
			PageInfo<ProductType> typeList = productTypeService.queryPageListByWhere(page, rows, productType);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", typeList);
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
			ProductType productType = productTypeService.queryById(id);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",productType);
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
	public JSONObject add(@RequestBody ProductType productType,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			Integer num =productTypeService.saveSelective(productType);
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
	public JSONObject update(@RequestBody ProductType productType,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			Integer num =productTypeService.updateSelective(productType);
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
	public JSONObject delete(@RequestBody ProductType productType,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			productType.setDataFlag((short) -1);
			Integer num =productTypeService.updateSelective(productType);
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
