package com.spring.front.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.model.Product;
import com.spring.service.ProductService;
@Controller
@RequestMapping("product")
public class ProductController {

	@Resource
	private ProductService productService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject list() {
		JSONObject jsonObj = new JSONObject();
		try{
			List<Product> products = productService.queryAll();
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",products);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
}
