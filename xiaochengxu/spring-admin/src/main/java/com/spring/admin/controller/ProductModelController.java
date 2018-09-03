package com.spring.admin.controller;

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
import com.spring.service.ProductModelService;

@Controller
@RequestMapping("productModel")
public class ProductModelController {
	@Resource
	private ProductModelService productModelService;

	@RequestMapping(value = "getPageByProductTypeId", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getListByBrandId(String productTypeId,Integer page,Integer rows, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			
			ProductModel productModel = new ProductModel();
			productModel.setProductTypeId(Long.valueOf(productTypeId));
			productModel.setDataFlag((short) 1);
			PageInfo<ProductModel> modelList = productModelService.queryPageListByWhere(page, rows, productModel);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", modelList);
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
			ProductModel productModel = productModelService.getProductModelById(id);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",productModel);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject add(@RequestBody ProductModel productModel, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			Integer num = productModelService.saveProductModel(productModel);
			if (num > 0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "添加成功");
				jsonObj.put("data", num);
			}
		} catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject update(@RequestBody ProductModel productModel, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			Integer num = productModelService.updateProductModel(productModel);
			if (num > 0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "修改成功");
				jsonObj.put("data", num);
			}
		} catch (Exception e) {
			System.err.println("出错了" + e);
			e.printStackTrace();
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject delete(@RequestBody ProductModel productModel, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			productModel.setDataFlag((short) -1);
			Integer num = productModelService.updateSelective(productModel);
			if (num > 0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "删除成功");
				jsonObj.put("data", num);
			}
		} catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="getListByProductName")
	@ResponseBody
	public JSONObject getListByProductName(Integer page, Integer rows,String productName) {
		JSONObject jsonObj = new JSONObject();
		try {
			PageInfo<ProductModel> pages = productModelService.getPageByProductName(page,rows,productName);
				jsonObj.put("result", 0);
				jsonObj.put("msg", "查询成功");
				jsonObj.put("data", pages);
		} catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
}
