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
import com.spring.model.PropertyDetail;
import com.spring.service.PropertyDetailService;

@Controller
@RequestMapping("propertyDetail")
public class PropertyDetailController {
	@Resource
	private PropertyDetailService propertyDetailService;

	@RequestMapping(value = "getPageByProductPropertieId", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getListByBrandId(@RequestBody String str, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			JSONObject json = JSONObject.parseObject(str);
			String productPropertieId = (String) json.get("productPropertieId");
			Integer page = (Integer) json.get("page");
			Integer rows = (Integer) json.get("rows");
			PropertyDetail propertyDetail = new PropertyDetail();
			propertyDetail.setProductPropertieId(Long.valueOf(productPropertieId));
			propertyDetail.setDataFlag((short) 1);
			PageInfo<PropertyDetail> detailList = propertyDetailService.queryPageListByWhere(page, rows,
					propertyDetail);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data", detailList);
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
			PropertyDetail propertyDetail = propertyDetailService.queryById(id);
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",propertyDetail);
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
			String productPropertieId = (String) json.get("productPropertieId");
			String value = (String) json.get("value");
			String weightRatio = (String) json.get("weightRatio");
			String remark = (String) json.get("remark");
			PropertyDetail propertyDetail = new PropertyDetail();
			propertyDetail.setProductPropertieId(Long.valueOf(productPropertieId));
			propertyDetail.setValue(value);
			propertyDetail.setWeightRatio(Double.valueOf(weightRatio));
			propertyDetail.setRemark(remark);
			Integer num = propertyDetailService.saveSelective(propertyDetail);
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
	public JSONObject update(PropertyDetail propertyDetail, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			Integer num = propertyDetailService.updateSelective(propertyDetail);
			if (num > 0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "修改成功");
				jsonObj.put("data",num);
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
	public JSONObject delete(@RequestBody PropertyDetail propertyDetail, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			propertyDetail.setDataFlag((short) -1);
			Integer num = propertyDetailService.updateSelective(propertyDetail);
			if (num > 0) {
				jsonObj.put("result", 0);
				jsonObj.put("msg", "修改成功");
				jsonObj.put("data",num);
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
