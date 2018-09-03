package com.spring.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.spring.util.MySessionContext;

@Controller
public class DemoController {
	
	@RequestMapping("/demo")
	@ResponseBody
	public JSONObject demo(HttpServletRequest request){
		JSONObject json = new JSONObject();
		MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(5);
        session.setAttribute("demo", "demo");
		return json;
	}
	
	@RequestMapping("/getSession")
	@ResponseBody
	public JSONObject getSession(String  sid){
		JSONObject json = new JSONObject();
		try {
			MySessionContext myc = MySessionContext.getInstance();
			HttpSession session = myc.getSession(sid);
			String demo=(String) session.getAttribute("demo");
		} catch (IllegalStateException e) {
			// TODO: handle exception
			System.out.println("错误："+e.getMessage());
//			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("错误："+e.toString());
			e.printStackTrace();
		}
		json.put("demo", "demo");
		return json;
	}
	
	@RequestMapping("index")
	public String index() {
		return "Login.jsp";
	}
}
