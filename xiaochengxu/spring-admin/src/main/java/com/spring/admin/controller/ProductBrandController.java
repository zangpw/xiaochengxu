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
import com.spring.model.ProductBrand;
import com.spring.service.ProductBrandService;

@Controller
@RequestMapping("productBrand")
public class ProductBrandController {
	@Resource
	private ProductBrandService productBrandService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject list(Long productId){
		JSONObject jsonObj = new JSONObject();
		try{
			ProductBrand productBrand=new ProductBrand();
			productBrand.setProductId(productId);
			productBrand.setDataFlag((short) 1);
			List<ProductBrand> productBrandList = productBrandService.queryListByWhere(productBrand);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",productBrandList);
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
			ProductBrand productBrand = productBrandService.queryById(id);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",productBrand);
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
	public JSONObject add(@RequestBody ProductBrand productBrand){
		JSONObject jsonObj = new JSONObject();
		try{
			productBrand.setDataFlag((short) 1);
			Integer num = productBrandService.saveSelective(productBrand);
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
	
	@RequestMapping(value="productBrandPage",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject productBrandPage(Long productId,String name,Integer page,Integer rows){
		JSONObject jsonObj = new JSONObject();
		try{
			ProductBrand productBrand=new ProductBrand();
			productBrand.setDataFlag((short) 1);
			productBrand.setProductId(productId);
			productBrand.setName(name);
			PageInfo<ProductBrand> productBrandPage = productBrandService.queryPageListByWhere(page, rows, productBrand);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",productBrandPage);
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
	public JSONObject update(@RequestBody ProductBrand productBrand){
		JSONObject jsonObj = new JSONObject();
		try{
			productBrand.setDataFlag((short) 1);
			Integer num = productBrandService.updateSelective(productBrand);
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
	public JSONObject delete(@RequestBody ProductBrand productBrand){
		JSONObject jsonObj = new JSONObject();
		try{
			productBrand.setDataFlag((short) -1);
			Integer num = productBrandService.updateSelective(productBrand);
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
