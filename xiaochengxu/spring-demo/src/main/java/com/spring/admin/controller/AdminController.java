package com.spring.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.model.Admin;
import com.spring.model.Member;
import com.spring.model.OfficialNews;
import com.spring.service.AdminService;
import com.spring.util.Digests;

@Controller
@RequestMapping("admin")
public class AdminController {
	@Resource
	private AdminService adminService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject login(@RequestBody String str, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			JSONObject json = JSONObject.parseObject(str);
			String name = (String) json.get("name");
			String password = (String) json.get("password");
			Admin admin = new Admin();
			admin.setName(name);
			admin.setDataFlag((short) 1);
			admin = adminService.queryOne(admin);
			if (admin == null) {
				jsonObj.put("result", -1);
				jsonObj.put("msg", "用户不存在");
				jsonObj.put("data", "[]");
			}
			if (Digests.validatePassword(password, admin.getPassword())) {
				HttpSession session = request.getSession();
				session.setAttribute("admin", admin);
				json.clear();
				json.put("sid", session.getId());
				jsonObj.put("result", 0);
				jsonObj.put("msg", "登录成功");
				jsonObj.put("data", json);
			} else {
				jsonObj.put("result", -1);
				jsonObj.put("msg", "密码错误");
				jsonObj.put("data", "[]");
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
	
	@RequestMapping(value="get",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject get(HttpServletRequest request){
		JSONObject jsonObj = new JSONObject();
		try{
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("admin");
			jsonObj.put("result", 0);
			jsonObj.put("msg", "获取成功");
			jsonObj.put("data",admin);
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
	@RequestMapping(value="loginout",method=RequestMethod.GET)
	@ResponseBody
	public JSONObject loginout(HttpServletRequest request){
		JSONObject jsonObj = new JSONObject();
		try{
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("admin");
			if(admin!=null) {
				session.removeAttribute("admin");
			}
			jsonObj.put("result", 0);
			jsonObj.put("msg", "退出成功");
			jsonObj.put("data","[]");
		}catch (Exception e) {
			System.err.println("出错了" + e);
			jsonObj.put("result", -1);
			jsonObj.put("msg", "服务器异常");
			jsonObj.put("data", "[]");
		}
		return jsonObj;
	}
	
}
